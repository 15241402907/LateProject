package com.lgd.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lgd.dao.VideoDao;
import com.lgd.entity.Video;
import com.lgd.util.FIleUploadAliyunUtils;
import com.lgd.util.InterceptorVideoUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class VideoServiceImpl implements VideoService{

    @Resource
    private VideoDao videoDao;
    @Override
    public HashMap<String, Object> selectAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);

        //查询总条数
        Integer count = videoDao.count();
        map.put("records",count);

        //计算总页数 总页数=总条数/每页展示的条数
        Integer total = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",total);

        List<Video> videos = videoDao.selectAll((page - 1) * rows, rows);

        map.put("rows",videos);
        return map;
    }

    @Override
    public void insert(Video video) {
        video.setUploadTime(new Date());
        video.setId(UUID.randomUUID().toString());
        videoDao.insert(video);
    }

    @Override
    public void delete(String id) {
        videoDao.delete(id);
    }

    @Override
    public void update(Video video) {
        videoDao.update(video);
    }

    @Override
    public Video selectOne(String id) {
        return videoDao.selectOne(id);
    }

    @Override
    public void deleteFile(String newName) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tHE98KtPFrLbmdb2s3M";
        String accessKeySecret = "X7o0WUt9WyLNvCfrnxWCbDD6PdZznR";
        String bucketName = "yingx-tanglj";
        String objectName = newName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    @Override
    public void uploadFile(MultipartFile videoPath, String id, HttpServletRequest request){
        //将文件上传到阿里云
        String objectName = FIleUploadAliyunUtils.uploadAliyunFile(videoPath);
        //生成本地图片存储文件夹
        String realPath = request.getSession().getServletContext().getRealPath("/Image");
        File file = new File(realPath);
        if(!file.exists())file.mkdirs();
        //生成图片文件名
        String[] split = objectName.replace("video/", "").split("\\.");
        String photoName = split[0] + ".jpg";
        String framefile = realPath+photoName;
        //截取阿里云中视频的封面并生成本地图片文件
        try {
            InterceptorVideoUtils.fetchFrame("https://yingx-tanglj.oss-cn-beijing.aliyuncs.com/"+objectName,framefile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将本地图片文件上传到阿里云
        FIleUploadAliyunUtils.uploadLocalFile("image/"+photoName,framefile);

        //删除本地图片
        File file1 = new File(framefile);
        if(file1.exists())file1.delete();

        //根据id查询视频对象
        Video video = selectOne(id);

        //修改数据库中的视频地址
        //https://yingx-tanglj.oss-cn-beijing.aliyuncs.com/video/1622201955170%E5%BA%94%E5%AD%A6App%E4%BD%BF%E7%94%A8.mp4
        //https://yingx-tanglj.oss-cn-beijing.aliyuncs.com/video/1622204536751yingx.mp4

        video.setVideoPath("https://yingx-tanglj.oss-cn-beijing.aliyuncs.com/"+objectName);
        video.setCoverPath("https://yingx-tanglj.oss-cn-beijing.aliyuncs.com/"+"image/"+photoName);
        update(video);
    }



}

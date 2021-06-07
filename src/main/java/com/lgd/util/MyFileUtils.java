package com.lgd.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MyFileUtils {

    /*
    file:文件
    path:  "/"+文件夹名  本方法自动创建日期文件夹
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request, String path){
        //获取原文件名
        String oldName = file.getOriginalFilename();
        //获取当前日期的文件夹
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        //获取文件的拓展名
        String str = FilenameUtils.getExtension(oldName);
        //生成新的文件名
        String newName = UUID.randomUUID().toString() + "." + str;
        //获取要上传的文件的绝对路径
        String realPath = request.getSession().getServletContext().getRealPath(path +"/"+ format);
        //判断文件夹是否存在，不存在则创建文件夹
        File f = new File(realPath);
        if(!f.exists()){
            f.mkdirs();
        }
        try {
            file.transferTo(new File(realPath,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //动态获取项目名
        String contextPath = request.getContextPath();
        return path+"/"+format+"/"+newName;
    }
    /*
    path:相对路径
    fileName:新文件名
     */
    public static void downloadFile(String path,String fileName, HttpServletResponse response,HttpServletRequest request){
        //设置下载方式
        try {
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName+".jpg","UTF-8"));
            //获取绝对路径
            String realPath = request.getSession().getServletContext().getRealPath(path);
            //获取响应输入流
            FileInputStream is = new FileInputStream(new File(realPath));
            //获取响应输出流
            ServletOutputStream os = response.getOutputStream();
            //文件拷贝
            IOUtils.copy(is,os);
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    path:相对路径
     */
    public static void deleteFile(String path,HttpServletRequest request){
        if(path!=null){
            String realPath = request.getSession().getServletContext().getRealPath(path);
            File file = new File(realPath);
            if(file.exists()){
                file.delete();
            }
        }
    }
}

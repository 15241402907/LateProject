package com.lgd.controller;

import com.lgd.entity.Video;
import com.lgd.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @RequestMapping("queryByPage")
    @ResponseBody
    public HashMap<String, Object> queryByPage(Integer page,Integer rows){
        HashMap<String, Object> map = videoService.selectAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(String oper, String id, Video video){
        String message = null;
        if(oper.equals("del")){
            Video video1 = videoService.selectOne(id);
            videoService.delete(id);
            String replace = video1.getVideoPath().replace("https://yingx-tanglj.oss-cn-beijing.aliyuncs.com/", "");
            videoService.deleteFile(replace);
        }
        if(oper.equals("edit")){

            message=video.getId();
            video.setVideoPath(videoService.selectOne(message).getVideoPath());
            videoService.update(video);
        }
        if(oper.equals("add")){
            videoService.insert(video);
            message=video.getId();
        }
        return message;
    }

    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile videoPath, String id, HttpServletRequest request){
        videoService.uploadFile(videoPath, id,request);
    }

    @RequestMapping("update")
    @ResponseBody
    public void updateFile(MultipartFile videoPath,  String id,HttpServletRequest request){
        if(videoPath.isEmpty())return;
        Video video = videoService.selectOne(id);
        String replace = video.getVideoPath().replace("https://yingx-tanglj.oss-cn-beijing.aliyuncs.com/", "");
        videoService.deleteFile(replace);
        videoService.uploadFile(videoPath, id,request);


    }

}

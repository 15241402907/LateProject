package com.lgd.service;

import com.lgd.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface VideoService {

    HashMap<String, Object> selectAll(Integer page, Integer rows);

    void insert(Video video);

    void delete(String id);

    void update(Video video);


    Video selectOne(String id);

    void deleteFile(String newName);

    void uploadFile(MultipartFile videoPath, String id, HttpServletRequest request);









}

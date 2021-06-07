package com.lgd.dao;

import com.lgd.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {

    List<Video> selectAll(@Param("begin")Integer begin,@Param("end") Integer end);

    void insert(Video video);

    void delete(String id);

    void update(Video video);


    Video selectOne(String id);

    Integer count();


}

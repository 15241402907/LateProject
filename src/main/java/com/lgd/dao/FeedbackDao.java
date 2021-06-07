package com.lgd.dao;

import com.lgd.entity.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackDao {

    List<Feedback> queryAll();

    List<Feedback> queryByPage(@Param("begin") Integer begin,@Param("end") Integer end);

    Integer count();



}

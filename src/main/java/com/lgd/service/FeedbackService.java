package com.lgd.service;

import com.lgd.entity.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface FeedbackService {

    List<Feedback> queryAll();

    HashMap<String, Object> queryByPage(Integer begin, Integer end);


}

package com.lgd.controller;

import com.lgd.entity.Feedback;
import com.lgd.service.FeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("feedback")
public class FeedbackController {
    @Resource
    private FeedbackService feedbackService;

    @RequestMapping("queryByPage")
    @ResponseBody
    public HashMap<String, Object> queryByPage(Integer page,Integer rows){
        HashMap<String, Object> map = feedbackService.queryByPage(page, rows);
        return  map;
    }

    @RequestMapping("queryAll")
    @ResponseBody
    public List<Feedback> queryAll(){
        List<Feedback> list = feedbackService.queryAll();
        return  list;
    }





}

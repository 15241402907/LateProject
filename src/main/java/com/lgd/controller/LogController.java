package com.lgd.controller;

import com.lgd.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("log")
public class LogController {
    @Resource
    private LogService logService;


    @RequestMapping("queryByPage")
    @ResponseBody
    public Map<String, Object> queryByPage(Integer page,Integer rows){
        Map<String, Object> map = logService.queryByPage(page, rows);
        return map;
    }



}

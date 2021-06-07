package com.lgd.controller;

import com.lgd.entity.Category;
import com.lgd.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;


    @RequestMapping("queryOne")
    @ResponseBody
    public Map<String, Object> queryOne(Integer page,Integer rows){
        Map<String, Object> map = categoryService.selectOneByPage(page, rows);
        return map;
    }

    @RequestMapping("queryTwo")
    @ResponseBody
    public Map<String, Object> queryTwo(Integer page,Integer rows,String id){
        Map<String, Object> map = categoryService.selectTwoByPage(id,page, rows);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public Map<String,String> edit(String oper,Category category,String id,String parentId){
        Map<String,String> map=null;
        if(oper.equals("del")){
            categoryService.delete(id);
        }
        if(oper.equals("edit")){
            categoryService.update(category);
        }
        if(oper.equals("add")){
            categoryService.insert(category,parentId);
        }
        return map;
    }








}

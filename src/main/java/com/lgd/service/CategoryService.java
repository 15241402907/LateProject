package com.lgd.service;

import com.lgd.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {


    Map<String, Object> selectOneByPage(Integer page, Integer rows);

    Map<String, Object> selectTwoByPage(String id,Integer page, Integer rows);

    void insert(Category category,String parentId);

    void delete(String id);

    void update(Category category);


}

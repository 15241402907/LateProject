package com.lgd.service;

import com.lgd.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdminService {

    Admin login(String username);


    Map<String, Object> queryByLevel(Integer begin, Integer end);

    void insert(Admin admin);

    void delete(String id);

    void update(Admin admin);


}

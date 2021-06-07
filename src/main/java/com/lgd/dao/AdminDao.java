package com.lgd.dao;

import com.lgd.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {

    Admin login(String username);

    List<Admin> queryByLevel(@Param("begin") Integer begin, @Param("end") Integer end);

    void insert(Admin admin);

    void delete(String id);

    void update(Admin admin);

    Integer count();


}
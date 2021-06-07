package com.lgd.dao;

import com.lgd.entity.Category;
import com.lgd.entity.Feedback;
import com.lgd.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    List<User> queryByPage(@Param("begin") Integer begin, @Param("end") Integer end);

    Integer count();

    void insert(User user);

    void delete(String id);

    void update(User user);

    User selectOne(String id);

}

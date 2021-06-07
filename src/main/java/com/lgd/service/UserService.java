package com.lgd.service;

import com.lgd.entity.Feedback;
import com.lgd.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {



    Map<String, Object> queryByPage(Integer begin, Integer end);

    void insert(User user);

    void delete(String id);

    void update(User user);

    User selectOne(String id);

}

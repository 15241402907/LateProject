package com.lgd.service;

import com.lgd.dao.UserDao;
import com.lgd.entity.Feedback;
import com.lgd.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.*;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao  userDao;



    @Override
    public Map<String, Object> queryByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);

        //查询总条数
        Integer count = userDao.count();
        map.put("records",count);

        //计算总页数 总页数=总条数/每页展示的条数
        Integer total = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",total);

        List<User> users = userDao.queryByPage((page - 1) * rows, rows);

        map.put("rows",users);
        return map;
    }

    @Override
    public void insert(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setStatus("1");
        user.setWechat(user.getPhone());
        user.setRegistTime(new Date());
        userDao.insert(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User selectOne(String id) {
        return userDao.selectOne(id);
    }


}

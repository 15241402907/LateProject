package com.lgd.service;

import com.lgd.dao.AdminDao;
import com.lgd.entity.Admin;
import com.lgd.entity.Feedback;
import com.lgd.util.Md5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

    @Resource
    private AdminDao adminDao;

    @Override
    public Admin login(String username) {
        return adminDao.login(username);
    }

    @Override
    public Map<String, Object> queryByLevel(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);

        //查询总条数
        Integer count = adminDao.count();
        map.put("records",count);

        //计算总页数 总页数=总条数/每页展示的条数
        Integer total = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",total);

        List<Admin> admins = adminDao.queryByLevel((page - 1) * rows, rows);

        map.put("rows",admins);
        return map;
    }

    @Override
    public void insert(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        String salt = Md5Utils.getSalt(6);
        admin.setSalt(salt);
        String md5Code = Md5Utils.getMd5Code(salt + admin.getPassword() + salt);
        admin.setPassword(md5Code);
        adminDao.insert(admin);
    }

    @Override
    public void delete(String id) {
        adminDao.delete(id);
    }

    @Override
    public void update(Admin admin) {
        adminDao.update(admin);
    }
}

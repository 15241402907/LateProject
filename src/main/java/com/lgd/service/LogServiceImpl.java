package com.lgd.service;

import com.lgd.dao.LogDao;
import com.lgd.entity.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LogServiceImpl implements LogService{
    @Resource
    private LogDao logDao;

    @Override
    public Map<String, Object> queryByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);

        //查询总条数
        Integer count = logDao.count();
        map.put("records",count);

        //计算总页数 总页数=总条数/每页展示的条数
        Integer total = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",total);

        List<Log> logs = logDao.selectAll((page - 1) * rows, rows);

        map.put("rows",logs);
        return map;
    }
}

package com.lgd.service;

import com.lgd.dao.FeedbackDao;
import com.lgd.entity.Feedback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService{
    @Resource
    private FeedbackDao feedbackDao;

    @Override
    public List<Feedback> queryAll() {
        return feedbackDao.queryAll();
    }

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);

        //查询总条数
        Integer count = feedbackDao.count();
        map.put("records",count);

        //计算总页数 总页数=总条数/每页展示的条数
        Integer total = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",total);

        List<Feedback> feedbacks = feedbackDao.queryByPage((page - 1) * rows, rows);

        map.put("rows",feedbacks);
        return map;


    }
}

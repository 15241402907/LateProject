package com.lgd;


import com.lgd.dao.LogDao;
import com.lgd.entity.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLog {
    @Resource
    private LogDao logDao;


    @Test
    public void show(){
        List<Log> logs = logDao.selectAll(0, 2);
        logs.forEach(a-> System.out.println(a));
    }




}

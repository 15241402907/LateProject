package com.lgd;

import com.lgd.dao.UserDao;
import com.lgd.dao.VideoDao;
import com.lgd.entity.User;
import com.lgd.entity.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestVideoDao {

    @Resource
    VideoDao videoDao;

    @Test
    public void us(){
//        Video video = videoDao.selectOne("1");
//        System.out.println(video);

//        videoDao.insert(new Video("2","lllll","hahaha","111111","111111",new Date(),"5","0","1",null,null,null));

//        videoDao.update(new Video("2","JK绝对领域","hahaha","111111","111111",new Date(),"5","0","1",null,null,null));
        List<Video> videos = videoDao.selectAll(0, 10);
        for (Video video : videos) {
            System.out.println(video);
        }
//        videoDao.delete("1");



    }


}

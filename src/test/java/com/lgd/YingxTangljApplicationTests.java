package com.lgd;



import com.lgd.dao.AdminDao;
import com.lgd.dao.CategoryDao;
import com.lgd.dao.FeedbackDao;
import com.lgd.dao.UserDao;
import com.lgd.entity.Admin;
import com.lgd.entity.Category;
import com.lgd.entity.Feedback;
import com.lgd.entity.User;
import com.lgd.util.Md5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class YingxTangljApplicationTests {

    @Resource
    AdminDao adminDao;

    @Resource
    FeedbackDao feedbackDao;

    @Resource
    UserDao userDao;

    @Resource
    CategoryDao categoryDao;


    @Test
    public void contextLoads() {
        Admin tlj = adminDao.login("tlj");
        System.out.println(tlj);

    }


    @Test
    public void co() {
        List<Feedback> fd = feedbackDao.queryAll();
        fd.forEach(a-> System.out.println(a));

        List<Feedback> feedbacks = feedbackDao.queryByPage(0, 2);
        feedbacks.forEach(aa-> System.out.println(aa));

        System.out.println(feedbackDao.count());

    }


    @Test
    public void coo() {

//        List<User> users = userDao.queryByPage(0, 3);
//        System.out.println(userDao.count());
//        users.forEach(a-> System.out.println(a));
//        userDao.updateStatus("3","1");


    }

    @Test
    public void ad() {
        System.out.println(adminDao.queryByLevel(0,1));
//        adminDao.insert(new Admin("3","ghb","123456","正常","2",null));
//        adminDao.update(new Admin("3","ghb","123456","正常","2","qwerty"));
//        adminDao.delete("3");
    }

    @Test
    public void cate() {
//        System.out.println(categoryDao.selectTwocount("2"));
//        System.out.println(categoryDao.selectOnecount());


        System.out.println(categoryDao.selectById("4"));
//        System.out.println(categoryDao.selectTwo("4"));

//        List<Category> categories = categoryDao.selectOneByPage(0, 4);
//        for (Category category : categories) {
//            System.out.println(category);
//        }

//        List<Category> categories = categoryDao.selectTwoByPage("1", 0, 2);
//        categories.forEach(a-> System.out.println(a));
//        List<Category> categories = categoryDao.selectByPage(0, 3);
//        categories.forEach(a-> System.out.println(a));
//        List<Category> categories = categoryDao.selectByLevel("2",0,3);
//        List<Category> categories = categoryDao.selectOne();


//        List<Category> categories = categoryDao.selectTwo();
//        categories.forEach(a-> System.out.println(a));
//        categoryDao.insert(new Category("5","C++","1",null));
//        categoryDao.update(new Category("5","C++","2","2"));

//        List<Category> categories = categoryDao.selectAll();
//        categories.forEach(a-> System.out.println(a));
//        System.out.println(categoryDao.selectAll().size());
//        Iterator<Category> iterator = categories.iterator();
//        while (iterator.hasNext()){
//            Category next = iterator.next();
//            if(!next.getLevel().equals("1")){
//                iterator.remove();
//            }
//        }
//        System.out.println(categories.size());


    }

}

package com.lgd.service;

import com.lgd.annotation.AddLog;
import com.lgd.dao.CategoryDao;
import com.lgd.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Resource
    private CategoryDao categoryDao;

    @Override
    public Map<String, Object> selectOneByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);

        //查询总条数

        Integer count =categoryDao.selectOnecount();
        map.put("records",count);

        //计算总页数 总页数=总条数/每页展示的条数
        Integer total = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",total);

        List<Category> categories = categoryDao.selectOneByPage((page - 1) * rows, rows);

        map.put("rows",categories);
        return map;
    }


    public Map<String, Object> selectTwoByPage(String id,Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();


        //设置当前页
        map.put("page",page);

        //查询总条数
        Integer count =categoryDao.selectTwocount(id);

        map.put("records",count);

        //计算总页数 总页数=总条数/每页展示的条数
        Integer total = count%rows==0 ? count/rows : count/rows+1;
        map.put("total",total);

        List<Category> categories = categoryDao.selectTwoByPage(id,(page - 1) * rows, rows);


        map.put("rows",categories);
        return map;
    }

    @AddLog("添加类别")
    @Override
    public void insert(Category category,String parentId) {
        category.setId(UUID.randomUUID().toString());
        if(category.getParentId()==null){
            category.setLevel("1");
            categoryDao.insert(category);
        }else{
            category.setLevel("2");
            category.setParentId(parentId);
            categoryDao.insert(category);
        }

    }

    @AddLog("删除类别")
    @Override
    public void delete(String id) {
        Category category = categoryDao.selectById(id);
        //判断一级类别下是否有二级类别
        if(category.getLevel().equals("1")){
            Category category1 = categoryDao.selectOne(id);
            System.out.println(category1);
            if(category1.getCates().isEmpty())categoryDao.delete(id);
        }
        //判断二级类别下是否有视频
        if(category.getLevel().equals("2")){
            Category category2 = categoryDao.selectTwo(id);
            System.out.println(category2);
            if(category2.getVideos().isEmpty())categoryDao.delete(id);
        }
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }


}

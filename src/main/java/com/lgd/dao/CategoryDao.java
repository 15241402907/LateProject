package com.lgd.dao;

import com.lgd.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {



    List<Category> selectOneByPage(@Param("begin") Integer begin, @Param("end") Integer end);

    List<Category> selectTwoByPage(@Param("id") String id,@Param("begin") Integer begin, @Param("end") Integer end);

    void insert(Category category);

    void delete(String id);

    void update(Category category);

    Integer selectOnecount();

    Integer selectTwocount(@Param("id") String id);


    Category selectOne(String id);


    Category selectTwo(String id);

    Category selectById(String id);






}

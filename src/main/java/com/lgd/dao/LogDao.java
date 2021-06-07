package com.lgd.dao;

import com.lgd.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDao {

    List<Log> selectAll(@Param("begin") Integer begin,@Param("end") Integer end);

    Integer count();

    void insert(Log log);




}

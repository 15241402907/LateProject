package com.lgd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.lgd.dao")
@SpringBootApplication
public class YingxTangljApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxTangljApplication.class, args);
    }

}

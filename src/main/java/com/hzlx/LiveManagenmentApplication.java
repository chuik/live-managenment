package com.hzlx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hzlx.mapper")
public class LiveManagenmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveManagenmentApplication.class, args);
    }

}

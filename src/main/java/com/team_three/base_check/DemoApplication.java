package com.team_three.base_check;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.team_three.base_check.mapper")
public class DemoApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
    }
}


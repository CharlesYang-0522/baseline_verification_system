package com.team_three.base_check.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafShiroConfiguration {

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}

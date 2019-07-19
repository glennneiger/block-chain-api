//package com.hb56.block.eureka.zuul.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author Been
// * @date 2018/12/20
// */
//@Order(0)
//@Configuration
//public class ZuulAuthSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Value("${zuul.routes.auth-server.path}")
//    private String path;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .antMatcher(path)
//                .authorizeRequests()
//                .anyRequest().permitAll();
//
//    }
//
//}

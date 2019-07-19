package com.hb56.client.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author Been
 */
@SpringBootApplication
@ComponentScan("com.hb56")
@MapperScan("com.hb56.client.user.dao")
public class SecurityUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityUserApplication.class, args);
    }
}
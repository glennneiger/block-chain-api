package com.hb56.client.api;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhangjun.huangfu
 * @version 1.0
 * @create 2019-03-15-13:41
 * @company www.harbsoft.com
 */

@SpringBootApplication
@ComponentScan("com.hb56")
@MapperScan("com.hb56.client.api.dao")
@EnableDistributedTransaction
public class ApiClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiClientApplication.class,args);
    }
}
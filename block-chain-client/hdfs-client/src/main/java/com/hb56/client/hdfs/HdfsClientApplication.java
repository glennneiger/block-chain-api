package com.hb56.client.hdfs;

import com.hb56.client.hdfs.configure.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({FileProperties.class})
public class HdfsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdfsClientApplication.class,args);

    }
}

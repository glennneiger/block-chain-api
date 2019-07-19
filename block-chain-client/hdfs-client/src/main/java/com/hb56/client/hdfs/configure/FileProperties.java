package com.hb56.client.hdfs.configure;

import lombok.Data;
import org.apache.hadoop.conf.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hdfs")
@Data
public class FileProperties {
    private String url;
    private String user;

    private Configuration configuration = new Configuration();
}

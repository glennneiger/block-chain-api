package com.hb56.client.hdfs.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cosmos
 */
@Data
public class HdfsFileInfo implements Serializable {
    String fileName;
    String fileExt;
    String path;
    String storedFileName;
    String hash;

}

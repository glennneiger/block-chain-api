import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class HDFSAPP {
    // HDFS文件系统服务器的地址以及端口
    public static final String HDFS_PATH = "hdfs://192.168.248.213:8020";
    // HDFS文件系统的操作对象
    FileSystem fileSystem = null;
    // 配置对象
    Configuration configuration = null;

    /**
     * 创建HDFS目录
     */
    @Test
    public void mkdir()throws Exception{
        // 需要传递一个Path对象
        fileSystem.mkdirs(new Path("/hdfsapi/test"));
    }


    @Test
    public void create() throws Exception {
        // 创建文件
        FSDataOutputStream outputStream = fileSystem.create(new Path("/hdfsapi/test/b.txt"));
        // 写入一些内容到文件中
        outputStream.write("hello hadoop".getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Test
    public void read() throws IOException {
        FSDataInputStream inputStream = fileSystem.open(new Path("/hdfsapi/test/b.txt"));
        byte[] bytes = new byte[100];
        inputStream.read(bytes);
        System.out.println(new String(bytes,"utf-8"));
    }

    // 准备资源
    @Before
    public void setUp() throws Exception {
        configuration = new Configuration();
        // 第一参数是服务器的URI，第二个参数是配置对象，第三个参数是文件系统的用户名
        fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, "admin");
        System.out.println("HDFSAPP.setUp");
    }

    // 释放资源
    @After
    public void tearDown() throws Exception {
        configuration = null;
        fileSystem = null;

        System.out.println("HDFSAPP.tearDown");
    }
}

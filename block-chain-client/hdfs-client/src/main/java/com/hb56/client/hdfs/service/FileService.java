package com.hb56.client.hdfs.service;

import com.hb56.client.hdfs.comm.SHA256;
import com.hb56.client.hdfs.configure.FileProperties;
import com.hb56.client.hdfs.exception.FileException;
import com.hb56.client.hdfs.pojo.HdfsFileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Random;

import org.apache.hadoop.fs.Path;


@Service
@Slf4j
public class FileService {

    private  FileProperties fileProperties;

    @Autowired
    public FileService(FileProperties fileProperties) {
        this.fileProperties = fileProperties;
    }

    /**
     * 存储文件到系统
     *
     * @param file 文件
     * @return 文件名
     */
    public HdfsFileInfo storeFile(MultipartFile file)   {
        // Normalize file name
        HdfsFileInfo hdfsFileInfo= new HdfsFileInfo();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        int point = fileName.lastIndexOf(".");
        hdfsFileInfo.setFileName(fileName);

        try {
            String ext;
            if (point>0){
                ext = fileName.substring(point+1);
            }else{
                ext = "unknow";
            }

            hdfsFileInfo.setFileExt(ext);


            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DateTime dateTime = new DateTime();
            int year = dateTime.getYear();
            int month = dateTime.getMonthOfYear();
            String path = "/"+year+"/"+month+"/";
            hdfsFileInfo.setPath(path);
            String millis = creatFileName();
            String realFileName = path+millis+"."+ext;
            String storedFileName  = new String(Base64.getEncoder().encode(realFileName.getBytes()),"utf-8");

            hdfsFileInfo.setStoredFileName(storedFileName);

            FileSystem fileSystem = FileSystem.get(new URI(fileProperties.getUrl()), fileProperties.getConfiguration(), fileProperties.getUser());
            FSDataOutputStream outputStream = fileSystem.create(new Path(realFileName));
            outputStream.write(file.getBytes());
            String hash = SHA256.bytesToHexString(SHA256.hash(file.getBytes()));
            hdfsFileInfo.setHash(hash);
            outputStream.flush();
            outputStream.close();
            log.info("End: "+System.currentTimeMillis());
            return hdfsFileInfo;
        } catch (IOException  |URISyntaxException| InterruptedException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * 加载文件
     * @param fileName 文件名
     * @return 文件
     */
    public File loadFileAsResource(String fileName) {
        try {
            String storedFileName = new String(Base64.getDecoder().decode(fileName.getBytes()),"utf-8");
            FileSystem fileSystem = FileSystem.get(new URI(fileProperties.getUrl()), fileProperties.getConfiguration(), fileProperties.getUser());
            String Path = System.getProperty("user.dir") + "/"+fileName;
            InputStream is = fileSystem.open(new Path(storedFileName));

            //保存到本地  最后 关闭输入输出流
            IOUtils.copyBytes(is, new FileOutputStream(new File(Path)), 2048, true);
            File resource = new File(Path);
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
            //MalformedURLException |
        } catch ( IOException  |URISyntaxException| InterruptedException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }



    public static String  creatFileName(){
        Random random = new Random();
        //文件夹名字的长度
        //default
        int length  = 10;

        String numstr = "123456789";
        String chastr_b = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;
        String chastr_s = "abcdefghijklmnopqrstuvwxyz";
        String specil = "_";
        String base = numstr+chastr_b+chastr_s+specil;

        //文件夹名的规范文件夹不能包含以下字符：
        //井号 (#)；百分号 (%)；“&”；星号 (*)；竖线 (|)；反斜杠 (\)；冒号(:)；
        //双引号 (")；小于号 (<)；大于号 (>)；问号 (?)；斜杠 (/)；前导或尾随空格 (' ')；这样的空格将被去除；
        //需求是将文件名的大写开头，数字结尾
        StringBuffer sb =  new StringBuffer();

        sb.append(chastr_b.charAt(random.nextInt(chastr_b.length())));
        for(int i =0 ;i <length-2;i++){
            int num = random.nextInt(base.length());
            sb.append(base.charAt(num));
        }
        //追加最后一个数字
        sb.append(numstr.charAt(random.nextInt(numstr.length())));
        return sb.toString();
    }

}

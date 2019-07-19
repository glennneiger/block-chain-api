package com.hb56.client.hdfs.controller;

import com.hb56.client.hdfs.pojo.HdfsFileInfo;
import com.hb56.client.hdfs.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.core.io.Resource;


@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public HdfsFileInfo uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        log.info("Start: " + System.currentTimeMillis());
        return fileService.storeFile(multipartFile);

    }

    @PostMapping("uploadFiles")
    public List<HdfsFileInfo> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @RequestMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        // Load file as Resource
        File resource = fileService.loadFileAsResource(fileName);
        String storedFileName = null;
        String contentType = null;
        try {
            storedFileName = new String(Base64.getDecoder().decode(fileName.getBytes()), "utf-8");
            contentType = request.getServletContext().getMimeType(storedFileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        HttpHeaders headers = new HttpHeaders();
        storedFileName = storedFileName.substring(storedFileName.lastIndexOf("/") + 1);
        headers.setContentDispositionFormData("attachment", storedFileName);
        headers.setContentType(MediaType.parseMediaType(contentType));
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(org.apache.commons.io.FileUtils.readFileToByteArray(resource), headers, HttpStatus.OK);

        resource.delete();
        return responseEntity;


//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
//                .body(resource);
    }

}

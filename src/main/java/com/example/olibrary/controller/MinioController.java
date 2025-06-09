package com.example.olibrary.controller;


import com.example.olibrary.components.MinioComponent;
import com.example.olibrary.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/s3")
public class MinioController {
    @Autowired
    private MinioComponent minioComponent;
    @Autowired
    private AuthUtils authUtils;

    @PostMapping(
            path = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileToMinIO(@RequestParam("file") MultipartFile file) {
        authUtils.isAllowed("POST /s3/upload");
        try {
            InputStream in = new ByteArrayInputStream(file.getBytes());
            String fileName = file.getOriginalFilename();
            minioComponent.putObject(fileName, in);
            return "File uploaded.";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Something wrong.";
    }

    @PostMapping("/download")
    public String downloadFile(@RequestParam("fileName") String fileName) throws Exception {
        authUtils.isAllowed("POST /genres/download");
        return minioComponent.getObject(fileName);
    }
}

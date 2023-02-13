package com.itheima.controller;

import com.itheima.common.OssTemplate;
import com.itheima.common.VodTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {

    @Autowired
    private OssTemplate ossTemplate;

    @Autowired
    private VodTemplate vodTemplate;

    //图片上传
    @RequestMapping("/admin/upload/image")
    public String uploadImage(MultipartFile uploadImage) throws IOException {
        String imgPath=ossTemplate.upload(uploadImage.getOriginalFilename(),uploadImage.getInputStream());
        System.out.println(imgPath);
        return imgPath;
    }

    //影视上传
    @RequestMapping("/admin/upload/video")
    public String uploadVideo(MultipartFile uploadVideo) throws IOException {
        String videoId=vodTemplate.uploadVideo(uploadVideo.getOriginalFilename(),uploadVideo.getInputStream());
        System.out.println(videoId);
        return videoId;
    }
}

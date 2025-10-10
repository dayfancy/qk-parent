package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.management.util.AliYunOSSOperators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: RightSquare
 * @Date: 2025/10/8 20:39
 * @Description: File Upload
 */
@Slf4j
@RestController
public class FileHandlerController {
    @Autowired
    private AliYunOSSOperators oss;
    @PostMapping("/upload")
    public Result upload(@RequestParam("image")MultipartFile image){
        try {
            //Param Checking  null
            Objects.requireNonNull(image);
            Objects.requireNonNull(image.getOriginalFilename());
            String originalFilename = image.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");
            String suffix = originalFilename.substring(index);
            String name = UUID.randomUUID() + suffix;
            String url = oss.upload(image.getBytes(),name);
            log.info("上传成功: {}",url);
            return Result.success(url);
        } catch (Exception e) {
            log.error("上传失败: {}",e.getMessage());
            return Result.error("上传失败");
        }

    }




//    @PostMapping("/upload")
//    public String uploadFile(String username, Integer age, @RequestParam("file") MultipartFile file) throws IOException {
//        log.info("文件的原始名称: {}",file.getOriginalFilename());
//        file.transferTo(new File("F:/" + file.getOriginalFilename()));
//        return "上传成功";
//    }
}

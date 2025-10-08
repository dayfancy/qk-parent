package com.qk.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: RightSquare
 * @Date: 2025/10/8 20:39
 * @Description: File Upload
 */
@Slf4j
@RestController
public class FileHandlerController {
    @PostMapping("/upload")
    public String uploadFile(String username, Integer age, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("文件的原始名称: {}",file.getOriginalFilename());
        file.transferTo(new File("F:/" + file.getOriginalFilename()));
        return "上传成功";
    }
}

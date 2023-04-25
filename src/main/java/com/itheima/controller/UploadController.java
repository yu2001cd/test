package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result getFile(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传{},{},{}",username,age,image);
        String uuid = UUID.randomUUID().toString();
        image.transferTo(new File("C:\\Users\\yu\\Desktop\\web资料\\testimage\\"+uuid+image.getOriginalFilename()));
        return Result.success();
    }
}

package com.yumeng.utils;

import com.yumeng.utils.path_utils.PathUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UtilsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UtilsApplication.class, args);
    }

    @GetMapping("get")
    public void test(){
        System.out.println(PathUtil.getTempPath());
        System.out.println(PathUtil.getResourcePath("/font/simsun.ttc"));
    }
}
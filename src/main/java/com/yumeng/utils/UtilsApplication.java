package com.yumeng.utils;

import com.yumeng.utils.bean_utils.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@SpringBootApplication
@RestController
public class UtilsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UtilsApplication.class, args);
    }

    @PostMapping("test")
    public String test(@Validated @RequestBody Test test){
        return "测试";
    }
}
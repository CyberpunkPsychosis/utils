package com.yumeng.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@RestController
public class UtilsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UtilsApplication.class, args);
    }

    @GetMapping("get")
    public void test(HttpServletResponse response) throws Exception{
        System.out.println("helloWorld");
    }
}
package com.yumeng.utils;

import com.yumeng.utils.file_utils.FileUtils;
import com.yumeng.utils.path_utils.PathUtil;
import com.yumeng.utils.pdf_utils.PdfUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@RestController
public class UtilsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UtilsApplication.class, args);
    }

    @GetMapping("get")
    public void test(HttpServletResponse response) throws Exception{
        InputStream inputStream = PathUtil.getResourceStream("/templates/测试.docx");
        String destPath = PathUtil.getTempDir() + File.separator + "测试.docx";
        Path temp = Paths.get(destPath);
        if (inputStream != null) {
            Files.copy(inputStream, temp);
        }
        String tempPath = PathUtil.getTempDir() + File.separator + "测试.pdf";
        PdfUtil.word2pdf(destPath, tempPath);
        FileInputStream fileInputStream = new FileInputStream(tempPath);
        FileUtils.downloadByStream("测试.pdf", fileInputStream, response);
        Path path = Paths.get(tempPath);
        Files.delete(path);
        Files.delete(temp);
    }
}
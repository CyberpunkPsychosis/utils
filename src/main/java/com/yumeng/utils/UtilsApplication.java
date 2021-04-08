package com.yumeng.utils;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.yumeng.utils.path_utils.PathUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
//        InputStream inputStream = PathUtil.getResourceStream("/templates/测试.docx");
//        String destPath = PathUtil.getTempDir() + File.separator + "测试.docx";
//        Path temp = Paths.get(destPath);
//        if (inputStream != null) {
//            Files.copy(inputStream, temp);
//        }
//        String tempPath = PathUtil.getTempDir() + File.separator + "测试.pdf";
//        PdfUtil.word2pdf(destPath, tempPath);
//        FileInputStream fileInputStream = new FileInputStream(tempPath);
//        FileUtils.downloadByStream("测试.pdf", fileInputStream, response);
//        Path path = Paths.get(tempPath);
//        Files.delete(path);
//        Files.delete(temp);
        OutputStream fos = new FileOutputStream("C:\\Users\\user1\\Desktop\\测试.pdf");
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        InputStream resourceStream = PathUtil.getResourceStream("/font/simsun.ttc");
        Path path = Paths.get(PathUtil.getTempPath(".ttc"));
        Files.copy(resourceStream, path);
        PdfFont font = PdfFontFactory.createFont(path.toFile().getAbsolutePath() + ",0", "utf-8");
        document.add(new Paragraph("中文").setFont(font));
        document.close();
        Files.delete(path);
    }
}
package com.yumeng.utils.pdfExport;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class PdfImportTest {

    @Test
    void contextLoads() {
//        try {
//            WordUtil.generateFile(getWordData(),
//                    "C:\\Users\\user1\\Desktop\\freemarker测试html.ftl",
//                    "C:\\Users\\user1\\Desktop\\freemarker测试html.html"
//                    );
//            PdfUtil.createPdfByPath("C:\\Users\\user1\\Desktop\\freemarker测试html.html",
//                    "C:\\Users\\user1\\Desktop\\freemarker测试pdf.pdf");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        PdfUtil.word2pdf("C:\\Users\\user1\\Desktop\\测试.docx", "C:\\Users\\user1\\Desktop\\测试.pdf");

    }

    /**
     * 获取生成Word文档所需要的数据
     */
    private static Map<String, Object> getWordData() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "用户1");
        return dataMap;
    }
}

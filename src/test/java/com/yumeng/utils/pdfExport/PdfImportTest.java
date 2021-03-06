package com.yumeng.utils.pdfExport;

import com.yumeng.utils.pdf_utils.PdfUtil;
import com.yumeng.utils.word_utils.WordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class PdfImportTest {

    @Test
    void contextLoads() {
        try {
            WordUtil.generateFile(getWordData(),
                    "C:\\Users\\user1\\Desktop\\freemarker测试html.ftl",
                    "C:\\Users\\user1\\Desktop\\freemarker测试html.html"
                    );
            PdfUtil.createPdfByPath("C:\\Users\\user1\\Desktop\\freemarker测试html.html",
                    "C:\\Users\\user1\\Desktop\\freemarker测试pdf.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

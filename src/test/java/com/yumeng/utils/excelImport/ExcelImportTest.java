package com.yumeng.utils.excelImport;

import com.yumeng.utils.excel_utils.ExcelImportUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class ExcelImportTest {

    @Test
    void contextLoads() {
        ExcelImportUtil excelImportUtil = new ExcelImportUtil();
        try {
            excelImportUtil.setFilePath("C:\\Users\\yum\\Desktop\\测试2.xlsx")
                    .setSaveImage(new SaveImageImpl())
                    .read(1, new User(), Collections.singletonList(1), null)
                    .validate().generateErrorMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

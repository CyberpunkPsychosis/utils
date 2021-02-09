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
            excelImportUtil.setFilePath("C:\\Users\\user1\\Desktop\\出库订单模板.xlsx")
                    .setSaveImage(new SaveImageImpl())
                    .read(2, new User(), null, null)
                    .validate().generateErrorMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

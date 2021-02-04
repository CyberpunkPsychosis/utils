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
            excelImportUtil.setFilePath("C:\\Users\\user1\\Desktop\\%E5%87%BA%E5%BA%93%E8%AE%A2%E5%8D%95%E6%A8%A1%E6%9D%BF.xlsx")
                    .setSaveImage(new SaveImageImpl())
                    .read(2, new User(), Collections.singletonList(1), null)
                    .validate().generateErrorMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

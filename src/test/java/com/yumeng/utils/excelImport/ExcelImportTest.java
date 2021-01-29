package com.yumeng.utils.excelImport;

import com.yumeng.utils.excel_utils.ExcelImportUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
class ExcelImportTest {

    @Test
    void contextLoads() {
        ExcelImportUtil excelImportUtil = new ExcelImportUtil();
        try {
            InputStream inputStream1 = new FileInputStream("C:\\Users\\user1\\Desktop\\B2B客户订单模板(1).xls");
            InputStream inputStream2 = new FileInputStream("C:\\Users\\user1\\Desktop\\B2B客户订单模板(1).xls");
            excelImportUtil.setInputStream(inputStream1)
                    .read(10, new OrderBox(), Arrays.asList(2,3), Arrays.asList(10, 23))
                    .setInputStream(inputStream2)
                    .read(excelImportUtil.getIndex(), new OrderGoods(), Collections.singletonList(12), null)
                    .validate().generateErrorMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

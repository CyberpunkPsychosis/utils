package com.yumeng.utils.excelImport;

import com.yumeng.utils.excel_utils.ExcelImportUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
class ExcelImportTest {

    @Test
    void contextLoads() {
        ExcelImportUtil excelImportUtil = new ExcelImportUtil();
        try {
            excelImportUtil.setFilePath("C:\\Users\\user1\\Desktop\\B2B客户订单模板(1).xls")
                    .read(10, new OrderBox(), Arrays.asList(2,3), Arrays.asList(10, 23))
                    .read(excelImportUtil.getIndex(), new OrderGoods(), Collections.singletonList(12), Arrays.asList(24, 25, 26))
                    .validate().generateErrorMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

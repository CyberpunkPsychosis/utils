package com.yumeng.utils;

import com.yumeng.utils.excel_utils.ExcelImageUtil;
import org.apache.poi.ss.usermodel.PictureData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@SpringBootTest
class UtilsApplicationTests {

    @Test
    void contextLoads() {
        try {
            InputStream inputStream = new FileInputStream("C:\\Users\\user1\\Desktop\\报价详情 (1).xlsx");
            List<Map<Integer, Map<Integer, PictureData>>> list = ExcelImageUtil.getSheetPictures(inputStream);
            System.out.println("ok");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

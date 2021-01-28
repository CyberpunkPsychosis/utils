package com.yumeng.utils;

import com.yumeng.utils.excel_utils.ExcelConfig;
import com.yumeng.utils.excel_utils.ExcelParse;
import com.yumeng.utils.excel_valid_utils.ExcelImportUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@SpringBootTest
class UtilsApplicationTests {

    private String TEST = "C:\\Users\\user1\\Desktop\\测试\\test.xlsx";

    private String ceshi = "C:\\Users\\user1\\Desktop\\测试";

    private String moban = "C:\\Users\\user1\\Desktop\\模板\\";

    @Test
    void contextLoads1() {
        Workbook workbook = null;
        File file = new File("C:\\Users\\user1\\Desktop\\一单到底号段 (5).xlsx");
        try {
            String fileName = file.getName();
            InputStream inputStream = new FileInputStream(file);
            if (fileName.toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (fileName.toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Integer, Boolean> map = new HashMap<>();
        map.put(1, true);
        ExcelConfig excelConfig = new ExcelConfig();
        excelConfig.setIgnoreTitle(map);
        ExcelParse excelParse = new ExcelParse(excelConfig);
        List<Map<Integer, Map<Integer, String>>> list = excelParse.resolveExcelWordOnImage(workbook);
        System.out.println(list.toString());
    }

    @Test
    void contextLoads2() {
        ExcelImportUtil excelImportUtil = new ExcelImportUtil();
        try {
            excelImportUtil.setFilePath("C:\\Users\\yum\\Desktop\\B2B客户订单模板(1).xls")
                    .read(10, new OrderBox(), Arrays.asList(2,3), Arrays.asList(10, 23))
                    .read(excelImportUtil.getIndex(), new OrderGoods(), Collections.singletonList(12), null)
                    .validate().generateErrorMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

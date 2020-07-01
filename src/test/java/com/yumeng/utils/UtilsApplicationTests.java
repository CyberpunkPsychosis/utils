package com.yumeng.utils;

import com.yumeng.utils.collection_utils.ListUtil;
import com.yumeng.utils.excel_utils.ExcelConfig;
import com.yumeng.utils.excel_utils.ExcelParse;
import com.yumeng.utils.qrcode_utils.QrCodeUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class UtilsApplicationTests {

    @Test
    void contextLoads() {

        File file = new File("C:\\Users\\user1\\Desktop\\aaa.xlsx");
        if(!file.exists()){
            System.out.println("文件不存在");
            return ;
        }

        FileInputStream fis = null;

        Workbook workBook = null;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workBook = new XSSFWorkbook(fis); // 使用XSSFWorkbook
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Integer, Boolean> ignoreTitle = new HashMap<>();

        ignoreTitle.put(1, true);
        ignoreTitle.put(2, true);
        ignoreTitle.put(3, true);

        Map<Integer, Boolean> map = new HashMap<>();

        map.put(2, true);

        ExcelConfig excelConfig = new ExcelConfig();

        excelConfig.setIgnoreTitle(ignoreTitle);

        excelConfig.setIgnoreCellBlank(map);

        ExcelParse excelParse = new ExcelParse(excelConfig);

        List<Map<Integer, Map<Integer, String >>> list = excelParse.resolveExcelWordOnImage(workBook);

        System.out.println(list);

//        ExcelParse excelParse = new ExcelParse();
//        List<Map<Integer, Map<Integer, XSSFPictureData>>> list = null;
//        try {
//            list = excelParse.getPicturesXlsx(workBook);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println(list);
//        System.out.println(list);
    }

    @Test
    void contextLoads2() {
//        try {
//            QrCodeUtils.encode("weixin://wxpay/bizpayurl?pr=smvdbCD", "G:\\img", true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String filename = "https://yuantongbucket.oss-cn-beijing.aliyuncs.com/qrcode/48194f172c2243a8b3076ffa8845dd98.jpg";
//        String relativePath = filename.substring(filename.lastIndexOf("/",filename.lastIndexOf("/")-1)+1);
//        System.out.println(relativePath);

    }
}

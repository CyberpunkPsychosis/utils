package com.yumeng.utils;

import com.yumeng.utils.excel_utils.ExcelConfig;
import com.yumeng.utils.excel_utils.ExcelParse;
import com.yumeng.utils.excel_valid_utils.RowEntity;
import com.yumeng.utils.excel_valid_utils.RowEntityImpl;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@SpringBootTest
class UtilsApplicationTests {

    @Test
    void contextLoads() {


        List<Map<Integer, Map<Integer, XSSFPictureData>>> mapXlsx = null;
        List<Map<Integer, Map<Short, HSSFPictureData>>> mapXls = null;

        Workbook workbook = null;

        File file = new File("C:\\Users\\user1\\Desktop\\商品备案表格.xlsx");
        try {
//            file = MultipartFileToFile(multiFile);
            String fileName = file.getName();
            InputStream inputStream = new FileInputStream(file);
            if (fileName.toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
                mapXlsx = ExcelParse.getPicturesXlsx(workbook);
            } else if (fileName.toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
                mapXls = ExcelParse.getPicturesXls(workbook);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }






        ExcelConfig excelConfig = new ExcelConfig();

        Map<Integer, Boolean> map = new HashMap<>();

        map.put(1, true);

        excelConfig.setIgnoreTitle(map);

        ExcelParse excelParse = new ExcelParse(excelConfig);

        List<Map<Integer, Map<Integer, String>>> list = excelParse.resolveExcelWordOnImage(workbook);

        Map<Integer, Map<Integer, String>> sheet = list.get(0);

        Iterator<Map.Entry<Integer, Map<Integer, String>>> var1 = sheet.entrySet().iterator();

        List<RowEntity> rowEntityList = new ArrayList<>();
        while (var1.hasNext()){
            Map<Integer, String> row = var1.next().getValue();

            RowEntityImpl rowEntity = new RowEntityImpl(var1.next().getKey());

            Iterator<Map.Entry<Integer, String >> var2 = row.entrySet().iterator();

            while (var2.hasNext()){
                rowEntity.addData(var2.next().getValue());
            }

            rowEntityList.add(rowEntity);
        }


        System.out.println(rowEntityList);

    }



    public static File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        assert fileName != null;

        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

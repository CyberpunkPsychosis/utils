package com.yumeng.utils;

import com.yumeng.utils.excel_utils.ExcelConfig;
import com.yumeng.utils.excel_utils.ExcelParse;
import com.yumeng.utils.excel_valid_utils.*;
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

import static java.util.stream.LongStream.range;


@SpringBootTest
class UtilsApplicationTests {

    @Test
    void contextLoads() {

        List<Map<Integer, Map<Integer, XSSFPictureData>>> mapXlsx = null;
        List<Map<Integer, Map<Short, HSSFPictureData>>> mapXls = null;
        Workbook workbook = null;
        File file = new File("C:\\Users\\user1\\Desktop\\商品备案表格.xlsx");
        try {
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

        List<RowEntity> rowEntityList = new ArrayList<>();

        for (Integer var1: sheet.keySet()) {

            Map<Integer, String> row = sheet.get(var1);

            RowEntityImpl rowEntity = new RowEntityImpl(var1);

            for (Integer var2: row.keySet()) {
                rowEntity.addData(var2, row.get(var2));
            }

            rowEntityList.add(rowEntity);
        }

        if (mapXls != null){
            Map<Integer, Map<Short, HSSFPictureData>> var3 = mapXls.get(0);
            rowEntityList.forEach(row -> {
                if (var3.containsKey(row.getRowNum())){
                    Map<Short, HSSFPictureData> cols = var3.get(row.getRowNum());
                    for (Short i: cols.keySet()) {
                        row.addData(i, "true");
                    }
                }
            });
        }

        if (mapXlsx != null){
            Map<Integer, Map<Integer, XSSFPictureData>> var4 = mapXlsx.get(0);
            rowEntityList.forEach(row -> {
                if (var4.containsKey(row.getRowNum())){
                    Map<Integer, XSSFPictureData> cols = var4.get(row.getRowNum());
                    for (int i: cols.keySet()) {
                        row.addData(i, "true");
                    }
                }
            });
        }

        System.out.println(rowEntityList);

        Validator isNotChineseValidator = new IsNotChineseValidator("商品名称(英文)不能为包含中文");
        Validator isNotNullValidator = new IsNotNullValidator("JanCode 不能为空");
        Validator isImgNotNullValidator = new IsImgNotNullValidator("商品图片不能为空");

        ValidatorsConfig validatorsConfig = new ValidatorsConfig();

        validatorsConfig.addValidator(3, isNotChineseValidator);

        validatorsConfig.addValidator(4, isNotNullValidator);

        validatorsConfig.addValidator(1, isImgNotNullValidator);

        rowEntityList.forEach(row -> {row.initValidators(validatorsConfig.getValidator());});

        rowEntityList.forEach(RowEntity::check);

        Map<Integer, List<String>> errors = new HashMap<>();

        rowEntityList.forEach(row -> {
            if (row.getErrorList().size() != 0){
                errors.put(row.getRowNum(), row.getErrorList());
            }
        });

        System.out.println(errors);
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

    @Test
    void contextLoads2() {
        range(1,3).forEach(x -> {
            System.out.println("aaa");
        });
    }

}

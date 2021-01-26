package com.yumeng.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.yumeng.utils.excel_utils.ExcelConfig;
import com.yumeng.utils.excel_utils.ExcelParse;
import com.yumeng.utils.excel_valid_utils.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.T;
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

    //表格校验示例代码
    @Test
    void contextLoads1() {

        String path = "C:\\Users\\user1\\Desktop\\一单到底号段 (5).xlsx";
        File file = new File(path);
        List<RowEntity> rowEntityList = ExcelValidUtil.createRowEntityList(file);
        //实例化校验器
        Validator isNotChineseValidator = new IsNotChineseValidator("商品名称(英文)不能包含中文");
        Validator isNotNullValidator = new IsNotNullValidator("JanCode 不能为空");
        Validator isImgNotNullValidator = new IsImgNotNullValidator("商品图片不能为空");

        //配置校验器应用的列号（可以重复）
        ValidatorsConfig validatorsConfig = new ValidatorsConfig();
//        validatorsConfig.addValidator(3, isNotChineseValidator);
        validatorsConfig.addValidator(1, isNotNullValidator);
        validatorsConfig.addValidator(2, isNotNullValidator);

//        validatorsConfig.addValidator(1, isImgNotNullValidator);

        //初始化校验器
        rowEntityList.forEach(row -> {row.initValidators(validatorsConfig.getValidator());});
        //检查
        rowEntityList.forEach(RowEntity::check);
        Map<Integer, List<String>> errors = new HashMap<>();
        //获取里面的错误列表和行号
        rowEntityList.forEach(row -> {
            if (row.getErrorList().size() != 0){
                errors.put(row.getRowNum(), row.getErrorList());
            }
        });

        System.out.println(rowEntityList.toString());
        System.out.println(errors.toString());
    }

    @Test
    void contextLoads2() {
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
    void contextLoads3() {
        List<Integer> ignoreRow1 = Arrays.asList(10, 23, 16);
        List<Integer> ignoreRow2 = Arrays.asList(24);
        ExcelImportUtil excelImportUtil = new ExcelImportUtil();
        try {
            excelImportUtil.setFilePath("C:\\Users\\user1\\Desktop\\B2B客户订单模板(1).xls")
                    .read(10, 23, new com.yumeng.utils.Test(), new ArrayList<>(), ignoreRow1)
                    .read(24, 102, new Test1(), new ArrayList<>(), ignoreRow2);
            System.out.println(excelImportUtil.getMap());
            System.out.println("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

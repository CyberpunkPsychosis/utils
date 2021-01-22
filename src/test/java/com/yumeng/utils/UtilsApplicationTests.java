package com.yumeng.utils;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.yumeng.utils.bean_utils.EntityTranslator;
import com.yumeng.utils.bean_utils.Test1;
import com.yumeng.utils.bean_utils.Test2;
import com.yumeng.utils.excel_utils.ExcelConfig;
import com.yumeng.utils.excel_utils.ExcelParse;
import com.yumeng.utils.excel_valid_utils.*;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Test1 test1 = new Test1();
        MethodAccess access = MethodAccess.get(Test1.class);
        long start1 = System.currentTimeMillis();
        int addNameIndex = access.getIndex("setName");
        for (int i = 0; i < 1000000; i++) {
            access.invoke(test1, addNameIndex, "Awesome McLovin");
        }
        System.out.println(System.currentTimeMillis() - start1);


        long start2 = System.currentTimeMillis();
        Method set = null;
        try {
            Test1 doInstance = null;
            try {
                doInstance = test1.getClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            set = test1.getClass().getDeclaredMethod("setName");
//            set.setAccessible(true);
            try {
                for (int i = 0; i < 1000000; i++) {
                    set.invoke(doInstance,"Awesome McLovin");
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException | IllegalAccessException e ) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start2);
    }
}

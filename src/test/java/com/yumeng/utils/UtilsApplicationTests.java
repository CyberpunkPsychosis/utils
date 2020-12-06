package com.yumeng.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelStyleType;
import cn.afterturn.easypoi.util.PoiMergeCellUtil;
import com.yumeng.utils.excel_utils.ExcelConfig;
import com.yumeng.utils.excel_utils.ExcelParse;
import com.yumeng.utils.excel_valid_utils.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.*;

@SpringBootTest
class UtilsApplicationTests {

    private String TEST = "C:\\Users\\yum\\Desktop\\测试\\test.xlsx";

    private String ceshi = "C:\\Users\\yum\\Desktop\\测试";

    private String moban = "C:\\Users\\yum\\Desktop\\模板\\";

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
    void contextLoads3() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                moban + "foreach_insert_merge.xlsx");
        Map<String, Object> map  = new HashMap();
        List<Map> list = new ArrayList<>();
        Map aa = new HashMap() {
            {
                put("id", "id" + 0);
                put("name", "name" + 0);
                put("age", "age" + 0);
                put("sex", "sex" + 0);
            }
        };
        for (int i = 0; i < 10; i++) {
            final int index = i;
            list.add(aa);
        }
        map.put("nums", "你是一只小小鸟");
        map.put("list1", list);
        map.put("list2", list);
        //本来导出是专业那个
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        PoiMergeCellUtil.mergeCells(workbook.getSheetAt(0), 1, 0, 4);
        File savefile = new File(ceshi);
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(TEST);
        workbook.write(fos);
        fos.close();
    }
}

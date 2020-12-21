package com.yumeng.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.util.PoiMergeCellUtil;
import com.yumeng.utils.boxNumberGenerate.Box;
import com.yumeng.utils.excel_utils.ExcelConfig;
import com.yumeng.utils.excel_utils.ExcelParse;
import com.yumeng.utils.excel_valid_utils.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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


    @Test
    void contextLoads4() throws Exception {
        Map<String, Object> map1  = new HashMap();
        map1.put("index", 1);
        map1.put("address", "辽宁省大连市");
        map1.put("name", "董先生");
        map1.put("num", "WEI111701");
        map1.put("length", "40");
        map1.put("width", "32");
        map1.put("height", "27");
        map1.put("weight", "15.30");
        map1.put("total", "30.60");

        Map<String, Object> map2  = new HashMap();
        map1.put("index", 2);
        map2.put("address", "辽宁省大连市");
        map2.put("name", "董先生");
        map2.put("num", "WEI111702");
        map2.put("length", "41");
        map2.put("width", "33");
        map2.put("height", "28");
        map2.put("weight", "15.30");
        map2.put("total", "30.60");
        List<Map> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);

        Map<String, Object> map  = new HashMap();
        map.put("list", list);

        Map<String, Object> temp = new HashMap<>();
        temp.put("total", 30);
        temp.put("a", map);
        TemplateExportParams params = new TemplateExportParams(
                "C:\\Users\\user1\\Desktop\\invoice模板1120 - 副本.xls");
        //本来导出是专业那个
        Workbook workbook = ExcelExportUtil.exportExcel(params, temp);
        PoiMergeCellUtil.mergeCells(workbook.getSheetAt(0), 1, 0, 1);
        PoiMergeCellUtil.mergeCells(workbook.getSheetAt(0), 1, 1, 2);
        File savefile = new File("C:\\Users\\user1\\Desktop\\测试2");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("C:\\Users\\user1\\Desktop\\测试2\\test.xlsx");
        workbook.write(fos);
        fos.close();
    }

    @Test
    void contextLoads5() throws Exception{
        TemplateExportParams params = new TemplateExportParams(
                moban + "foreach_sum.xlsx");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map> list = new ArrayList<Map>();

        for (int i = 0; i < 4; i++) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("index", i + 1);
            map1.put("accountType", "开源项目");
            map1.put("projectName", "EasyPoi" + i + "期");
            map1.put("amountApplied", i * 10000);
            map1.put("approvedAmount", (i + 1) * 10000 - 100);
            list.add(map1);
        }
        map.put("entitylist", list);
        params.setSheetNum(new Integer[]{0,2});
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
//        PoiMergeCellUtil.mergeCells(workbook.getSheetAt(0), 1, 0, 4);
        File savefile = new File(ceshi);
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(TEST);
        workbook.write(fos);
        fos.close();
    }
    @Test
    void contextLoads6() throws Exception{
        Workbook workbook = getWorkBook();
        CellStyle cellStyle = setProperties(workbook);
        Sheet sheet = getSheet(workbook);
        fillOutboundBox(sheet, cellStyle);
        fillgoodsTitle(sheet, cellStyle);
        fillgoods(sheet, cellStyle);
        File savefile = new File("C:\\Users\\user1\\Desktop\\测试2");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("C:\\Users\\user1\\Desktop\\测试2\\test.xlsx");
        workbook.write(fos);
        fos.close();
    }

    private Workbook getWorkBook() throws Exception{
        File file = new File("C:\\Users\\user1\\Desktop\\invoice模板1120 - 副本.xls");
        //获取流
        InputStream inputStream = new FileInputStream(file);
        //POI读取Excel信息
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    private CellStyle setProperties(Workbook workbook) {
        //设置样式对象，这里仅设置了边框属性
        CellStyle cellStyle = workbook.createCellStyle();
        //下边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        //左边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //上边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        //右边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        //字体加粗
        font.setBold(true);
        cellStyle.setFont(font);
        return cellStyle;
    }

    private Sheet getSheet(Workbook workbook) {
        return workbook.getSheetAt(0);
    }

    private void fillOutboundBox(Sheet sheet,CellStyle cellStyle) {
        for (int i = 10; i < 20; i++) {
            Row row = sheet.createRow(i);
            row.setHeight((short) (20 * 24));
            row.createCell(1).setCellValue("辽宁省大连市");
            row.createCell(2).setCellValue("董先生");
            row.createCell(3).setCellValue("WEI111701");
            row.createCell(4).setCellValue("40");
            row.createCell(5).setCellValue("32");
            row.createCell(6).setCellValue("27");
            row.createCell(7).setCellValue("15.30");
            for (int j = 1; j < 8; j++) {
                row.getCell(j).setCellStyle(cellStyle);
            }
        }
        Row row = sheet.createRow(20);
        row.setHeight((short) (20 * 24));
        row.createCell(1).setCellValue("");
        row.createCell(2).setCellValue("");
        row.createCell(3).setCellValue("");
        row.createCell(4).setCellValue("");
        row.createCell(5).setCellValue("");
        row.createCell(6).setCellValue("");
        row.createCell(7).setCellValue("30.60");
        for (int j = 1; j < 8; j++) {
            row.getCell(j).setCellStyle(cellStyle);
        }
    }

    private void fillgoodsTitle(Sheet sheet,CellStyle cellStyle) {
        Row row = sheet.createRow(21);
        row.setHeight((short) (20 * 24));
        row.createCell(0).setCellValue("No");
        row.createCell(1).setCellValue("JAN CODE No.\n" +
                "商品编码");
        row.createCell(2).setCellValue("DESCRIPTION OF GOODS English name\n" +
                "商品英文名称");
        row.createCell(3).setCellValue("DESCRIPTION OF GOODS Chinese name\n" +
                "商品中文名称");
        row.createCell(4).setCellValue("DESCRIPTION OF GOODS Brand\n" +
                "商品品牌");
        row.createCell(5).setCellValue("重量/g");
        row.createCell(6).setCellValue("PCS\n" +
                "数量");
        row.createCell(7).setCellValue("U.PRICE(JPY) \n" +
                "单价(日元)");
        row.createCell(8).setCellValue("AMOUNT (JPY) \n" +
                "总金额(日元)");
        row.createCell(9).setCellValue("商品所在箱号(在外纸箱上用大头笔写上编号)");
        for (int j = 0; j < 10; j++) {
            row.getCell(j).setCellStyle(cellStyle);
        }
    }

    private void fillgoods(Sheet sheet,CellStyle cellStyle){

        Row row = sheet.createRow(22);
        row.setHeight((short) (20 * 24));
        row.createCell(1).setCellValue("辽宁省大连市");

        for (int i = 23; i < 33; i++) {
            Row row1 = sheet.createRow(i);
            row1.setHeight((short) (20 * 24));
            row1.createCell(0).setCellValue("辽宁省大连市");
            row1.createCell(1).setCellValue("董先生");
            row1.createCell(2).setCellValue("WEI111701");
            row1.createCell(3).setCellValue("40");
            row1.createCell(4).setCellValue("32");
            row1.createCell(5).setCellValue("27");
            row1.createCell(6).setCellValue("15.30");
            row1.createCell(7).setCellValue("15.30");
            row1.createCell(8).setCellValue("15.30");
            row1.createCell(9).setCellValue("15.30");
            for (int j = 0; j < 10; j++) {
                row1.getCell(j).setCellStyle(cellStyle);
            }
        }
    }
}

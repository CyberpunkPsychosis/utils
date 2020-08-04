package com.yumeng.utils.excel_valid_utils;

import com.yumeng.utils.excel_utils.ExcelConfig;
import com.yumeng.utils.excel_utils.ExcelParse;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 读取Excel表个里面的数据，如果存在图片，存'true'字符串
 */
public class ExcelValidUtil {

    public static List<RowEntity> createRowEntityList(String path){
        List<Map<Integer, Map<Integer, XSSFPictureData>>> mapXlsx = new ArrayList<>();
        List<Map<Integer, Map<Short, HSSFPictureData>>> mapXls = new ArrayList<>();
        Workbook workbook = null;
        File file = new File(path);
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
        if (mapXls.size() != 0){
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
        if (mapXlsx.size() != 0){
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
        return rowEntityList;
    }
}

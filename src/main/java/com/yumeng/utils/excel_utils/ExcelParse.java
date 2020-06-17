package com.yumeng.utils.excel_utils;

import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel解析工具类（不能处理图片）
 */
public class ExcelParse {

    private ExcelConfig excelConfig;

    public ExcelParse(ExcelConfig excelConfig) {
        this.excelConfig = excelConfig;
    }

    public ExcelParse(){}

    public List<Map<Integer, List<String>>> resolveExcelWord(Workbook workBook){
        List<Map<Integer, List<String>>> list = null;
        try {
            list = dealWorkBook(workBook);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(workBook != null){
                try {
                    workBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    private List<Map<Integer, List<String>>> dealWorkBook(Workbook workBook){
        Map<Integer, Boolean> ignoreTitle = null;
        Map<Integer, Boolean> ignoreCellBlank = null;
        if (this.excelConfig.getIgnoreTitle() != null){
            ignoreTitle = this.excelConfig.getIgnoreTitle();
        }

        if (this.excelConfig.getIgnoreCellBlank() != null){
            ignoreCellBlank = this.excelConfig.getIgnoreCellBlank();
        }

        List<Map<Integer, List<String>>> list = new ArrayList<>();

        int sheetNum = 0;

        for (int i = 0; i < workBook.getNumberOfSheets(); i++){
            Sheet sheet = workBook.getSheetAt(i);

            sheetNum++;

            Map<Integer, List<String>> map = new HashMap<>();

            int rowNum = 1;

            for(Row row : sheet){

                if (ignoreTitle != null){
                    if (ignoreTitle.containsKey(sheetNum)){
                        if (row.getRowNum() == 0){
                            rowNum++;
                            continue;
                        }
                    }
                }

                if (isRowEmpty(row)){
                    rowNum++;
                    continue;
                }

                map.put(rowNum, new ArrayList<>());

                int n = row.getLastCellNum();

                for (int j = 0; j < n ; j++){

                    Cell cell = row.getCell(j);

                    if (cell == null || cell.getCellType() == CellType.BLANK){
                        if (ignoreCellBlank != null){
                            if (ignoreCellBlank.containsKey(j + 1)){
                                continue;
                            }else {
                                map.remove(rowNum);
                                break;
                            }
                        }else {
                            map.remove(rowNum);
                            break;
                        }
                    }

                    switch(cell.getCellType()) {

                        case _NONE:
                        case BOOLEAN:
                        case ERROR:
                            break;
                        case STRING:
                            map.get(rowNum).add(cell.getRichStringCellValue().getString());
                            break;
                        case FORMULA:
                            map.get(rowNum).add(cell.getCellFormula()+"");
                            break;
                        case NUMERIC:
                            map.get(rowNum).add(cell.getNumericCellValue()+"");
                            break;
                    }
                }

                rowNum++;
            }
            list.add(map);
        }
        return list;
    }

    //判断是否是空行
    private boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK)
                return false;
        }
        return true;
    }
}

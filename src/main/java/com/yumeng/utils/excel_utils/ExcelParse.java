package com.yumeng.utils.excel_utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel解析工具类
 */
public class ExcelParse {

    private ExcelConfig excelConfig;

    public ExcelParse(ExcelConfig excelConfig) {
        this.excelConfig = excelConfig;
    }

    public ExcelParse(){}

    //（不能处理图片）（若有图片则所在行数据直接跳过）（返回的是每个sheet的每个行数）（不推荐使用）
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

    //(如果有图片也会读取图片所在的数据，可以设置是否忽略表头，但是无法设置空单元格过滤，空单元格所在的行也会读取)(判断是否单元格为空可以在得到数据之后再做判断，因为返回的是所有数据的行号和列号，可以更加细化，推荐使用)
    public List<Map<Integer, Map<Integer, String>>> resolveExcelWordOnImage(Workbook workBook){
        List<Map<Integer, Map<Integer, String>>> list = null;
        try {
            list = dealWorkBookOnImage(workBook);
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

    private List<Map<Integer, Map<Integer, String>>> dealWorkBookOnImage(Workbook workBook){
        Map<Integer, Boolean> ignoreTitle = null;
        if (this.excelConfig.getIgnoreTitle() != null){
            ignoreTitle = this.excelConfig.getIgnoreTitle();
        }

        List<Map<Integer, Map<Integer, String>>> list = new ArrayList<>();

        int sheetNum = 0;

        for (int i = 0; i < workBook.getNumberOfSheets(); i++){
            Sheet sheet = workBook.getSheetAt(i);

            sheetNum++;

            Map<Integer, Map<Integer, String>> map = new HashMap<>();

            int rowNum = 1;

            int startRowIndex = sheet.getFirstRowNum();
            int endRowIndex = sheet.getLastRowNum();
            for (int j = startRowIndex; j <= endRowIndex; j++ ){

                if (isRowEmpty(sheet.getRow(j))){
                    rowNum++;
                    continue;
                }

                if (ignoreTitle != null){
                    if (ignoreTitle.containsKey(sheetNum)){
                        if (sheet.getRow(j).getRowNum() == 0){
                            rowNum++;
                            continue;
                        }
                    }
                }

                map.put(rowNum, new HashMap<>());
                for(Cell cell : sheet.getRow(j)){
                    switch(cell.getCellType()) {
                        case _NONE:
                        case BLANK:
                        case BOOLEAN:
                        case ERROR:
                            break;
                        case STRING:
                            map.get(rowNum).put(cell.getColumnIndex() + 1, cell.getRichStringCellValue().getString());
                            break;
                        case FORMULA:
                            map.get(rowNum).put(cell.getColumnIndex() + 1, cell.getCellFormula()+"");
                            break;
                        case NUMERIC:
                            map.get(rowNum).put(cell.getColumnIndex() + 1, cell.getNumericCellValue()+"");
                            break;
                    }
                }
                rowNum++;
            }

            if (map.size() != 0){
                list.add(map);
            }
        }
        return list;
    }

    //判断是否是空行
    private boolean isRowEmpty(Row row) {
        if (row == null){
            return true;
        }
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK)
                return false;
        }
        return true;
    }

    //读取Xlsx图片，获取行号和列号
    public static List<Map<Integer, Map<Integer, XSSFPictureData>>> getPicturesXlsx (Workbook workBook) throws IOException {

        List<Map<Integer, Map<Integer, XSSFPictureData>>> ret = new ArrayList<>();

        for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
            XSSFSheet sheet = (XSSFSheet)workBook.getSheetAt(i);

            Map<Integer, Map<Integer, XSSFPictureData>> map = new HashMap<>();
            List<POIXMLDocumentPart> list = sheet.getRelations();

            for (POIXMLDocumentPart part : list) {
                if (part instanceof XSSFDrawing) {
                    XSSFDrawing drawing = (XSSFDrawing) part;
                    List<XSSFShape> shapes = drawing.getShapes();
                    for (XSSFShape shape : shapes) {
                        XSSFPicture picture = (XSSFPicture) shape;
                        XSSFClientAnchor anchor = picture.getPreferredSize();
                        CTMarker marker = anchor.getFrom();
                        int row = marker.getRow();
                        int col = marker.getCol();

                        if (map.containsKey(row+1)){
                            map.get(row+1).put(col+1, picture.getPictureData());
                        }else {
                            Map<Integer, XSSFPictureData> temp = new HashMap<>();
                            temp.put(col+1, picture.getPictureData());
                            map.put(row+1, temp);
                        }
                    }
                }
            }
            if (map.size() != 0){
                ret.add(map);
            }
        }

        return ret;
    }

    //读取Xls图片，获取行号和列号
    public static List<Map<Integer, Map<Short, HSSFPictureData>>> getPicturesXls (Workbook workBook) throws IOException {

        List<Map<Integer, Map<Short, HSSFPictureData>>> ret = new ArrayList<>();

        for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
            HSSFSheet sheet = (HSSFSheet)workBook.getSheetAt(i);

            if (sheet.getDrawingPatriarch() == null){
                continue;
            }

            Map<Integer, Map<Short, HSSFPictureData>> map = new HashMap<>();
            List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
            for (HSSFShape shape : list) {
                if (shape instanceof HSSFPicture) {
                    HSSFPicture picture = (HSSFPicture) shape;
                    HSSFClientAnchor cAnchor = picture.getClientAnchor();
                    int row = cAnchor.getRow1();
                    short col = cAnchor.getCol1();

                    if (map.containsKey(row+1)){
                        map.get(row+1).put((short) (col+1), picture.getPictureData());
                    }else {
                        Map<Short, HSSFPictureData> temp = new HashMap<>();
                        temp.put((short) (col+1), picture.getPictureData());
                        map.put(row+1, temp);
                    }
                }
            }
            if (map.size() != 0){
                ret.add(map);
            }

        }
        return ret;
    }
}

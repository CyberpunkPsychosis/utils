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
public class ExcelImageUtil {

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

package com.yumeng.utils.excel_utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.poi.ss.usermodel.PictureData;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Excel导入工具类
 */
public class ExcelImportUtil {


    /**
     * 读取的数据
     */
    private final Map<String, Object> map = new HashMap<>();
    
    /**
     * 文件路径
     */
    private String filePath;
    
    /**
     * 文件流
     */
    private InputStream inputStream;
    
    /**
     * 错误行信息
     */
    Map<Integer, String> errorMap = new LinkedMap<>();
    
    /**
     * 读取之后,下一次读的索引
     */
    private Integer index;

    /**
     * sheet索引
     */
    private Integer sheetNo = 1;

    public ExcelImportUtil setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public Map<String, Object> getMap() {
        return map;
    }
    
    public ExcelImportUtil setInputStream(InputStream inputStream){
        this.inputStream = inputStream;
        return this;
    }
    
    public Map<Integer, String> getErrorMap() {
        return errorMap;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public ExcelImportUtil setSheetNo(Integer no){
        this.sheetNo = no;
        return this;
    }

    public <G> ExcelImportUtil read(Integer startRow, G g, List<Integer> mergeCell, List<Integer> ignoreRow) throws Exception{
        if (startRow == null){
            throw new Exception("起始行为空");
        }
        if (startRow <= 0){
            throw new Exception("起始行超出范围");
        }

        DataListener<G> dataListener = new DataListener<>();
        dataListener.setStartRow(startRow);
        dataListener.setMergeCell(mergeCell);
        dataListener.setIgnoreRow(ignoreRow);
        dataListener.setMap(this.map);
        dataListener.setExcelImportUtil(this);
        if (this.filePath == null && this.inputStream == null){
            throw new Exception("缺少数据源");
        }
        if (this.filePath == null){
            List<Map<Integer, Map<Integer, PictureData>>> pictures = ExcelImageUtil.getSheetPictures(this.inputStream);
            if (pictures.size() != 0){
                dataListener.setPictureMap(pictures.get(this.sheetNo - 1));
            }
            EasyExcel.read(this.inputStream, g.getClass(), dataListener)
                    .extraRead(CellExtraTypeEnum.MERGE)
                    .sheet(this.sheetNo-1)
                    .headRowNumber(startRow).doRead();
        }else if(this.inputStream == null){
            List<Map<Integer, Map<Integer, PictureData>>> pictures = ExcelImageUtil.getSheetPictures(this.filePath);
            if (pictures.size() != 0){
                dataListener.setPictureMap(pictures.get(this.sheetNo - 1));
            }
            EasyExcel.read(this.filePath, g.getClass(), dataListener)
                    .extraRead(CellExtraTypeEnum.MERGE)
                    .sheet(this.sheetNo-1)
                    .headRowNumber(startRow).doRead();
        }else{
            throw new Exception("数据源不明确");
        }
        return this;
    }

    public ExcelImportUtil validate() throws Exception{
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        for (Map.Entry<String, Object> entry : this.map.entrySet()) {
            Object object = entry.getValue();
            List<Object> list = (List<Object>)object;
            for (Object entity : list) {
                Set<ConstraintViolation<Object>> constraintViolations = validator
                        .validate(entity);
                List<String> messages = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
                Class clazz = entity.getClass();
                try {
                    Field field = clazz.getDeclaredField("error");
                    field.setAccessible(true);
                    field.set(entity,String.join(",", messages));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new Exception("没有error属性");
                }
            }
        }
        return this;
    }

    public ExcelImportUtil generateErrorMap() throws Exception{
        for (Map.Entry<String, Object> entry : this.map.entrySet()) {
            Object object = entry.getValue();
            List<Object> list = (List<Object>)object;
            for (Object entity : list) {
                Class clazz = entity.getClass();
                try {
                    Field error = clazz.getDeclaredField("error");
                    Field index = clazz.getDeclaredField("index");
                    error.setAccessible(true);
                    index.setAccessible(true);
                    String errorMessage = (String) error.get(entity);
                    if (errorMessage != null && !errorMessage.equals("")){
                        this.errorMap.put(Integer.parseInt((String)index.get(entity)), errorMessage);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new Exception("没有error属性和index属性");
                }
            }
        }
        return this;
    }
}

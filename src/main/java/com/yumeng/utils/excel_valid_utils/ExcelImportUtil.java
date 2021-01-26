package com.yumeng.utils.excel_valid_utils;

import com.alibaba.excel.EasyExcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel导入工具类
 */
public class ExcelImportUtil {

    /**
     * 读取的数据
     */
    private final Map<String, Object> map = new HashMap<>();

    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * 文件路径
     */
    private String filePath;

    public ExcelImportUtil setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public <G> ExcelImportUtil read(Integer startRow, Integer endRow, G g, List<Integer> mergeCell, List<Integer> ignoreRow) throws Exception{
        if (startRow <= 0 || endRow <= 0){
            throw new Exception("起始行/结束行超出范围");
        }
        DataListener<G> dataListener = new DataListener();
        dataListener.setEndRow(endRow);
        dataListener.setIgnoreRow(ignoreRow);
        dataListener.setMap(this.map);
        EasyExcel.read(this.filePath, g.getClass(), dataListener)
                .sheet()
                .headRowNumber(startRow).doRead();
        return this;
    }
}

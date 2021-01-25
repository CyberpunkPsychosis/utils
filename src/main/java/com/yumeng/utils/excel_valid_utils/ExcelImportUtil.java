package com.yumeng.utils.excel_valid_utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.util.List;
import java.util.Map;

/**
 * Excel导入工具类
 */
public class ExcelImportUtil<T> {

    /**
     * 读取的数据
     */
    private Map<String, Object> map;

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

    private class DataListener extends AnalysisEventListener<T> {

        private List<T> list;

        private Integer endRow;

        private List<Integer> ignoreRow;

        public void setEndRow(Integer endRow) {
            this.endRow = endRow;
        }

        public void setIgnoreRow(List<Integer> ignoreRow) {
            this.ignoreRow = ignoreRow;
        }

        @Override
        public void invoke(T t, AnalysisContext analysisContext){
            ReadRowHolder readRowHolder = analysisContext.readRowHolder();
            int rowNum = readRowHolder.getRowIndex() + 1;
            MethodAccess access = MethodAccess.get(t.getClass());
            try {
                access.invoke(t, "setIndex", rowNum);
            } catch (IllegalArgumentException ignored) {}
            if (!this.ignoreRow.contains(rowNum) && rowNum != this.endRow){
                
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        }
    }

    public void read(Integer startRow, Integer endRow, Class clazz, List<Integer> mergeCell, List<Integer> ignoreRow) throws Exception{
//        if (startRow <= 0 || endRow <= 0){
//            throw new Exception("起始行/结束行超出范围");
//        }
//        DataListener dataListener = new DataListener();
//        EasyExcel.read(this.filePath, clazz, testDataListener)
//                .sheet()
//                .headRowNumber(startRow-1).doRead();
    }

}

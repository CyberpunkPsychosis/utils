package com.yumeng.utils.excel_valid_utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataListener<G> extends AnalysisEventListener<G> {

    private final List<G> list = new ArrayList<>();

    private Integer endRow;

    private List<Integer> ignoreRow;

    private String className;

    private Map<String, Object> map;

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public void setIgnoreRow(List<Integer> ignoreRow) {
        this.ignoreRow = ignoreRow;
    }

    @Override
    public void invoke(G t, AnalysisContext analysisContext){
        this.className = t.getClass().getSimpleName();
        ReadRowHolder readRowHolder = analysisContext.readRowHolder();
        int rowNum = readRowHolder.getRowIndex() + 1;
        MethodAccess access = MethodAccess.get(t.getClass());
        try {
            access.invoke(t, "setIndex", rowNum);
        } catch (IllegalArgumentException ignored) {}
        if (!this.ignoreRow.contains(rowNum) && rowNum <= this.endRow){
            this.list.add(t);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        map.put(className, this.list);
    }
}
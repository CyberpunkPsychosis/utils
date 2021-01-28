package com.yumeng.utils.excel_valid_utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.collections4.map.LinkedMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataListener<G> extends AnalysisEventListener<G> {

    private final List<G> list = new ArrayList<>();

    private final List<G> allList = new ArrayList<>();

    private Integer startRow;

    private Integer endRow;

    private List<Integer> ignoreRow;

    private List<Integer> mergeCell;

    private String className;

    private Map<String, Object> map;

    private final List<CellExtra> cellExtraList = new ArrayList<>();

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setStartRow(Integer startRow) { this.startRow = startRow; }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public void setIgnoreRow(List<Integer> ignoreRow) {
        this.ignoreRow = ignoreRow;
    }

    public void setMergeCell(List<Integer> mergeCell) { this.mergeCell = mergeCell; }

    @Override
    public void invoke(G g, AnalysisContext analysisContext){
        this.className = g.getClass().getSimpleName();
        ReadRowHolder readRowHolder = analysisContext.readRowHolder();
        int rowNum = readRowHolder.getRowIndex() + 1;
        MethodAccess access = MethodAccess.get(g.getClass());
        try {
            access.invoke(g, "setIndex", String.valueOf(rowNum));
        } catch (IllegalArgumentException ignored) {}
        if (!this.ignoreRow.contains(rowNum) && rowNum <= this.endRow){
            this.list.add(g);
        }
        if (rowNum <= this.endRow){
            this.allList.add(g);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        List<CellExtra> filtered = new ArrayList<>();
        for (CellExtra cellExtra:cellExtraList) {
            if (this.mergeCell.contains(cellExtra.getFirstColumnIndex()+1)){
                filtered.add(cellExtra);
            }
        }
        Map<Integer, List<CellExtra>> filteredMap = filtered.stream()
                .collect(Collectors.groupingBy(CellExtra::getFirstColumnIndex));
        for (Integer key : filteredMap.keySet()) {
            List<CellExtra> var1 = filteredMap.get(key);
            Map<Integer, Integer> var3 = new LinkedMap<>();
            for (CellExtra cellExtra : var1) {
                var3.put(cellExtra.getFirstRowIndex() + 1, cellExtra.getLastRowIndex() - cellExtra.getFirstRowIndex() + 1);
            }
            Map<Integer, List<G>> var2 = new LinkedMap<>();
            for (int i = 0; i < this.allList.size();){
                G var5 = this.allList.get(i);
                Integer index = getIndex(var5);
                if (var3.containsKey(index)) {
                    int num = var3.get(index);
                    int clearNum = 0;
                    for (int k = i; k < i + num; k++){
                        Integer var7 = getIndex(this.allList.get(k));
                        if (this.ignoreRow.contains(var7)){
                            clearNum++;
                        }
                    }
                    num -= clearNum;
                    var3.put(index, num);
                    i += num;
                }else {
                    i++;
                }
            }
            for (int i = 0; i < this.list.size();){
                G var5 = this.list.get(i);
                Integer index = getIndex(var5);
                if (var3.containsKey(index)) {
                    int num = var3.get(index);
                    List<G> var6 = new ArrayList<>();
                    for (int j = i; j < i + num; j++){
                        var6.add(this.list.get(j));
                    }
                    var2.put(index, var6);
                    i += num;
                }else {
                    i++;
                }
            }
            for (Integer var8 : var2.keySet()) {
                List<G> var9 = var2.get(var8);
                String mergeValue = "";
                for (G g : var9) {
                    Class clazz = g.getClass();
                    Field[] fields = clazz.getDeclaredFields();
                    Field hasMergeColKey = null;
                    for (Field field : fields) {
                        if (field.getAnnotation(MergeCol.class) != null){
                            if (field.getAnnotation(MergeCol.class).value() == key + 1){
                                hasMergeColKey = field;
                            }
                        }
                    }
                    if (hasMergeColKey != null){
                        hasMergeColKey.setAccessible(true);
                        try {
                            Object value = hasMergeColKey.get(g);
                            String temp = (String)value;
                            if (temp != null){
                                mergeValue = temp;
                            }else {
                                if (mergeValue.equals("")){
                                    hasMergeColKey.set(g,null);
                                }else {
                                    hasMergeColKey.set(g,mergeValue);
                                }
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        map.put(className, this.list);
    }

    private Integer getIndex(G g){
        MethodAccess access = MethodAccess.get(g.getClass());
        String indexStr = (String) access.invoke(g, "getIndex");
        return Integer.parseInt(indexStr);
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        switch (extra.getType()) {
            case COMMENT:
            case HYPERLINK:
                break;
            case MERGE:
                int rowNum = extra.getLastRowIndex() + 1;
                if (rowNum >= startRow && rowNum < this.endRow){
                    this.cellExtraList.add(extra);
                }
                break;
            default:
        }
    }
}
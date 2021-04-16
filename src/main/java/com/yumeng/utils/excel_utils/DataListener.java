package com.yumeng.utils.excel_utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.poi.ss.usermodel.PictureData;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class DataListener<G> extends AnalysisEventListener<G> {

    private final List<G> list = new ArrayList<>();

    private final List<G> allList = new ArrayList<>();

    private Integer startRow;

    private List<Integer> ignoreRow;

    private List<Integer> mergeCell;

    private String className;

    private Map<String, Object> map;

    private Integer endRow = -1;

    private ExcelImportUtil excelImportUtil;

    private Map<Integer, Map<Integer, PictureData>> pictureMap = new HashMap<>();

    public void setPictureMap(Map<Integer, Map<Integer, PictureData>> pictureMap) {
        this.pictureMap = pictureMap;
    }

    public void setExcelImportUtil(ExcelImportUtil excelImportUtil) {
        this.excelImportUtil = excelImportUtil;
    }

    private final List<CellExtra> cellExtraList = new ArrayList<>();

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setStartRow(Integer startRow) { this.startRow = startRow; }

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

        Class clazz = g.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(EndFlag.class) != null){
                field.setAccessible(true);
                try {
                    String endFlagStr = (String)field.get(g);
                    if (field.getAnnotation(EndFlag.class).value().equals(endFlagStr)){
                        this.endRow = rowNum;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        if (this.ignoreRow != null){
            if (!this.ignoreRow.contains(rowNum) && (endRow == -1 || rowNum < endRow)){
                this.list.add(g);
            }
        }else {
            this.list.add(g);
        }
        if ((endRow == -1 || rowNum < endRow)){
            this.allList.add(g);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        G last = this.allList.get(this.allList.size() - 1);
        MethodAccess access = MethodAccess.get(last.getClass());
        try {
            String index = (String)access.invoke(last, "getIndex");
            this.excelImportUtil.setIndex(Integer.parseInt(index) + 1);
        } catch (IllegalArgumentException ignored) {}
        for (G g : this.list) {
            MethodAccess var10 = MethodAccess.get(g.getClass());
            String index = (String)var10.invoke(g, "getIndex");
            if (this.pictureMap.containsKey(Integer.valueOf(index))){
                Map<Integer, PictureData> var11 = this.pictureMap.get(Integer.valueOf(index));
                Class clazz = g.getClass();
                Field[] fields = clazz.getDeclaredFields();
                List<Field> var12 = new ArrayList<>();
                for (Field field : fields) {
                    if (field.getAnnotation(ImageCol.class) != null){
                        var12.add(field);
                    }
                }
                for (Field field : var12) {
                    field.setAccessible(true);
                    int key = field.getAnnotation(ImageCol.class).value();
                    if (var11.containsKey(key)){
                        String url = this.excelImportUtil.getSaveImage().save(var11.get(key));
                        try {
                            field.set(g,url);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        List<CellExtra> filtered = new ArrayList<>();
        for (CellExtra cellExtra:cellExtraList) {
            if (this.mergeCell != null){
                if (this.mergeCell.contains(cellExtra.getFirstColumnIndex()+1)){
                    filtered.add(cellExtra);
                }
            }
        }
        Map<Integer, List<CellExtra>> filteredMap = filtered.stream()
                .collect(Collectors.groupingBy(CellExtra::getFirstColumnIndex));
        for (Integer key : filteredMap.keySet()) {
            List<CellExtra> var1 = filteredMap.get(key);
            Map<Integer, Integer> var3 = new LinkedHashMap<>();
            for (CellExtra cellExtra : var1) {
                var3.put(cellExtra.getFirstRowIndex() + 1, cellExtra.getLastRowIndex() - cellExtra.getFirstRowIndex() + 1);
            }
            Map<Integer, List<G>> var2 = new LinkedHashMap<>();
            for (int i = 0; i < this.allList.size();){
                G var5 = this.allList.get(i);
                Integer index = getIndex(var5);
                if (var3.containsKey(index)) {
                    int num = var3.get(index);
                    int clearNum = 0;
                    for (int k = i; k < i + num; k++){
                        Integer var7 = getIndex(this.allList.get(k));
                        if (this.ignoreRow != null){
                            if (this.ignoreRow.contains(var7)){
                                clearNum++;
                            }
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
                String mergeValue = null;
                for (G g : var9) {
                    Field hasMergeColKey = getHasMergeColKey(g.getClass(), key + 1);
                    if (hasMergeColKey != null){
                        hasMergeColKey.setAccessible(true);
                        try {
                            Object value = hasMergeColKey.get(g);
                            String temp = (String)value;
                            if (temp != null){
                                mergeValue = temp;
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                for (G g : var9) {
                    Field hasMergeColKey = getHasMergeColKey(g.getClass(), key + 1);
                    if (hasMergeColKey != null){
                        hasMergeColKey.setAccessible(true);
                        try {
                            hasMergeColKey.set(g,mergeValue);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        map.put(className, this.list);
    }

    private Field getHasMergeColKey(Class clazz, Integer key){
        Field[] fields = clazz.getDeclaredFields();
        Field hasMergeColKey = null;
        for (Field field : fields) {
            if (field.getAnnotation(MergeCol.class) != null){
                if (field.getAnnotation(MergeCol.class).value() == key){
                    hasMergeColKey = field;
                }
            }
        }
        return hasMergeColKey;
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
                if (rowNum >= startRow && (endRow == -1 || rowNum < endRow)){
                    this.cellExtraList.add(extra);
                }
                break;
            default:
        }
    }
}
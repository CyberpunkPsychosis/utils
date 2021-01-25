package com.yumeng.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.alibaba.fastjson.JSON;

import java.util.List;

public class TestDataListener extends AnalysisEventListener<Test> {

    private List<Test> list;

    public List<Test> getList() {
        return list;
    }

    public void setList(List<Test> list) {
        this.list = list;
    }

    @Override
    public void invoke(Test test, AnalysisContext analysisContext) {
        ReadRowHolder readRowHolder = analysisContext.readRowHolder();
        System.out.println(readRowHolder.getRowIndex());
        list.add(test);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        for (Test test: list) {
//            System.out.println(test.getPackingNo());
//            System.out.println(test.getLength());
//            System.out.println(test.getWidth());
//            System.out.println(test.getHeight());
//            System.out.println(test.getGW());
//        }
    }

//    @Override
//    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
//        ReadHolder readHolder = context.currentReadHolder();
//        ExcelReadHeadProperty excelReadHeadProperty = readHolder.excelReadHeadProperty();
//        System.out.println(JSON.toJSONString(excelReadHeadProperty.getHeadRowNumber()));
//        System.out.println(JSON.toJSONString(headMap));
//    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        System.out.println("读取到了一条额外信息:{}" + JSON.toJSONString(extra));
        switch (extra.getType()) {
//            case COMMENT:
//                System.out.println("额外信息是批注,在rowIndex:{},columnIndex;{},内容是:{}"
//                                + extra.getRowIndex()
//                                + extra.getColumnIndex()
//                                + extra.getText()
//                        );
//                break;
//            case HYPERLINK:
//                if ("Sheet1!A1".equals(extra.getText())) {
//                    System.out.println("额外信息是超链接,在rowIndex:{},columnIndex;{},内容是:{}"
//                                    + extra.getRowIndex()
//                                    + extra.getColumnIndex()
//                                    + extra.getText());
//                } else if ("Sheet2!A1".equals(extra.getText())) {
//                    System.out.println("额外信息是超链接,而且覆盖了一个区间,在firstRowIndex:{},firstColumnIndex;{},lastRowIndex:{},lastColumnIndex:{},"
//                                    + "内容是:{}"
//                            + extra.getFirstRowIndex()
//                            + extra.getFirstColumnIndex()
//                            + extra.getLastRowIndex()
//                            + extra.getLastColumnIndex()
//                            + extra.getText());
//                } else {
//                    System.out.println("Unknown hyperlink!");
//                }
//                break;
            case MERGE:
//                System.out.println("额外信息是超链接,而且覆盖了一个区间,在firstRowIndex:" + extra.getFirstRowIndex() +
//                        "firstColumnIndex:" +extra.getFirstColumnIndex() +
//                        "lastRowIndex:" +extra.getLastRowIndex() +
//                        "lastColumnIndex:" + extra.getLastColumnIndex()
//                        + extra.getText()
//                );
                break;
            default:
        }
    }
}

package com.yumeng.utils;

import com.alibaba.excel.annotation.ExcelProperty;

public class Test {

    @ExcelProperty("国内地址（必填）*")
    private String one;

    @ExcelProperty("托盘号（不必填）")
    private String two;

    @ExcelProperty("NO. of Packing")
    private String packingNo;

    @ExcelProperty("length(cm)")
    private String length;

    @ExcelProperty("width(cm)")
    private String width;

    @ExcelProperty("height(cm)")
    private String height;

    @ExcelProperty("G.W(KG)")
    private String GW;

    @ExcelProperty("index")
    private String index;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPackingNo() {
        return packingNo;
    }

    public void setPackingNo(String packingNo) {
        this.packingNo = packingNo;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGW() {
        return GW;
    }

    public void setGW(String GW) {
        this.GW = GW;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }
}
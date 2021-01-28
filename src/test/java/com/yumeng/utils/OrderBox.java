package com.yumeng.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import com.yumeng.utils.excel_valid_utils.EndFlag;
import com.yumeng.utils.excel_valid_utils.MergeCol;

import javax.validation.constraints.NotNull;

public class OrderBox {

    @ExcelProperty(index = 1)
    @MergeCol(2)
    @NotNull(message = "不为空")
    private String homeAddress;

    @ExcelProperty(index = 2)
    @MergeCol(3)
    @NotNull(message = "不为空")
    private String number;

    @ExcelProperty(index = 3)
    @EndFlag("DESCRIPTION OF GOODS Chinese name\n" +
            "商品中文名称")
    @NotNull(message = "不为空")
    private String packingNo;

    @ExcelProperty(index = 4)
    @NotNull(message = "不为空")
    private String length;

    @ExcelProperty(index = 5)
    @NotNull(message = "不为空")
    private String width;

    @ExcelProperty(index = 6)
    @NotNull(message = "不为空")
    private String height;

    @ExcelProperty(index = 7)
    @NotNull(message = "不为空")
    private String GW;

    private String index;

    private String error;

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

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
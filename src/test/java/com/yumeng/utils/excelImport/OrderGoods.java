package com.yumeng.utils.excelImport;

import com.alibaba.excel.annotation.ExcelProperty;
import com.yumeng.utils.excel_utils.MergeCol;

import javax.validation.constraints.NotNull;

public class OrderGoods {

    @ExcelProperty(index = 1)
    @NotNull(message = "不为空")
    public String janCode;
    @ExcelProperty(index = 2)
    @NotNull(message = "不为空")
    public String goodsNameEn;
    @ExcelProperty(index = 3)
    @NotNull(message = "不为空")
    public String goodsNameCh;
    @ExcelProperty(index = 5)
    @NotNull(message = "不为空")
    public String brand;
    @ExcelProperty(index = 7)
    @NotNull(message = "不为空")
    public String goodsNumber;
    @ExcelProperty(index = 8)
    @NotNull(message = "不为空")
    public String priceJp;
    @ExcelProperty(index = 9)
    @NotNull(message = "不为空")
    public String totalPriceJp;
    @ExcelProperty(index = 10)
    @NotNull(message = "不为空")
    public String goodsType;
    @ExcelProperty(index = 11)
    @MergeCol(12)
    @NotNull(message = "不为空")
    public String boxNumber;

    private String index;

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getJanCode() {
        return janCode;
    }

    public void setJanCode(String janCode) {
        this.janCode = janCode;
    }

    public String getGoodsNameEn() {
        return goodsNameEn;
    }

    public void setGoodsNameEn(String goodsNameEn) {
        this.goodsNameEn = goodsNameEn;
    }

    public String getGoodsNameCh() {
        return goodsNameCh;
    }

    public void setGoodsNameCh(String goodsNameCh) {
        this.goodsNameCh = goodsNameCh;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getPriceJp() {
        return priceJp;
    }

    public void setPriceJp(String priceJp) {
        this.priceJp = priceJp;
    }

    public String getTotalPriceJp() {
        return totalPriceJp;
    }

    public void setTotalPriceJp(String totalPriceJp) {
        this.totalPriceJp = totalPriceJp;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }
}

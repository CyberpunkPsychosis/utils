package com.yumeng.utils.excel_valid_utils;

import java.util.ArrayList;
import java.util.List;

public class RowEntityImpl implements RowEntity{

    private int rowNum;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public RowEntityImpl(int rowNum) {
        this.rowNum = rowNum;
    }

    List<String> list = new ArrayList<>();

    @Override
    public List<String> getErrorList() {
        return null;
    }

    public void addData(String value){
        this.list.add(value);
    }

    public String getData(int index){
        return this.list.get(index);
    }

    List<Validator> validators = new ArrayList<>();

    @Override
    public void initValidators(List<Validator> list) {
        this.validators = list;
    }

    @Override
    public void check() {

    }
}

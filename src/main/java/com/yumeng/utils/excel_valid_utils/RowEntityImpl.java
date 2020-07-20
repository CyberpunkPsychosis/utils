package com.yumeng.utils.excel_valid_utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowEntityImpl implements RowEntity{

    private int rowNum;
    public int getRowNum() { return rowNum; }
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }
    public RowEntityImpl(int rowNum) {
        this.rowNum = rowNum;
    }

    List<String> errors = new ArrayList<>();
    @Override
    public List<String> getErrorList() {
        return this.errors;
    }

    Map<Integer, String> map = new HashMap<>();
    public void addData(int i, String value){
        this.map.put(i, value);
    }

    Map<Integer, List<Validator>> validators = new HashMap<>();
    @Override
    public void initValidators(Map<Integer, List<Validator>> validators) {
        this.validators = validators;
    }
    @Override
    public void check() {
        for (Integer var1: this.validators.keySet()) {
            this.errors.addAll(valid(this.map.getOrDefault(var1, null), var1));
        }
    }
    private List<String> valid(String value, Integer key){
        List<Validator> validators = this.validators.get(key);
        List<String> errors = new ArrayList<>();
        for (Validator validator: validators) {
            String error = validator.error(value);
            if (error != null){
                errors.add(error);
            }
        }
        return errors;
    }
}

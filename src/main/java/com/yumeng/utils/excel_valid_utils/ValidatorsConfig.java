package com.yumeng.utils.excel_valid_utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatorsConfig {

    private Map<Integer, List<Validator>> map = new HashMap<>();

    public ValidatorsConfig(Map<Integer, List<Validator>> map) {
        this.map = map;
    }

    public ValidatorsConfig(){}

    public void addValidator(int i, Validator validator){
        if (!this.map.containsKey(i)){
            List<Validator> list = new ArrayList<>();
            list.add(validator);
            this.map.put(i, list);
        }else {
            this.map.get(i).add(validator);
        }
    }

    public Map<Integer, List<Validator>> getValidator() {
        return map;
    }
}

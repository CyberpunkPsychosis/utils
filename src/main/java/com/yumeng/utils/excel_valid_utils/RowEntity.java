package com.yumeng.utils.excel_valid_utils;

import java.util.List;
import java.util.Map;

public interface RowEntity {

    int getRowNum();

    List<String> getErrorList();

    void initValidators(Map<Integer, List<Validator>> map);

    void check();
}

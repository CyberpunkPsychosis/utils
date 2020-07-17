package com.yumeng.utils.excel_valid_utils;

import java.util.List;

public interface RowEntity {

    List<String> getErrorList();

    void initValidators(List<Validator> list);

    void check();
}

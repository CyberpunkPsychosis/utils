package com.yumeng.utils.excel_valid_utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorImpl implements Validator{

    private String message;

    public ValidatorImpl(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String error(String value) {
        if (isContainChinese(value)){
            return this.message;
        }
        return null;
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        return m.find();
    }
}

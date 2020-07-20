package com.yumeng.utils.excel_valid_utils;

public class IsNotNullValidator implements Validator{

    private String message;

    public IsNotNullValidator(String message) {
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
        if (value == null || (value != null && value.equals(""))){
            return this.message;
        }
        return null;
    }
}

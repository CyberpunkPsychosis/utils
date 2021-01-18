package com.yumeng.utils.bean_utils;

import javax.validation.constraints.NotNull;

public class Test3 {

    @NotNull(message = "不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

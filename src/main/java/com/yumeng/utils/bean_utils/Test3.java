package com.yumeng.utils.bean_utils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Test3 {

    @NotNull(message = "不能为空")
    private String name;

    @Valid
    List<Test4> test4;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Test4> getTest4() {
        return test4;
    }

    public void setTest4(List<Test4> test4) {
        this.test4 = test4;
    }
}
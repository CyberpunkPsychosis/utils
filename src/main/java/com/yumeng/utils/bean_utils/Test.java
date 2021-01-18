package com.yumeng.utils.bean_utils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Test {

    @NotNull(message = "用户名不能为空")
    private String name;

    @Valid
    List<Test1> test1;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Test1> getTest1() {
        return test1;
    }

    public void setTest1(List<Test1> test1) {
        this.test1 = test1;
    }
}

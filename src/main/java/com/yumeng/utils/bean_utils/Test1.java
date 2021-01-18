package com.yumeng.utils.bean_utils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Test1 {

    @NotNull(message = "不能为空")
    private String name;

    @Valid
    List<Test2> test2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Test2> getTest2() {
        return test2;
    }

    public void setTest2(List<Test2> test2) {
        this.test2 = test2;
    }
}

package com.yumeng.utils.bean_utils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Test2 {

    @NotNull(message = "不能为空")
    private String name;

    @Valid
    List<Test3> test3;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Test3> getTest3() {
        return test3;
    }

    public void setTest3(List<Test3> test3) {
        this.test3 = test3;
    }
}

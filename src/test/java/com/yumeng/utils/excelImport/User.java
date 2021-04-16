package com.yumeng.utils.excelImport;

import com.alibaba.excel.annotation.ExcelProperty;

import javax.validation.constraints.NotNull;

public class User {

    @ExcelProperty(index = 0)
//    @ImageCol(1)
//    @MergeCol(1)
    private String headUrl;

    @NotNull(message = "缺少姓名")
    @ExcelProperty(index = 1)
    private String name;

    @NotNull(message = "缺少年龄")
    @ExcelProperty(index = 2)
    private String age;

    @NotNull(message = "缺少性别")
    @ExcelProperty(index = 3)
    private String sex;

    private String index;

    private String error;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

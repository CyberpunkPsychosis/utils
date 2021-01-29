package com.yumeng.utils.excel_utils;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MergeCol {
    int value() default 0;
}

package com.yumeng.utils.string_utils;

/**
 * 字符串转换器
 */
public final class StringTranslator {

    /**
     * 首字母大写
     * @param word 原字符串
     * @return 新字符串
     */
    public static String capitalize(String word){
        return word.substring(0, 1).toUpperCase()+word.substring(1);
    }


}

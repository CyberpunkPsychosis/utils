package com.yumeng.utils.file_utils;

import java.io.InputStream;


/**
 * 用于QrCodeUtils工具类
 */
public class FileUtils {

    /**
     * 获取指定文件的输入流
     *
     * @param logoPath 文件的路径
     * @return
     */
    public static InputStream getResourceAsStream(String logoPath) {
        return FileUtils.class.getResourceAsStream(logoPath);
    }
}


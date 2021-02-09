package com.yumeng.utils.image_utils;

import cn.hutool.core.util.StrUtil;
import sun.misc.BASE64Encoder;

import java.io.*;

public class ImageUtil {

    /**
     * 将图片内容转换成Base64编码的字符串
     * @param imageFile 图片文件的全路径名称
     * @return 转换成Base64编码的图片内容字符串
     */
    public static String getImageBase64String(String imageFile) {
        if (StrUtil.hasBlank(imageFile)) {
            return "";
        }
        File file = new File(imageFile);
        if (!file.exists()) {
            return "";
        }
        try {
            return getImageBase64String(new FileInputStream(imageFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getImageBase64String(InputStream inputStream){
        byte[] data = null;
        try {
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}

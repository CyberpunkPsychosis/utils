package com.yumeng.utils.file_utils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class FileUtils {

    public static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void downloadByStream(String fileName, InputStream inputStream, HttpServletResponse response) {
        try {
            //判断浏览器代理并分别设置响应给浏览器的编码格式
            String finalFileName = URLEncoder.encode(fileName, "UTF8");//其他浏览器
            //设置HTTP响应头
            response.reset();//重置 响应头
            response.setContentType("application/octet-stream");//告知浏览器下载文件，而不是直接打开，浏览器默认为打开
            response.addHeader("Content-Disposition", "attachment;filename=" + finalFileName);//下载文件的名称
            response.setHeader("Cache-Control", "No-cache");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("filename", finalFileName);

            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inputStream.close();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

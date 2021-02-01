package com.yumeng.utils.word_utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class WordUtil {
    /**
     * 使用FreeMarker自动生成Word文档
     * @param dataMap 生成Word文档所需要的数据
     * @param sourceFtlPath 原Ftl模板路径
     * @param destDocPath 目标doc路径
     */
    public static void generateFile(Map<String, Object> dataMap, String sourceFtlPath, String destDocPath) throws Exception {
        Template template = getTemplate(sourceFtlPath);
        // 创建一个Word文档的输出流
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(destDocPath)), StandardCharsets.UTF_8));
        //FreeMarker使用Word模板和数据生成Word文档
        template.process(dataMap, out);
        out.flush();
        out.close();
    }

    public static byte[] generateByte(Map<String, Object> dataMap, String sourceFtlPath) throws Exception {
        Template template = getTemplate(sourceFtlPath);
        // 创建一个Word文档的输出流
        Writer out = new StringWriter();
        //FreeMarker使用Word模板和数据生成Word文档
        template.process(dataMap, out);
        out.flush();
        byte[] temp = out.toString().getBytes();
        out.close();
        return temp;
    }

    private static Template getTemplate(String sourceFtlPath) throws Exception{
        // 设置FreeMarker的版本和编码格式
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");
        // 设置FreeMarker生成Word文档所需要的模板的路径
        configuration.setDirectoryForTemplateLoading(new File(sourceFtlPath.substring(0, sourceFtlPath.lastIndexOf(File.separator)+1)));
        // 设置FreeMarker生成Word文档所需要的模板
        return configuration.getTemplate(sourceFtlPath.substring(sourceFtlPath.lastIndexOf(File.separator)+1), "UTF-8");
    }
}

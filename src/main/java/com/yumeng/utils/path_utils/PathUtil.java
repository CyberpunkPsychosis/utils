package com.yumeng.utils.path_utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description 路径工具类
 * @Author ym <liu_hao_cheng@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/3/11 15:53
 */
public class PathUtil {

    private static final String DEFAULT_TEMP = "temp";

    private final static String SYSTEM_PATH = System.getProperty("user.dir");

    static {
        String defaultPathDir = SYSTEM_PATH + File.separator + DEFAULT_TEMP;
        Path path = Paths.get(defaultPathDir);
        if (!Files.exists(path)){
            try {
                Files.createDirectory(path);
            } catch (IOException ignored) {
            }
        }
    }

    public static String getTempPath(){
        return SYSTEM_PATH + File.separator + DEFAULT_TEMP;
    }

    public static String getResourcePath(String path){
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        try {
            return resourceLoader.getResource(path).getURL().getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

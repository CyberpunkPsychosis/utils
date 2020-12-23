package com.yumeng.utils.bean_utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 实体转换工具1.0
 * 主要用于DTO与数据实体的相互转换
 */
public final class EntityTranslator {


    /**
     * @param source 原实体对象
     * @param targetClass 目标对象的Class
     * @param map 实体之间的映射配置（当两个实体之间属性名不想同时，进行映射配置）
     * @param <Target> 目标泛型
     * @return 返回目标类型
     */
    public static <Target>Target translate(Object source, Class<Target> targetClass, Map<String, String> map){
        try {

            //如果实体或者DTO为空，返回空
            if(source==null || targetClass==null){
                return null;
            }

            //创建目标实例
            Target doInstance = targetClass.newInstance();

            //如果映射不为空，则遍历寻找
            if (map != null){
                for (String key:
                        map.keySet()) {
                    try {

                        //获取原对象Get方法，获取值
                        String getMethodName = StringUtils.capitalize(key);
                        Method get = source.getClass().getMethod("get" + getMethodName);
                        String value = (String) get.invoke(source);

                        //获取目标对象Set方法，用原对象的值赋值
                        String setMethodName = StringUtils.capitalize(key);
                        Method set = targetClass.getDeclaredMethod("set" + setMethodName, targetClass.getDeclaredField(map.get(key)).getType());
                        set.invoke(doInstance,value);

                    } catch (NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }

            //拷贝相同属性名的值
            BeanUtils.copyProperties(source, doInstance);
            //返回目标实例
            return doInstance;

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.yumeng.utils.collection_utils;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class ListUtil {


    /**
     * 根据list里的对象的某个属性去重
     * 用法示例list.stream().filter(ListUtil.distinctByAttr(A::getName)).collect(Collectors.toList());
     * @param keyExtractor A::getName
     * @param <T> 返回值
     * @return
     */
    public static <T> Predicate<T> distinctByAttr(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 基础字段去重，例如String，Integer
     * @param list
     * @return
     */
    public static List<?> distinctBasicList(List<?> list){
        return list.stream().distinct().collect(Collectors.toList());
    }
}

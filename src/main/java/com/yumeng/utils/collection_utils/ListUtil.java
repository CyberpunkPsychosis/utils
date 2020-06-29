package com.yumeng.utils.collection_utils;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ListUtil {


    /**
     * 根据list里的对象的某个属性去重
     * 用法示例list.stream().filter(ListUtil.distinctByKey(A::getName)).collect(Collectors.toList());
     * @param keyExtractor A::getName
     * @param <T> 返回值
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}

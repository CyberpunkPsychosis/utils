package com.yumeng.utils.testExcetion;

import org.apache.commons.collections4.map.LinkedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ExceptionHandle {

    private final static Logger logger= LoggerFactory.getLogger(ExceptionHandle.class);

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> exceptionHandler(MethodArgumentNotValidException e)
    {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> fieldList = getBigError(fieldErrors);
        Map<String, Object> map = getBaseStructure(fieldList);
        return map;
    }

    private List<String> getBigError(List<FieldError> fieldErrors){
        List<String> bigList = new ArrayList<>();
        for (FieldError fieldError:fieldErrors) {
            String field = fieldError.getField();
            String[] fieldArray = field.split("\\.");
            List<String> fieldList = new ArrayList<>(Arrays.asList(fieldArray));
            fieldList.add(fieldError.getDefaultMessage());
            if (fieldList.size() >= bigList.size()){
                bigList = fieldList;
            }
        }
        return bigList;
    }

    private Map<String, Object> getBaseStructure(List<String> fieldList){
        Map<String, Object> result = new LinkedMap<>();
        String fieldName = fieldList.get(fieldList.size()-2);
        int listSize = fieldList.size() - 2;
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new LinkedMap<>();
        map.put(fieldName, fieldList.get(fieldList.size()-1));
        list.add(map);
        List<Map<String, Object>> var3 = new ArrayList<>();
        for (int i = listSize - 1 ; i >= 0; i--) {
            if (i == listSize - 1){
                continue;
            }
            if (i == listSize - 2){
                List<Map<String, Object>> var1 = new ArrayList<>();
                Map<String, Object> var2 = new LinkedMap<>();
                String last = fieldList.get(i + 1);
                String listName = last.substring(0, last.indexOf("["));
                var2.put(listName, list);
                var1.add(var2);
                var3 = var1;
            }else {
                List<Map<String, Object>> var1 = new ArrayList<>();
                Map<String, Object> var2 = new LinkedMap<>();
                String last = fieldList.get(i + 1);
                String listName = last.substring(0, last.indexOf("["));
                var2.put(listName, var3);
                var1.add(var2);
                var3 = var1;
            }
        }
        if (fieldList.size() == 2){
            result.put(fieldList.get(0), fieldList.get(1));
        }else {
            String first = fieldList.get(0);
            String firstName = first.substring(0, first.indexOf("["));
            result.put(firstName, var3);
        }
        return result;
    }
}

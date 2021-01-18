package com.yumeng.utils.testExcetion;

import org.apache.commons.lang3.StringUtils;
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
    public String exceptionHandler(MethodArgumentNotValidException e)
    {
        System.out.println(e.getBindingResult().getTarget());
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        getErrMap(fieldErrors);
        for (FieldError fieldError:fieldErrors) {
            System.out.println(fieldError.getField());
            System.out.println(fieldError.getRejectedValue());
            System.out.println(fieldError.getDefaultMessage());
            System.out.println(fieldError.getCode());
        }
        return Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
    }


    public void getErrMap(List<FieldError> fieldErrors){
        Map<Integer, Map<String, Object>> saveErrorMap = new TreeMap<>();
        for (FieldError fieldError:fieldErrors) {
            String field = fieldError.getField();
            String[] fieldArray = field.split("\\.");
            if (saveErrorMap.containsKey(fieldArray.length)){
                int key = fieldArray.length;
                if (key == 1){
                    saveErrorMap.get(key).put(fieldArray[0], fieldError.getDefaultMessage());
                }else {
                    List<String> lastTwoList = null;
                    if (fieldArray.length == 2){
                        lastTwoList = Arrays.asList(fieldArray[fieldArray.length-2], fieldArray[fieldArray.length-1], "###[-1]");
                    }else {
                        lastTwoList = Arrays.asList(fieldArray[fieldArray.length-2], fieldArray[fieldArray.length-1], fieldArray[fieldArray.length-3]);
                    }
                    String list = lastTwoList.get(0);
                    String listName = list.substring(0, list.indexOf("["));
                    String sub = list.substring(list.indexOf("["));
                    sub = StringUtils.strip(sub, "[]");
                    String fieldName = lastTwoList.get(1);

                    String parentList = lastTwoList.get(2);
                    String parentSub = parentList.substring(parentList.indexOf("["));
                    parentSub = StringUtils.strip(parentSub, "[]");

                    Map<String, Object> listMap = saveErrorMap.get(key);
                    Map<Integer, List<Map<String, Object>>> indexSubMap = (Map<Integer, List<Map<String, Object>>>) listMap.get(listName);
                    List<Map<String, Object>> subList = indexSubMap.get(Integer.parseInt(parentSub));
                    if (subList == null){
                        List<Map<String, Object>> subTempList = new ArrayList<>();
                        for (int i = 0; i < Integer.parseInt(sub); i++){
                            Map<String, Object> map = new HashMap<>(16);
                            map.put(fieldName, "");
                            subTempList.add(map);
                        }
                        Map<String, Object> map = new HashMap<>(16);
                        map.put(fieldName, fieldError.getDefaultMessage());
                        subTempList.add(map);
                        indexSubMap.put(Integer.parseInt(parentSub), subTempList);
                    }else {
                        if (subList.size() >= Integer.parseInt(sub) + 1){
                            for (int i = 0; i < subList.size(); i++){
                                if (i == Integer.parseInt(sub)){
                                    subList.get(i).put(fieldName, fieldError.getDefaultMessage());
                                }
                            }
                        }else {
                            int lastSub = subList.size();
                            for (int i = lastSub; i < Integer.parseInt(sub); i++){
                                Map<String, Object> map = new HashMap<>(16);
                                map.put(fieldName, "");
                                subList.add(map);
                            }
                            Map<String, Object> map = new HashMap<>(16);
                            map.put(fieldName, fieldError.getDefaultMessage());
                            subList.add(map);
                        }
                    }
                }
            }else {
                if (fieldArray.length == 1){
                    Map<String, Object> map = new HashMap<>(16);
                    map.put(fieldArray[0], fieldError.getDefaultMessage());
                    saveErrorMap.put(fieldArray.length, map);
                }else {
                    List<String> lastTwoList = null;
                    if (fieldArray.length == 2){
                        lastTwoList = Arrays.asList(fieldArray[fieldArray.length-2], fieldArray[fieldArray.length-1], "###[-1]");
                    }else {
                        lastTwoList = Arrays.asList(fieldArray[fieldArray.length-2], fieldArray[fieldArray.length-1], fieldArray[fieldArray.length-3]);
                    }
                    String list = lastTwoList.get(0);
                    String listName = list.substring(0, list.indexOf("["));
                    String sub = list.substring(list.indexOf("["));
                    sub = StringUtils.strip(sub, "[]");

                    String parentList = lastTwoList.get(2);
                    String parentSub = parentList.substring(parentList.indexOf("["));
                    parentSub = StringUtils.strip(parentSub, "[]");

                    String fieldName = lastTwoList.get(1);
                    List<Map<String, Object>> subList = new ArrayList<>();
                    for (int i = 0; i < Integer.parseInt(sub); i++){
                        Map<String, Object> map = new HashMap<>(16);
                        map.put(fieldName, "");
                        subList.add(map);
                    }
                    Map<String, Object> map = new HashMap<>(16);
                    map.put(fieldName, fieldError.getDefaultMessage());
                    subList.add(map);
                    Map<String, Object> listMap = new HashMap<>(16);
                    Map<Integer, List<Map<String, Object>>> indexSubMap = new HashMap<>();
                    indexSubMap.put(Integer.parseInt(parentSub), subList);
                    listMap.put(listName, indexSubMap);
                    saveErrorMap.put(fieldArray.length, listMap);
                }
            }
        }
        System.out.println(saveErrorMap);
        List<Integer> keyList = new ArrayList<>();
        for (Integer key: saveErrorMap.keySet()) {
            keyList.add(key);
        }
        Collections.reverse(keyList);

    }

    public static void main(String[] args) {

    }
}

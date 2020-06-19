package com.yumeng.utils.bean_utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回实体类
 */
public class UnitaryEntity {

    private int statusCode;

    private String notice;

    private Map<String, Object> message = new HashMap<>();

    public UnitaryEntity addStatus(int statusCode, String notice) {
        this.statusCode = statusCode;
        this.notice = notice;
        return this;
    }

    public UnitaryEntity addMessage(String flag, Object entity) {
        this.message.put(flag, entity);
        return this;
    }

    public Map<String, Object> getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getNotice() {
        return notice;
    }
}

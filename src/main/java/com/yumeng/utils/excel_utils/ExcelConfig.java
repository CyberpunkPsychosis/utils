package com.yumeng.utils.excel_utils;


import java.util.Map;

/**
 * Excel解析配置类
 * ignoreTitle 设置是否忽略表头（如果忽略则不读取）
 * ignoreCellBlank 设置是否读取空单元格所在的行（默认不读取，如果设置则读取）
 */
public class ExcelConfig {

    private Map<Integer, Boolean> ignoreTitle = null;

    private Map<Integer, Boolean> ignoreCellBlank = null;

    public Map<Integer, Boolean> getIgnoreTitle() {
        return ignoreTitle;
    }

    public void setIgnoreTitle(Map<Integer, Boolean> ignoreTitle) {
        this.ignoreTitle = ignoreTitle;
    }

    public Map<Integer, Boolean> getIgnoreCellBlank() {
        return ignoreCellBlank;
    }

    public void setIgnoreCellBlank(Map<Integer, Boolean> ignoreCellBlank) {
        this.ignoreCellBlank = ignoreCellBlank;
    }
}

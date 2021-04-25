package com.yumeng.utils.excelExport;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel导出数据内容单元格合并规则
 */
public class ExcelFileCellMergeStrategy implements CellWriteHandler {
    /**
     * 合并列的范围索引
     */
    private int[] mergeColumnIndex;
    /**
     * 合并起始行索引
     */
    private int mergeRowIndex;
    
    /**
     * 需要合并的所有起始行
     */
    private int[] mergeRowIndexes;

    /**
     * 中间值
     */
    private Map<Integer, String> midValueMap = new HashMap<>();

    public ExcelFileCellMergeStrategy(int[] mergeColumnIndex, int mergeRowIndex) {
        this.mergeColumnIndex = mergeColumnIndex;
        this.mergeRowIndex = mergeRowIndex;
    }

    public ExcelFileCellMergeStrategy(int[] mergeColumnIndex, int[] mergeRowIndexes) {
        this.mergeColumnIndex = mergeColumnIndex;
        this.mergeRowIndexes = mergeRowIndexes;
    }

    public ExcelFileCellMergeStrategy() {
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        //当前行
        int curRowIndex = cell.getRowIndex();
        //当前列
        int curColIndex = cell.getColumnIndex();
        if (curRowIndex >= mergeRowIndex){
            for (int columnIndex : mergeColumnIndex) {
                if (curColIndex == columnIndex){
                    mergeWithPrevRow(writeSheetHolder, cell ,curRowIndex, curColIndex, mergeRowIndex);
                    break;
                }
            }
        }
    }

    /**
     * 当前单元格向上合并
     * @param writeSheetHolder
     * @param cell
     * @param curRowIndex
     * @param curColIndex
     */
    private void mergeWithPrevRow(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex, int startRow) {
        Object curData = cell.getCellTypeEnum() == CellType.STRING ? cell.getStringCellValue() : cell.getNumericCellValue();
        if (curRowIndex == startRow) {
            String value = (String) curData;
            this.midValueMap.put(curColIndex, value);
        }
        Object preData = this.midValueMap.get(curColIndex);
        //比较当前行的第一列的单元格与上一行是否相同，相同合并当前单元格与上一行
        if (curData.equals(preData) && curRowIndex != startRow) {
            Sheet sheet = writeSheetHolder.getSheet();
            List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
            boolean isMerged = false;
            for (int i = 0; i < mergedRegions.size() && !isMerged; i++) {
                CellRangeAddress cellAddresses = mergedRegions.get(i);
                //若上 一个单元格已经被合并，则先移出原有的合并单元，再重新添加合并单元
                if (cellAddresses.isInRange(curRowIndex - 1, curColIndex)) {
                    sheet.removeMergedRegion(i);
                    cellAddresses.setLastRow(curRowIndex);
                    sheet.addMergedRegion(cellAddresses);
                    isMerged = true;
                }
            }
            //若上一个单元格未被合并，则新增合并单元
            if (!isMerged) {
                CellRangeAddress cellAddresses = new CellRangeAddress(curRowIndex - 1, curRowIndex, curColIndex, curColIndex);
                sheet.addMergedRegion(cellAddresses);
            }
        } else {
            String value = (String) preData;
            this.midValueMap.put(curColIndex, value);
        }
    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }
}



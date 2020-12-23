package com.yumeng.utils.boxNumberGenerate;

public class Box {

    /**
     * 编号字母 A-1里的A
     */
    private String codeLetter;

    /**
     * 编号字母数量AA-1  2个A就为2
     */
    private Integer codeNumber;

    /**
     * 编号数字A-1里的1
     */
    private Integer codeDigit;

    public String getCodeLetter() {
        return codeLetter;
    }

    public void setCodeLetter(String codeLetter) {
        this.codeLetter = codeLetter;
    }

    public Integer getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(Integer codeNumber) {
        this.codeNumber = codeNumber;
    }

    public Integer getCodeDigit() {
        return codeDigit;
    }

    public void setCodeDigit(Integer codeDigit) {
        this.codeDigit = codeDigit;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "codeLetter='" + codeLetter + '\'' +
                ", codeNumber=" + codeNumber +
                ", codeDigit=" + codeDigit +
                '}';
    }
}

package com.yumeng.utils.boxNumberGenerate;

public class Box {

    private String codeLetter;

    private Integer codeNumber;

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

package com.yumeng.utils.pdf_utils;

import cn.hutool.core.io.resource.ClassPathResource;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PdfUtil {

    private static final String CHINESE_FONT_PATH = "font/simsun.ttc";

    /**
     * 通过html的文件路径转pdf
     */
    public static void createPdfByPath(String htmlFilePath, String destFilePath) throws IOException, DocumentException {
        OutputStream out = new FileOutputStream(destFilePath);
        ClassPathResource classPathResource = new ClassPathResource(CHINESE_FONT_PATH);
        ITextRenderer renderer = new ITextRenderer();
        String url = new File(htmlFilePath).toURI().toURL().toString();
        renderer.setDocument(url);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(classPathResource.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(out);
        out.close();
    }
}

package com.yumeng.utils.pdf_utils;

import com.aspose.words.Document;
import com.aspose.words.FileFontSource;
import com.aspose.words.FontSettings;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.yumeng.utils.path_utils.PathUtil;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfUtil {

    private static final String CHINESE_FONT_PATH = "/font/simsun.ttc";

    /**
     * 通过html的文件路径转pdf
     */
    public static void createPdfByPath(String htmlFilePath, String destFilePath) throws IOException, DocumentException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Path path = Paths.get(PathUtil.getTempPath(".ttc"));
        InputStream resourceStream = PathUtil.getResourceStream(CHINESE_FONT_PATH);
        if (resourceStream != null) {
            Files.copy(resourceStream, path);
        }
        OutputStream out = new FileOutputStream(destFilePath);
        ITextRenderer renderer = new ITextRenderer();
        String url = new File(htmlFilePath).toURI().toURL().toString();
        renderer.setDocument(url);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(path.toFile().getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(out);
        out.close();
    }


    /**
     * word 转为 pdf 输出
     *
     * @param inPath  word文件
     * @param outPath pdf 输出文件目录
     */
    public static String word2pdf(String inPath, String outPath) {
        // 验证License
        if (!isWordLicense()) {
            return null;
        }
        FileOutputStream os = null;
        try {
            String path = outPath.substring(0, outPath.lastIndexOf(File.separator));
            File file = new File(path);
            // 创建文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            // 新建一个空白pdf文档
            file = new File(outPath);
            os = new FileOutputStream(file);
            // Address是将要被转化的word文档
            Document doc = new Document(inPath);
            FontSettings fontSettings = new FontSettings();
            Path temp = Paths.get(PathUtil.getTempPath(".ttc"));
            try {
                InputStream inputStream = PathUtil.getResourceStream(CHINESE_FONT_PATH);
                if (inputStream != null){
                    Files.copy(inputStream, temp);
                }
                FileFontSource fileFontSource = new FileFontSource(temp.toFile().getAbsolutePath());
                FileFontSource[] fileFontSourceArray = new FileFontSource[]{fileFontSource};
                fontSettings.setFontsSources(fileFontSourceArray);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            doc.setFontSettings(fontSettings);
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            doc.save(os, com.aspose.words.SaveFormat.PDF);
            os.close();
            Files.delete(temp);
        } catch (Exception e) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        return outPath;
    }

    /**
     * 验证 Aspose.word 组件是否授权
     * 无授权的文件有水印和试用标记
     */
    public static boolean isWordLicense() {
        boolean result = false;
        try {
            // 避免文件遗漏
            String licensexml = "<License>\n" +
                    "<Data>\n" +
                    "<Products>\n" +
                    "<Product>Aspose.Total for Java</Product>\n" +
                    "<Product>Aspose.Words for Java</Product>\n" +
                    "</Products>\n" +
                    "<EditionType>Enterprise</EditionType>\n" +
                    "<SubscriptionExpiry>20991231</SubscriptionExpiry>\n" +
                    "<LicenseExpiry>20991231</LicenseExpiry>\n" +
                    "<SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" +
                    "</Data>\n" +
                    "<Signature>" +
                    "sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU="
                    +
                    "</Signature>\n" +
                    "</License>";
            InputStream inputStream = new ByteArrayInputStream(licensexml.getBytes());
            com.aspose.words.License license = new com.aspose.words.License();
            license.setLicense(inputStream);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

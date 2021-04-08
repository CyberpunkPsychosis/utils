package com.yumeng.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class UtilsApplicationTests {
    @Test
    void contextLoads() throws Exception {
//        Document document = new Document();
//        OutputStream os = new FileOutputStream(new File("C:\\Users\\user1\\Desktop\\测试.pdf"));
//        PdfWriter.getInstance(document,os);
//        document.open();
//        // Create a PdfFont
//        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
//        // Add a Paragraph
//        document.add(new Paragraph("iText is:").setFont(font));
//        // Create a List
//        List list = new List()
//                .setSymbolIndent(12)
//                .setListSymbol("•")
//                .setFont(font);
//        // Add ListItem objects
//        list.add(new ListItem("Never gonna give you up"))
//                .add(new ListItem("Never gonna let you down"))
//                .add(new ListItem("Never gonna run around and desert you"))
//                .add(new ListItem("Never gonna make you cry"))
//                .add(new ListItem("Never gonna say goodbye"))
//                .add(new ListItem("Never gonna tell a lie and hurt you"));
//        // Add the list
//        document.add(list);
//        document.close();

//        Image fox = new Image(ImageDataFactory.create("C:\\Users\\user1\\Pictures\\测试.png"));
//        Image dog = new Image(ImageDataFactory.create("C:\\Users\\user1\\Pictures\\测试.png"));
//        Paragraph p = new Paragraph("The quick brown ")
//                .add(fox)
//                .add(" jumps over the lazy ")
//                .add(dog);
//        document.add(p);
//        document.close();
//        PdfWriter writer = new PdfWriter(fos);
//        PdfDocument pdf = new PdfDocument(writer);
//        Document document = new Document(pdf, PageSize.A4);
//        document.setMargins(20, 20, 20, 20);
//        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
//        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
//        Table table = new Table(new float[]{1,2});
//        table.setWidthPercent(100);
//        for (int i = 0; i < 2; i++) {
//            table.addCell(new Cell().add(new Paragraph("aaa").setFont(font)));
//        }
//        document.add(table);
//        document.close();
//        InputStream resourceStream = PathUtil.getResourceStream("/templates/simsun.ttc");
//        Path path = Paths.get(PathUtil.getTempPath(".ttc"));
//        Files.copy(resourceStream, path);
//        FontProgram fontProgram =
//                FontProgramFactory.createFont("C:\\Users\\user1\\IdeaProjects\\utils\\temp\\6970580321b34806b6c3a4064ba720db.ttc,0");
//        Paragraph p = new Paragraph();
//        p.setFont(font);
//        PdfFont font = PdfFontFactory.createFont(
//                fontProgram, PdfEncodings.WINANSI, true);
//        p.add("中文");
//        document.add(p);
//        Files.delete(path);



        Document document = new Document(PageSize.A4);
        OutputStream os = new FileOutputStream(new File("C:\\Users\\user1\\Desktop\\测试.pdf"));
        PdfWriter.getInstance(document,os);
        document.open();
        BaseFont baseFont = BaseFont.createFont("/templates/simsun.ttc" + ",0",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);

        PdfPTable header = new PdfPTable(2);
        PdfPTable headerLeft = new PdfPTable(1);

        //顶部左边的图片
        PdfPCell pdfPCell = new PdfPCell(new Phrase(" ", font));
        pdfPCell.setFixedHeight(60);
        pdfPCell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        pdfPCell.disableBorderSide(15);
        headerLeft.addCell(pdfPCell);

        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), font));
        pdfPCell2.setFixedHeight(20);
        pdfPCell2.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        pdfPCell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        pdfPCell2.disableBorderSide(15);
        headerLeft.addCell(pdfPCell2);

        PdfPCell pdfPCell3 = new PdfPCell(headerLeft);
        pdfPCell3.disableBorderSide(13);

        header.addCell(pdfPCell3);
        //顶部右边的图片
        PdfPCell pdfPCell1 = new PdfPCell(new Phrase(" ", font));
        pdfPCell1.setFixedHeight(80);
        pdfPCell1.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        pdfPCell1.disableBorderSide(13);
        header.addCell(pdfPCell1);

        document.add(header);
        document.close();
    }
}

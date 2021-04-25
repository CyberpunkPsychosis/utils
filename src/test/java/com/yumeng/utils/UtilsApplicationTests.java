package com.yumeng.utils;

import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
        document.setMargins(10, 10, 10, 10);
        OutputStream os = new FileOutputStream(new File("C:\\Users\\user1\\Desktop\\测试.pdf"));
        PdfWriter.getInstance(document,os);
        document.open();
        BaseFont baseFont = BaseFont.createFont("/templates/simsun.ttc" + ",0",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);

        PdfPTable headerArea = new PdfPTable(2);
        headerArea.setWidthPercentage(100);
        headerArea.setWidths(new int[]{70, 30});

        PdfPTable headerLeft = new PdfPTable(1);
        //顶部左边的图片
        Image headerLeftImage = Image.getInstance("C:\\Users\\user1\\Pictures\\微信图片_20201013133103.jpg");
        PdfPCell headerLeftCell1 = new PdfPCell(headerLeftImage);
        headerLeftCell1.setFixedHeight(70);
        headerLeftCell1.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        headerLeftCell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        headerLeftCell1.disableBorderSide(15);
        headerLeft.addCell(headerLeftCell1);

        Font headerLeftCell2Font = new Font(baseFont, 12, Font.BOLD);
        PdfPCell headerLeftCell2 = new PdfPCell(new Phrase(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), headerLeftCell2Font));
        headerLeftCell2.setFixedHeight(30);
        headerLeftCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        headerLeftCell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        headerLeftCell2.disableBorderSide(15);
        headerLeftCell2.setPaddingRight(20);
        headerLeft.addCell(headerLeftCell2);

        PdfPCell headerLeftCell = new PdfPCell(headerLeft);
        headerLeftCell.disableBorderSide(13);
        headerArea.addCell(headerLeftCell);

        //顶部右边的图片
        Image headerRightImage = Image.getInstance("C:\\Users\\user1\\Pictures\\微信图片_20201013133103.jpg");
        PdfPCell headerRightCell1 = new PdfPCell(headerRightImage);
        headerRightCell1.setFixedHeight(100);
        headerRightCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        headerRightCell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        headerRightCell1.disableBorderSide(13);
        headerArea.addCell(headerRightCell1);

        //申通条形码和图片
        PdfPTable stoArea = new PdfPTable(1);
        stoArea.setWidthPercentage(100);

        Image stoAreaCellImage = Image.getInstance("C:\\Users\\user1\\Pictures\\微信图片_20201013133103.jpg");
        PdfPCell stoAreaCell1 = new PdfPCell(stoAreaCellImage);
        stoAreaCell1.setFixedHeight(90);
        stoAreaCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        stoAreaCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        stoAreaCell1.disableBorderSide(15);
        stoAreaCell1.setPaddingTop(5);
        stoArea.addCell(stoAreaCell1);

        Font stoAreaCell2Font = new Font(baseFont, 15, Font.BOLD);
        PdfPCell stoAreaCell2 = new PdfPCell(new Phrase("5530303134301", stoAreaCell2Font));
        stoAreaCell2.setFixedHeight(30);
        stoAreaCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        stoAreaCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        stoAreaCell2.disableBorderSide(13);
        stoArea.addCell(stoAreaCell2);


        Font threeNumberFont = new Font(baseFont, 40, Font.BOLD);
        PdfPTable threeNumberArea = new PdfPTable(1);
        threeNumberArea.setWidthPercentage(100);
        PdfPCell threeNumberAreaCell1 = new PdfPCell(new Phrase("241 T109 H25", threeNumberFont));
        threeNumberAreaCell1.setFixedHeight(100);
        threeNumberAreaCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        threeNumberAreaCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        threeNumberAreaCell1.disableBorderSide(13);
        threeNumberArea.addCell(threeNumberAreaCell1);

        Font packAreaCell1Font = new Font(baseFont, 30, Font.BOLD);
        PdfPTable packArea = new PdfPTable(1);
        packArea.setWidthPercentage(100);
        PdfPCell packAreaCell1 = new PdfPCell(new Phrase("哈尔滨市内包", packAreaCell1Font));
        packAreaCell1.setFixedHeight(100);
        packAreaCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        packAreaCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        packAreaCell1.disableBorderSide(13);
        packArea.addCell(packAreaCell1);

        //收件人信息
        PdfPTable consigneeArea = new PdfPTable(1);
        consigneeArea.setWidthPercentage(100);

        Font consigneeTopCell1Font = new Font(baseFont, 12, Font.BOLD);
        PdfPTable consigneeTop = new PdfPTable(2);
        consigneeTop.setWidthPercentage(100);
        PdfPCell consigneeTopCell1 = new PdfPCell(new Phrase("于蒙", consigneeTopCell1Font));
        consigneeTopCell1.setFixedHeight(20);
        consigneeTopCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        consigneeTopCell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        consigneeTopCell1.disableBorderSide(15);
        consigneeTopCell1.setPaddingLeft(50);
        consigneeTop.addCell(consigneeTopCell1);

        PdfPCell consigneeTopCell2 = new PdfPCell(new Phrase("16619777258", consigneeTopCell1Font));
        consigneeTopCell2.setFixedHeight(20);
        consigneeTopCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        consigneeTopCell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        consigneeTopCell2.disableBorderSide(15);
        consigneeTopCell2.setPaddingRight(50);
        consigneeTop.addCell(consigneeTopCell2);

        PdfPCell consigneeAddressCell = new PdfPCell(new Phrase("黑龙江省齐齐哈尔市讷河市", font));
        consigneeAddressCell.setFixedHeight(80);
        consigneeAddressCell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        consigneeAddressCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        consigneeAddressCell.disableBorderSide(13);
        consigneeAddressCell.setPaddingLeft(50);

        PdfPCell consigneeTopCell = new PdfPCell(consigneeTop);
        consigneeTopCell.disableBorderSide(15);

        consigneeArea.addCell(consigneeTopCell);
        consigneeArea.addCell(consigneeAddressCell);


        //发件人信息
        PdfPTable senderArea = new PdfPTable(1);
        senderArea.setWidthPercentage(100);

        PdfPTable senderTop = new PdfPTable(2);
        senderTop.setWidthPercentage(100);
        PdfPCell senderTopCell1 = new PdfPCell(new Phrase("于蒙", consigneeTopCell1Font));
        senderTopCell1.setFixedHeight(20);
        senderTopCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        senderTopCell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        senderTopCell1.disableBorderSide(15);
        senderTopCell1.setPaddingLeft(50);
        senderTop.addCell(senderTopCell1);

        PdfPCell senderTopCell2 = new PdfPCell(new Phrase("16619777258", consigneeTopCell1Font));
        senderTopCell2.setFixedHeight(20);
        senderTopCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        senderTopCell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        senderTopCell2.disableBorderSide(15);
        senderTopCell2.setPaddingRight(50);
        senderTop.addCell(senderTopCell2);

        PdfPCell senderAddressCell = new PdfPCell(new Phrase("黑龙江省齐齐哈尔市讷河市", font));
        senderAddressCell.setFixedHeight(60);
        senderAddressCell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        senderAddressCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        senderAddressCell.disableBorderSide(13);
        senderAddressCell.setPaddingLeft(50);

        PdfPCell senderTopCell = new PdfPCell(senderTop);
        senderTopCell.disableBorderSide(15);

        senderArea.addCell(senderTopCell);
        senderArea.addCell(senderAddressCell);

        //商品信息
        PdfPTable itemTable = new PdfPTable(2);
        itemTable.setWidthPercentage(100);
        itemTable.setWidths(new int[]{300, 200});


        PdfPTable itemLeftTable = new PdfPTable(1);
        itemLeftTable.setWidthPercentage(100);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            stringBuilder.append("内饰物品:MR01 日用品 * 1;");
            stringBuilder.append("\n");
        }
        PdfPCell itemLeftCell1 = new PdfPCell(new Phrase(stringBuilder.toString(), font));
        itemLeftCell1.setFixedHeight(70);
        itemLeftCell1.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        itemLeftCell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        itemLeftCell1.disableBorderSide(15);
        itemLeftCell1.setPaddingLeft(10);
        itemLeftTable.addCell(itemLeftCell1);

        Font itemLeftCell2Font = new Font(baseFont, 15, Font.BOLD);
        PdfPCell itemLeftCell2 = new PdfPCell(new Phrase("已验视", itemLeftCell2Font));
        itemLeftCell2.setFixedHeight(30);
        itemLeftCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        itemLeftCell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        itemLeftCell2.disableBorderSide(15);
        itemLeftCell2.setPaddingBottom(5);
        itemLeftCell2.setPaddingRight(5);
        itemLeftTable.addCell(itemLeftCell2);

        PdfPCell itemLeftCell = new PdfPCell(itemLeftTable);
        itemLeftCell.disableBorderSide(5);
        itemTable.addCell(itemLeftCell);

        Image itemRightCellImage = Image.getInstance("C:\\Users\\user1\\Pictures\\微信图片_20201013133103.jpg");
        PdfPCell itemRightCell = new PdfPCell(itemRightCellImage);
        itemRightCell.setFixedHeight(80);
        itemRightCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        itemRightCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        itemRightCell.setPadding(5);
        itemRightCell.disableBorderSide(9);

        itemTable.addCell(itemRightCell);

        //申通条形码和图片
        PdfPTable stoArea2 = new PdfPTable(1);
        stoArea2.setWidthPercentage(100);

        Image stoArea2Cell1Image = Image.getInstance("C:\\Users\\user1\\Pictures\\微信图片_20201013133103.jpg");
        PdfPCell stoArea2Cell1 = new PdfPCell(stoArea2Cell1Image);
        stoArea2Cell1.setFixedHeight(85);
        stoArea2Cell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        stoArea2Cell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        stoArea2Cell1.disableBorderSide(15);
        stoArea2Cell1.setPaddingTop(5);
        stoArea2.addCell(stoArea2Cell1);

        Font stoArea2Cell2Font = new Font(baseFont, 15, Font.BOLD);
        PdfPCell stoArea2Cell2 = new PdfPCell(new Phrase("5530303134301", stoArea2Cell2Font));
        stoArea2Cell2.setFixedHeight(30);
        stoArea2Cell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        stoArea2Cell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        stoArea2Cell2.disableBorderSide(15);
        stoArea2Cell2.setPaddingTop(10);
        stoArea2.addCell(stoArea2Cell2);

        document.add(headerArea);
        document.add(stoArea);
        document.add(threeNumberArea);
        document.add(packArea);
        document.add(consigneeArea);
        document.add(senderArea);
        document.add(itemTable);
        document.add(stoArea2);
        document.close();
    }


    @Test
    void contextLoads2() throws Exception {
        Document document = new Document(PageSize.A4);
        document.setMargins(10, 10, 10, 10);
        OutputStream os = new FileOutputStream(new File("C:\\Users\\user1\\Desktop\\测试.pdf"));
        PdfWriter writer = PdfWriter.getInstance(document, os);
        document.open();
        PdfContentByte directContent = writer.getDirectContent();
        BaseFont baseFont = BaseFont.createFont("/templates/simsun.ttc" + ",0",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);

        //顶部的条形码
        PdfPTable headerArea = new PdfPTable(2);
        headerArea.setWidthPercentage(100);
        headerArea.setWidths(new int[]{40, 60});

        PdfPCell headerLeftCell = new PdfPCell(new Phrase(""));
        headerLeftCell.setFixedHeight(100);
        headerLeftCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        headerLeftCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        headerLeftCell.disableBorderSide(13);
        headerArea.addCell(headerLeftCell);

        PdfPTable headerRightTable = new PdfPTable(1);

        Image stoArea2Cell1Image = Image.getInstance("C:\\Users\\user1\\Pictures\\微信图片_20201013133103.jpg");
        PdfPCell headerRightTableCell1 = new PdfPCell(stoArea2Cell1Image);
        headerRightTableCell1.setFixedHeight(80);
        headerRightTableCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        headerRightTableCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        headerRightTableCell1.disableBorderSide(15);
        headerRightTable.addCell(headerRightTableCell1);

        PdfPCell headerRightTableCell2 = new PdfPCell(new Phrase("JP20049274", font));
        headerRightTableCell2.setFixedHeight(20);
        headerRightTableCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        headerRightTableCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        headerRightTableCell2.disableBorderSide(15);
        headerRightTable.addCell(headerRightTableCell2);

        PdfPCell headerRightTableCell = new PdfPCell(headerRightTable);
        headerRightTableCell.disableBorderSide(13);

        headerArea.addCell(headerRightTableCell);


        //寄件人收件人信息
        PdfPTable consigneeSenderTable = new PdfPTable(2);
        consigneeSenderTable.setWidthPercentage(100);
        consigneeSenderTable.setWidths(new int[]{50, 50});

        PdfPTable consigneeSenderLeftTable = new PdfPTable(1);

        Font consigneeSenderLeftTableCell1Font = new Font(baseFont, 15, Font.BOLD);
        PdfPCell consigneeSenderLeftTableCell1 = new PdfPCell(new Phrase("寄件", consigneeSenderLeftTableCell1Font));
        consigneeSenderLeftTableCell1.setFixedHeight(30);
        consigneeSenderLeftTableCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        consigneeSenderLeftTableCell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        consigneeSenderLeftTableCell1.disableBorderSide(15);
        consigneeSenderLeftTableCell1.setPaddingTop(10);
        consigneeSenderLeftTable.addCell(consigneeSenderLeftTableCell1);

        Font consigneeSenderLeftTableCell2Font = new Font(baseFont, 8);
        String stringBuilder = "寄件人姓名" + "\n" + "\n" + "寄件人电话" + "\n" + "\n" +
                "寄件人地址";
        PdfPCell consigneeSenderLeftTableCell2 = new PdfPCell(new Phrase(stringBuilder, consigneeSenderLeftTableCell2Font));
        consigneeSenderLeftTableCell2.setFixedHeight(50);
        consigneeSenderLeftTableCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        consigneeSenderLeftTableCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        consigneeSenderLeftTableCell2.disableBorderSide(15);
        consigneeSenderLeftTable.addCell(consigneeSenderLeftTableCell2);

        PdfPCell consigneeSenderLeftTableCell3 = new PdfPCell(new Phrase("收件", consigneeSenderLeftTableCell1Font));
        consigneeSenderLeftTableCell3.setFixedHeight(20);
        consigneeSenderLeftTableCell3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        consigneeSenderLeftTableCell3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        consigneeSenderLeftTableCell3.disableBorderSide(15);
        consigneeSenderLeftTable.addCell(consigneeSenderLeftTableCell3);

        String stringBuilder2 = "收件人姓名" + "\n" + "\n" + "收件人电话" + "\n" + "\n" +
                "收件人地址";
        PdfPCell consigneeSenderLeftTableCell4 = new PdfPCell(new Phrase(stringBuilder2, consigneeSenderLeftTableCell2Font));
        consigneeSenderLeftTableCell4.setFixedHeight(50);
        consigneeSenderLeftTableCell4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        consigneeSenderLeftTableCell4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        consigneeSenderLeftTableCell4.disableBorderSide(13);
        consigneeSenderLeftTable.addCell(consigneeSenderLeftTableCell4);

        PdfPCell consigneeSenderLeftTableCell = new PdfPCell(consigneeSenderLeftTable);
        consigneeSenderLeftTableCell.disableBorderSide(15);
        consigneeSenderTable.addCell(consigneeSenderLeftTableCell);

        Font consigneeSenderRightCellFont = new Font(baseFont, 40, Font.BOLD);
        PdfPCell consigneeSenderRightCell = new PdfPCell(new Phrase("贵州", consigneeSenderRightCellFont));
        consigneeSenderRightCell.setFixedHeight(150);
        consigneeSenderRightCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        consigneeSenderRightCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        consigneeSenderRightCell.disableBorderSide(13);
        consigneeSenderTable.addCell(consigneeSenderRightCell);


        //JP单号
        Font jpNumberFont = new Font(baseFont, 40, Font.BOLD);
        PdfPTable jpNumberArea = new PdfPTable(1);
        jpNumberArea.setWidthPercentage(100);

        PdfPCell jpNumberAreaCell1 = new PdfPCell(new Phrase("JP20049274", jpNumberFont));
        jpNumberAreaCell1.setFixedHeight(80);
        jpNumberAreaCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        jpNumberAreaCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        jpNumberAreaCell1.disableBorderSide(13);
        jpNumberArea.addCell(jpNumberAreaCell1);

        //订单号 件数 重量
        PdfPTable orderNumberArea = new PdfPTable(3);
        orderNumberArea.setWidthPercentage(100);
        orderNumberArea.setWidths(new int[]{33, 33, 33});

        PdfPCell orderNumberAreaCell1 = new PdfPCell(new Phrase("订单号: 52495811", consigneeSenderLeftTableCell1Font));
        orderNumberAreaCell1.setFixedHeight(60);
        orderNumberAreaCell1.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        orderNumberAreaCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        orderNumberAreaCell1.disableBorderSide(13);
        orderNumberAreaCell1.setPaddingTop(10);
        orderNumberArea.addCell(orderNumberAreaCell1);

        PdfPCell orderNumberAreaCell2 = new PdfPCell(new Phrase("件数", consigneeSenderLeftTableCell1Font));
        orderNumberAreaCell2.setFixedHeight(60);
        orderNumberAreaCell2.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        orderNumberAreaCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        orderNumberAreaCell2.disableBorderSide(13);
        orderNumberAreaCell2.setPaddingTop(10);
        orderNumberArea.addCell(orderNumberAreaCell2);

        PdfPCell orderNumberAreaCell3 = new PdfPCell(new Phrase("重量", consigneeSenderLeftTableCell1Font));
        orderNumberAreaCell3.setFixedHeight(60);
        orderNumberAreaCell3.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        orderNumberAreaCell3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        orderNumberAreaCell3.disableBorderSide(13);
        orderNumberAreaCell3.setPaddingTop(10);
        orderNumberArea.addCell(orderNumberAreaCell3);

        //商品信息和Logo
        PdfPTable itemArea = new PdfPTable(2);
        itemArea.setWidthPercentage(100);
        itemArea.setWidths(new int[]{50, 50});


        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            stringBuilder1.append("日用品").append("*").append("1").append("\n");
        }
        Font itemAreaCellFont = new Font(baseFont, 15);
        PdfPCell itemAreaCell1 = new PdfPCell(new Phrase(stringBuilder1.toString(), itemAreaCellFont));
        itemAreaCell1.setFixedHeight(100);
        itemAreaCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        itemAreaCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        itemAreaCell1.disableBorderSide(13);
        itemArea.addCell(itemAreaCell1);

        Image itemAreaCell2Image = Image.getInstance("C:\\Users\\user1\\Pictures\\微信图片_20201013133103.jpg");
        PdfPCell itemAreaCell2 = new PdfPCell(itemAreaCell2Image);
        itemAreaCell2.setFixedHeight(100);
        itemAreaCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        itemAreaCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        itemAreaCell2.disableBorderSide(13);
        itemAreaCell2.setPaddingTop(5);
        itemAreaCell2.setPaddingBottom(5);
        itemArea.addCell(itemAreaCell2);

        //条形码
        PdfPTable barCodeTable = new PdfPTable(1);
        barCodeTable.setWidthPercentage(100);

        Image barCodeCellImage = Image.getInstance("C:\\Users\\user1\\Pictures\\微信图片_20201013133103.jpg");
        PdfPCell barCodeCell = new PdfPCell(barCodeCellImage);
        barCodeCell.setFixedHeight(100);
        barCodeCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        barCodeCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        barCodeCell.disableBorderSide(13);
        barCodeCell.setPaddingTop(5);
        barCodeCell.setPaddingBottom(5);
        barCodeTable.addCell(barCodeCell);

        //寄件收件人信息
        PdfPTable senderArea = new PdfPTable(2);
        senderArea.setWidthPercentage(100);
        senderArea.setWidths(new int[]{50, 50});

        PdfPTable senderAreaLeftTable = new PdfPTable(1);

        Font senderAreaLeftTableCell1Font = new Font(baseFont, 15, Font.BOLD);
        PdfPCell senderAreaLeftTableCell1 = new PdfPCell(new Phrase("寄件", senderAreaLeftTableCell1Font));
        senderAreaLeftTableCell1.setFixedHeight(30);
        senderAreaLeftTableCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        senderAreaLeftTableCell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        senderAreaLeftTableCell1.disableBorderSide(15);
        senderAreaLeftTableCell1.setPaddingTop(10);
        senderAreaLeftTable.addCell(senderAreaLeftTableCell1);

        String stringBuilder3 = "寄件人姓名" + "\n" + "寄件人电话" + "\n" +
                "寄件人地址";
        PdfPCell senderAreaLeftTableCell2 = new PdfPCell(new Phrase(stringBuilder3, font));
        senderAreaLeftTableCell2.setFixedHeight(80);
        senderAreaLeftTableCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        senderAreaLeftTableCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        senderAreaLeftTableCell2.disableBorderSide(15);
        senderAreaLeftTable.addCell(senderAreaLeftTableCell2);

        PdfPCell senderAreaLeftTableCell = new PdfPCell(senderAreaLeftTable);
        senderAreaLeftTableCell.disableBorderSide(13);
        senderArea.addCell(senderAreaLeftTableCell);


        PdfPTable senderAreaRightTable = new PdfPTable(1);

        Font senderAreaRightTableCell1Font = new Font(baseFont, 15, Font.BOLD);
        PdfPCell senderAreaRightTableCell1 = new PdfPCell(new Phrase("收件", senderAreaRightTableCell1Font));
        senderAreaRightTableCell1.setFixedHeight(30);
        senderAreaRightTableCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        senderAreaRightTableCell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        senderAreaRightTableCell1.disableBorderSide(15);
        senderAreaRightTableCell1.setPaddingTop(10);
        senderAreaRightTable.addCell(senderAreaRightTableCell1);

        String stringBuilder4 = "收件人姓名" + "\n" + "收件人电话" + "\n" +
                "收件人地址";
        PdfPCell senderAreaRightTableCell2 = new PdfPCell(new Phrase(stringBuilder4, font));
        senderAreaRightTableCell2.setFixedHeight(80);
        senderAreaRightTableCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        senderAreaRightTableCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        senderAreaRightTableCell2.disableBorderSide(13);
        senderAreaRightTable.addCell(senderAreaRightTableCell2);

        PdfPCell senderAreaRightTableCell = new PdfPCell(senderAreaRightTable);
        senderAreaRightTableCell.disableBorderSide(15);
        senderArea.addCell(senderAreaRightTableCell);

        //二维码
        PdfPTable qrCodeArea = new PdfPTable(2);
        qrCodeArea.setWidthPercentage(100);
        qrCodeArea.setWidths(new int[]{60, 40});

        Font qrCodeAreaTableCell1Font = new Font(baseFont, 15);
        String tips = "备注: 收获前请确认包装是否完, 有无破损, 如有问题, 请拒绝签收" + "\n" + "\n" + "JP20049274";
        PdfPCell qrCodeAreaTableCell1 = new PdfPCell(new Phrase(tips, qrCodeAreaTableCell1Font));
        qrCodeAreaTableCell1.setFixedHeight(100);
        qrCodeAreaTableCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        qrCodeAreaTableCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        qrCodeAreaTableCell1.disableBorderSide(15);
        qrCodeAreaTableCell1.setPaddingTop(20);
        qrCodeArea.addCell(qrCodeAreaTableCell1);

        Image qrCodeAreaTableCell2Image = Image.getInstance("C:\\Users\\user1\\Pictures\\微信图片_20201013133103.jpg");
        PdfPCell qrCodeAreaTableCell2 = new PdfPCell(qrCodeAreaTableCell2Image);
        qrCodeAreaTableCell2.setFixedHeight(100);
        qrCodeAreaTableCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        qrCodeAreaTableCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        qrCodeAreaTableCell2.disableBorderSide(15);
        qrCodeAreaTableCell2.setPaddingTop(20);
        qrCodeArea.addCell(qrCodeAreaTableCell2);

        document.add(headerArea);
        document.add(consigneeSenderTable);
        document.add(jpNumberArea);
        document.add(orderNumberArea);
        document.add(itemArea);
        document.add(barCodeTable);
        document.add(senderArea);
        document.add(qrCodeArea);
        document.close();
        os.close();
    }

    @Test
    void contextLoads3() throws Exception {
        Rectangle EXP = new RectangleReadOnly(283.3F, 425F);
        Document document = new Document(EXP);
        document.setMargins(5, 5, 5, 5);
        OutputStream os = new FileOutputStream(new File("C:\\Users\\user1\\Desktop\\测试.pdf"));
        PdfWriter writer = PdfWriter.getInstance(document, os);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        BaseFont baseFont = BaseFont.createFont("/templates/simsun.ttc" + ",0",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);


        PdfPTable headerArea = new PdfPTable(2);
        headerArea.setWidthPercentage(100);
        headerArea.setWidths(new int[]{30, 70});

        PdfPTable headerLeftTable = new PdfPTable(1);

        Font headerLeftTableFont = new Font(baseFont, 20, Font.BOLD);
        PdfPCell headerLeftTableCell1 = new PdfPCell(new Phrase("配货单", headerLeftTableFont));
        headerLeftTableCell1.setFixedHeight(60);
        headerLeftTableCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        headerLeftTableCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        headerLeftTableCell1.disableBorderSide(15);
        headerLeftTable.addCell(headerLeftTableCell1);

        PdfPCell headerLeftTableCell2 = new PdfPCell(new Phrase("批次号", font));
        headerLeftTableCell2.setFixedHeight(20);
        headerLeftTableCell2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        headerLeftTableCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        headerLeftTableCell2.disableBorderSide(15);
        headerLeftTable.addCell(headerLeftTableCell2);

        PdfPCell headerRightTableCell = new PdfPCell(headerLeftTable);
        headerRightTableCell.disableBorderSide(13);

        headerArea.addCell(headerRightTableCell);

        Barcode128 code128 = new Barcode128();
        code128.setCode(IdUtil.simpleUUID());
        code128.setCodeType(Barcode128.CODE128);
//        code128.setFont(null);
        Image code128Image = code128.createImageWithBarcode(cb, null, null);
        code128Image.scalePercent(60);
        PdfPCell headerRightTableCell1 = new PdfPCell(code128Image);
        headerRightTableCell1.setFixedHeight(80);
        headerRightTableCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        headerRightTableCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        headerRightTableCell1.disableBorderSide(13);
        headerArea.addCell(headerRightTableCell1);

        PdfPTable itemTable = new PdfPTable(3);
        itemTable.setWidthPercentage(100);
        itemTable.setWidths(new int[]{33, 33, 33});

        Font itemTableCellTitleFont = new Font(baseFont, 9, Font.BOLD);
        PdfPCell itemTableCellTitle1 = new PdfPCell(new Phrase("JAN", itemTableCellTitleFont));
        itemTableCellTitle1.setFixedHeight(20);
        itemTableCellTitle1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        itemTableCellTitle1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        itemTableCellTitle1.disableBorderSide(13);
        itemTable.addCell(itemTableCellTitle1);

        PdfPCell itemTableCellTitle2 = new PdfPCell(new Phrase("数量", itemTableCellTitleFont));
        itemTableCellTitle2.setFixedHeight(20);
        itemTableCellTitle2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        itemTableCellTitle2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        itemTableCellTitle2.disableBorderSide(13);
        itemTable.addCell(itemTableCellTitle2);

        PdfPCell itemTableCellTitle3 = new PdfPCell(new Phrase("品名", itemTableCellTitleFont));
        itemTableCellTitle3.setFixedHeight(20);
        itemTableCellTitle3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        itemTableCellTitle3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        itemTableCellTitle3.disableBorderSide(13);
        itemTable.addCell(itemTableCellTitle3);

        Font tempFont = new Font(baseFont, 9);
        for (int i = 0; i < 10; i++) {
            PdfPCell temp1 = new PdfPCell(new Phrase("JAN", tempFont));
            temp1.setFixedHeight(20);
            temp1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            temp1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            if (i == 9) {
                temp1.disableBorderSide(13);
            } else {
                temp1.disableBorderSide(15);
            }
            itemTable.addCell(temp1);

            PdfPCell temp2 = new PdfPCell(new Phrase("数量", tempFont));
            temp2.setFixedHeight(20);
            temp2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            temp2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            if (i == 9) {
                temp2.disableBorderSide(13);
            } else {
                temp2.disableBorderSide(15);
            }
            itemTable.addCell(temp2);

            PdfPCell temp3 = new PdfPCell(new Phrase("品名", tempFont));
            temp3.setFixedHeight(20);
            temp3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            temp3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            if (i == 9) {
                temp3.disableBorderSide(13);
            } else {
                temp3.disableBorderSide(15);
            }
            itemTable.addCell(temp3);
        }

        PdfPTable remark = new PdfPTable(1);
        remark.setWidthPercentage(100);

        PdfPCell remarkCell = new PdfPCell(new Phrase("备注", itemTableCellTitleFont));
        remarkCell.setFixedHeight(20);
        remarkCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        remarkCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        remarkCell.disableBorderSide(13);
        remark.addCell(remarkCell);

        PdfPTable bottom = new PdfPTable(2);
        bottom.setWidthPercentage(100);
        bottom.setWidths(new int[]{60, 40});

        PdfPTable bottomLeft = new PdfPTable(1);

        Font bottomFont = new Font(baseFont, 12);
        PdfPCell bottomLeft1 = new PdfPCell(new Phrase("收件人姓名", bottomFont));
        bottomLeft1.setFixedHeight(30);
        bottomLeft1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        bottomLeft1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        bottomLeft1.disableBorderSide(15);
        bottomLeft.addCell(bottomLeft1);

        PdfPCell bottomLeft2 = new PdfPCell(new Phrase("地址", bottomFont));
        bottomLeft2.setFixedHeight(30);
        bottomLeft2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        bottomLeft2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        bottomLeft2.disableBorderSide(15);
        bottomLeft.addCell(bottomLeft2);


        PdfPCell bottomLeft3 = new PdfPCell(new Phrase("代理名称/用户名称", bottomFont));
        bottomLeft3.setFixedHeight(30);
        bottomLeft3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        bottomLeft3.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        bottomLeft3.disableBorderSide(15);
        bottomLeft.addCell(bottomLeft3);

        PdfPCell bottomLeftCell = new PdfPCell(bottomLeft);
        bottomLeftCell.disableBorderSide(13);

        bottom.addCell(bottomLeftCell);


        PdfPTable bottomRight = new PdfPTable(1);

        BarcodeQRCode qrcode = new BarcodeQRCode("https://www.baidu.com/", 2, 2, null);
        Image qrcodeImage = qrcode.getImage();
        qrcodeImage.scalePercent(200);
        PdfPCell bottomRight1 = new PdfPCell(qrcodeImage);
        bottomRight1.setFixedHeight(70);
        bottomRight1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        bottomRight1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        bottomRight1.disableBorderSide(15);
        bottomRight1.setPaddingTop(5);
        bottomRight.addCell(bottomRight1);

        PdfPCell bottomRight2 = new PdfPCell(new Phrase("订单类型B2B/B2C", bottomFont));
        bottomRight2.setFixedHeight(20);
        bottomRight2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        bottomRight2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        bottomRight2.disableBorderSide(15);
        bottomRight.addCell(bottomRight2);

        PdfPCell bottomRightCell = new PdfPCell(bottomRight);
        bottomRightCell.disableBorderSide(13);

        bottom.addCell(bottomRightCell);

        document.add(headerArea);
        document.add(itemTable);
        document.add(remark);
        document.add(bottom);
        document.close();
    }

    @Test
    void contextLoads4() throws Exception {
        Demo demo1 = new Demo(1);
        Demo demo2 = new Demo(2);
        Demo demo3 = new Demo(3);
        Demo demo4 = new Demo(4);
        Demo demo5 = new Demo(5);
        Demo demo6 = new Demo(6);
        Demo demo7 = new Demo(7);
        Demo demo8 = new Demo(8);
        Demo demo9 = new Demo(9);
        Demo demo10 = new Demo(10);
        Demo demo11 = new Demo(11);
        Demo demo12 = new Demo(12);

        List<Demo> list = new ArrayList<>();
        list.add(demo1);
        list.add(demo2);
        list.add(demo3);
        list.add(demo4);
        list.add(demo5);
        list.add(demo6);
        list.add(demo7);
        list.add(demo8);
        list.add(demo9);
        list.add(demo10);
        list.add(demo11);
        list.add(demo12);

        list.sort(Comparator.comparing(Demo::getIndex));

        System.out.println(list.toString());
    }

    class Demo {
        private Integer index;

        public Demo(Integer index) {
            this.index = index;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "Demo{" +
                    "index=" + index +
                    '}';
        }
    }

    @Test
    void contextLoads5() throws Exception {
        // 写法1
//        String fileName = PathUtil.getTempDir() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
//
//        // 写法2
//        fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = null;
//        try {
//            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            excelWriter.write(data(), writeSheet);
//        } finally {
//            // 千万别忘记finish 会帮忙关闭流
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
//        }
    }


    public class DemoData {
        @ExcelProperty("字符串标题")
        private String string;
        @ExcelProperty("日期标题")
        private Date date;
        @ExcelProperty("数字标题")
        private Double doubleData;
        /**
         * 忽略这个字段
         */
        @ExcelIgnore
        private String ignore;

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Double getDoubleData() {
            return doubleData;
        }

        public void setDoubleData(Double doubleData) {
            this.doubleData = doubleData;
        }

        public String getIgnore() {
            return ignore;
        }

        public void setIgnore(String ignore) {
            this.ignore = ignore;
        }
    }

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


}

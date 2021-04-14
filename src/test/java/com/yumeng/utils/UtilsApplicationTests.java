package com.yumeng.utils;

import com.itextpdf.text.*;
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
}

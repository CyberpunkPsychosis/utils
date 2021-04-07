package com.yumeng.utils.wordExport;

import com.yumeng.utils.word_utils.WordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class WordImportTest {

    @Test
    void contextLoads() {
        try {
//            ReportForFreemarkerUtil.createWord(getWordData(), "C:\\Users\\user1\\Desktop\\SubpackageA.ftl",
//                    "C:\\Users\\user1\\Desktop\\", "freemarker测试.docx");
            WordUtil.generateFile(getWordData(),
                    "C:\\Users\\user1\\Desktop\\SubpackageA.ftl",
                    "C:\\Users\\user1\\Desktop\\freemarker测试.doc"
                    );

//            String destPdf = PathUtil.getTempPath(".pdf");
//            PdfUtil.word2pdf("C:\\Users\\user1\\Desktop\\freemarker测试.docx", destPdf);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取生成Word文档所需要的数据
     */
    private Map<String, Object> getWordData() {
        /*
         * 创建一个Map对象，将Word文档需要的数据都保存到该Map对象中
         */
        Map<String, Object> dataMap = new HashMap<>();
//        /*
//         * 直接在map里保存一个用户的各项信息
//         * 该用户信息用于Word文档中FreeMarker普通文本处理
//         * 模板文档占位符${name}中的name即指定使用这里的name属性的值"用户1"替换
//         */
//        dataMap.put("name", "用户1");
//        dataMap.put("sex", "男");
//        dataMap.put("birthday", "1991-01-01");
//        /*
//         * 将用户的各项信息封装成对象，然后将对象保存在map中，
//         * 该用户对象用于Word文档中FreeMarker表格和图片处理
//         * 模板文档占位符${userObj.name}中的userObj即指定使用这里的userObj属性的值(即user2对象)替换
//         */
//        User user2 = new User();
//        user2.setName("用户2");
//        user2.setSex("女");
//        user2.setBirthday("1992-02-02");
//        // 使用FreeMarker在Word文档中生成图片时，需要将图片的内容转换成Base64编码的字符串
//        user2.setPhoto(ImageUtil.getImageBase64String("C:\\Users\\user1\\Pictures\\测试.png"));
//        dataMap.put("user", user2);
//        /*
//         * 将多个用户对象封装成List集合，然后将集合保存在map中
//         * 该用户集合用于Word文档中FreeMarker表单处理
//         * 模板文档中使用<#list users as userList>循环遍历集合，即指定使用这里的users属性的值(即users集合)替换
//         */
//        List<User> userList = new ArrayList<>();
//        User user3 = new User();
//        user3.setName("用户3");
//        user3.setSex("男");
//        user3.setBirthday("1993-03-03");
//        User user4 = new User();
//        user4.setName("用户4");
//        user4.setSex("女");
//        user4.setBirthday("1994-04-04");
//        User user5 = new User();
//        user5.setName("用户4");
//        user5.setSex("女");
//        user5.setBirthday("1994-04-04");
//        User user6 = new User();
//        user6.setName("用户4");
//        user6.setSex("女");
//        user6.setBirthday("1994-04-04");
//        User user7 = new User();
//        user7.setName("用户4");
//        user7.setSex("女");
//        user7.setBirthday("1994-04-04");
//        User user8 = new User();
//        user8.setName("用户4");
//        user8.setSex("女");
//        user8.setBirthday("1994-04-04");
//        User user9 = new User();
//        user9.setName("用户4");
//        user9.setSex("女");
//        user9.setBirthday("1994-04-04");
//        User user10 = new User();
//        user10.setName("用户4");
//        user10.setSex("女");
//        user10.setBirthday("1994-04-04");
//        User user11 = new User();
//        user11.setName("用户4");
//        user11.setSex("女");
//        user11.setBirthday("1994-04-04");
//        User user12 = new User();
//        user12.setName("用户4");
//        user12.setSex("女");
//        user12.setBirthday("1994-04-04");
//        User user13 = new User();
//        user13.setName("用户4");
//        user13.setSex("女");
//        user13.setBirthday("1994-04-04");
//        User user14 = new User();
//        user14.setName("用户4");
//        user14.setSex("女");
//        user14.setBirthday("1994-04-04");
//        User user15 = new User();
//        user15.setName("用户4");
//        user15.setSex("女");
//        user15.setBirthday("1994-04-04");
//        userList.add(user3);
//        userList.add(user4);
//        userList.add(user5);
//        userList.add(user6);
//        userList.add(user7);
//        userList.add(user8);
//        userList.add(user9);
//        userList.add(user10);
//        userList.add(user11);
//        userList.add(user12);
//        userList.add(user13);
//        userList.add(user14);
//        userList.add(user15);
        Map<String, String> map = new HashMap<>();
        map.put("jpNum", "JP200");
        map.put("sn", "aaa");
        map.put("stel", "bbb");
        map.put("saddr", "dafsd");
        map.put("rn", "dfasdf");
        map.put("rtel", "dfad");
        map.put("raddr", "dfad");
        map.put("pro", "");
        map.put("photo1", "");
        map.put("photo2", "");
        map.put("photo3", "");
        map.put("photo4", "");
//        WrapperData wrapperData = new WrapperData();
//        wrapperData.setJpNum("JP200" + RandomUtil.randomNumbers(6));
//        wrapperData.setSn("aaa");
//        wrapperData.setStel("bbb");
//        wrapperData.setSaddr("dafsd");
//        wrapperData.setRn("dfasdf");
//        wrapperData.setRtel("dfad");
//        wrapperData.setRaddr("dfad");
//        wrapperData.setPro("");
//        String qrCodePath = PathUtil.getTempPath(".jpg");
//        File qrCodeFile = new File(qrCodePath);
////        QrCodeUtil.generate(harOrderInfo.getStoNumber(), 300, 300, qrCodeFile);
//        wrapperData.setPhoto4(ImageUtil.getImageBase64String(qrCodePath));
////        File barCodeFile = BarCodeUtils.genBarCode(harOrderInfo.getStoNumber(), PathUtil.getTempPath(), 0);
////        String barCodePath = PathUtil.getTempPath() + harOrderInfo.getStoNumber() + "_" + 0 + ".png";
//        wrapperData.setPhoto1(ImageUtil.getImageBase64String(" "));
//        wrapperData.setPhoto2(ImageUtil.getImageBase64String(" "));
////        String tempFilePath = PathUtil.getTempFilePath(logoUrl.substring(logoUrl.lastIndexOf(".")));
////        UpFileToOSS.getObjectInputStream(logoUrl, tempFilePath);
////        File imgFile = new File(tempFilePath);
//        wrapperData.setPhoto3(ImageUtil.getImageBase64String(" "));
//        qrCodeFile.delete();
////        barCodeFile.delete();
////        imgFile.delete();
        dataMap.put("o", map);
        return dataMap;
    }

    class WrapperData{

        private String jpNum;

        private String sn;

        private String saddr;

        private String rn;

        private String rtel;

        private String raddr;

        private String pro;

//        private String date;

        private String stel;

//        private String trackNum;

//        private String threeNum;

//        private String pack;

//        private String item;

        private String photo1;
        private String photo2;
        private String photo3;
        private String photo4;

        public String getJpNum() {
            return jpNum;
        }

        public void setJpNum(String jpNum) {
            this.jpNum = jpNum;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getSaddr() {
            return saddr;
        }

        public void setSaddr(String saddr) {
            this.saddr = saddr;
        }

        public String getRn() {
            return rn;
        }

        public void setRn(String rn) {
            this.rn = rn;
        }

        public String getRtel() {
            return rtel;
        }

        public void setRtel(String rtel) {
            this.rtel = rtel;
        }

        public String getRaddr() {
            return raddr;
        }

        public void setRaddr(String raddr) {
            this.raddr = raddr;
        }

        public String getPro() {
            return pro;
        }

        public void setPro(String pro) {
            this.pro = pro;
        }

        public String getStel() {
            return stel;
        }

        public void setStel(String stel) {
            this.stel = stel;
        }

        public String getPhoto1() {
            return photo1;
        }

        public void setPhoto1(String photo1) {
            this.photo1 = photo1;
        }

        public String getPhoto2() {
            return photo2;
        }

        public void setPhoto2(String photo2) {
            this.photo2 = photo2;
        }

        public String getPhoto3() {
            return photo3;
        }

        public void setPhoto3(String photo3) {
            this.photo3 = photo3;
        }

        public String getPhoto4() {
            return photo4;
        }

        public void setPhoto4(String photo4) {
            this.photo4 = photo4;
        }
    }
}

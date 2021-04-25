package com.yumeng.utils.excelExport;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class exportExportTest {

    @Test
    void contextLoads() throws Exception {

        String destPath = "C:\\Users\\user1\\Desktop\\target.xlsx";

        String sourcePath = "C:\\Users\\user1\\Desktop\\Source.xlsx";

        Demo demo1 = new Demo();
        demo1.setName("a");

        Demo demo2 = new Demo();
        demo2.setName("a");

        Demo demo3 = new Demo();
        demo3.setName("a");

        Demo demo4 = new Demo();
        demo4.setName("a");

        List<Demo> list = new ArrayList<>();
        list.add(demo1);
        list.add(demo2);
        list.add(demo3);
        list.add(demo4);
        EasyExcel.write(destPath).withTemplate(sourcePath)
                .registerWriteHandler(new ExcelFileCellMergeStrategy(
                        new int[]{0},
                        1
                ))
                .sheet().doFill(list);
    }


    class Demo {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

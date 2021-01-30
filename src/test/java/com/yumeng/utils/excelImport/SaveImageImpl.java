package com.yumeng.utils.excelImport;

import com.yumeng.utils.alibaba_oss.AlibabaOSSUtil;
import com.yumeng.utils.excel_utils.SaveImage;
import org.apache.poi.ss.usermodel.PictureData;

import java.io.ByteArrayInputStream;

public class SaveImageImpl implements SaveImage {

    @Override
    public String save(PictureData pictureData) {
        return AlibabaOSSUtil
                .streamUpload(new ByteArrayInputStream(pictureData.getData()),
                        "test", "test", ".jpg");
    }

}

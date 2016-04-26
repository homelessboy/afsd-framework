package me.afsd.site.base.view;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * User: afsd
 * Date: 2016/3/7
 * Time: 15:53
 */
public abstract class BaseTemplateXlsView extends BaseXlsView {
    public static final String TEMPLATE_PATH = "templatePath";
    public static final int ORDERSHEET_INDEX=1;

    @Override
    protected HSSFWorkbook setUpWorkbook(Map<String, Object> model, HSSFWorkbook workbook) {
        try {
            workbook=new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:" + model.get(TEMPLATE_PATH))));
        } catch (IOException e) {
            return workbook;
        }
        return workbook;
    }
}

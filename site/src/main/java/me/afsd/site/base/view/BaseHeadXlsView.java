package me.afsd.site.base.view;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import java.io.IOException;
import java.util.Map;

/**
 * User: afsd
 * Date: 2016/3/7
 * Time: 16:03
 */
public abstract class BaseHeadXlsView extends BaseXlsView{
    @Override
    protected HSSFWorkbook setUpWorkbook(Map<String,Object> model,HSSFWorkbook workbook) {
        createSheet(model,workbook);
        return workbook;
    }

    protected void setUpHeader(HSSFSheet sheet, CellStyle style) {
        HSSFRow header = sheet.createRow(0);
        String[] headerNames = getHEADER_NAMES();
        for (int column = 0; column < headerNames.length; column++) {
            header.createCell(column).setCellValue(headerNames[column]);
            header.getCell(column).setCellStyle(style);
        }
    }

    protected HSSFSheet createSheet(Map<String, Object> model, HSSFWorkbook workbook){
        HSSFSheet sheet = workbook.createSheet((String) model.get(SHEET_NAME));
        setUpHeader(sheet, getHeadStyle(sheet, workbook));
        return sheet;
    }

    protected CellStyle getHeadStyle(HSSFSheet sheet, HSSFWorkbook workbook) {
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        style.setFillForegroundColor(HSSFColor.ORANGE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        return style;
    }

    protected abstract String[] getHEADER_NAMES();
}

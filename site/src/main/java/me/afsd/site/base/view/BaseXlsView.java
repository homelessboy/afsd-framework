package me.afsd.site.base.view;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * User: afsd
 * Date: 2016/3/7
 * Time: 11:02
 */
public abstract class BaseXlsView extends AbstractXlsView {
    protected String FILE_EXTENSION = ".xls";
    public static final String FILE_NAME = "fileName";
    public static final String SHEET_NAME = "sheetName";
    public static final String MODEL_NAME = "data";

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workBook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        buildExcelDocument(model, (HSSFWorkbook) workBook);
        response.setHeader("Content-Disposition", "attachment; filename=" + encodeFilename(getFileName(model), request));
    }

    protected String getFileName(Map<String, Object> model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM/dd-HH:mm:ss");
        String fileName = (String) model.get(FILE_NAME);
        if (fileName != null && !fileName.isEmpty()) {
            return fileName + "-" + sdf.format(new Date()) + FILE_EXTENSION;
        } else {
            return sdf.format(new Date()) + FILE_EXTENSION;
        }
    }

    private String encodeFilename(String fileName, HttpServletRequest request) {
        String agent = request.getHeader("USER-AGENT");
        try {
            if ((agent != null) && (agent.contains("MSIE"))) {
                String newFileName = URLEncoder.encode(fileName, "UTF-8");
                newFileName = StringUtils.replace(newFileName, "+", "%20");
                if (newFileName.length() > 150) {
                    newFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                    newFileName = StringUtils.replace(newFileName, " ", "%20");
                }
                return newFileName;
            }
            if ((agent != null) && (agent.contains("Mozilla")))
                return new String(fileName.getBytes("GB2312"),"ISO-8859-1");

            return fileName;
        } catch (Exception ex) {
            return fileName;
        }
    }

    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook) throws Exception {
        setUpWorkbook(model, workbook);
        fillupData(model, workbook);
    }

    protected abstract HSSFWorkbook setUpWorkbook(Map<String,Object> model,HSSFWorkbook workbook);

    protected abstract void fillupData(Map<String, Object> model, HSSFWorkbook workbook);

    public String getFILE_EXTENSION() {
        return FILE_EXTENSION;
    }

    public void setFILE_EXTENSION(String FILE_EXTENSION) {
        this.FILE_EXTENSION = FILE_EXTENSION;
    }
}
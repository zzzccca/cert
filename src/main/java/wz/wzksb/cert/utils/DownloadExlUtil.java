package wz.wzksb.cert.utils;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import wz.wzksb.cert.domain.Cert;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wu on 18-1-31.
 */
@Component
public class DownloadExlUtil {


    /***
     * 创建表头
     * @param workbook
     * @param sheet
     */
    private void createTitle(XSSFWorkbook workbook, XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 12 * 256);
        sheet.setColumnWidth(3, 17 * 256);
        sheet.setColumnWidth(4, 20 * 256);

        //设置为居中加粗
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        XSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("考生姓名");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("证件号码");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("考试名称");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("合格时间");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("证书号码");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("邮寄地址");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("手机号码");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("申请时间");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("状态");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("快递单号");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("操作时间");
        cell.setCellStyle(style);
    }

    public void getExcel(HttpServletResponse res, List<Cert> certList)  {

        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=Cert.xlsx");//下载后的word名
//        res.flushBuffer();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("统计表");
        createTitle(workbook, sheet);

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for (Cert cert : certList) {

            XSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(cert.getName());
            row.createCell(1).setCellValue(cert.getIdCard());
            row.createCell(2).setCellValue(cert.getQualification());
            row.createCell(3).setCellValue(cert.getAppropriatetime());
            row.createCell(4).setCellValue(cert.getCertNumber());
            row.createCell(5).setCellValue(cert.getMailAddr());
            row.createCell(6).setCellValue(cert.getMailMobile());
            row.createCell(7).setCellValue(TimeConversionUtil.millis2longdate(cert.getMailReqDate()));
            row.createCell(8).setCellValue(cert.getMailStatus());
            row.createCell(9).setCellValue(cert.getEmsNumber());
            if (StringUtils.hasText(cert.getEmsDate())) {
                row.createCell(10).setCellValue(TimeConversionUtil.millis2date(cert.getEmsDate()));
            }
            XSSFCell cell = row.createCell(11);
            rowNum++;
        }
        try {
        workbook.write(res.getOutputStream());
        }catch (IOException e){}
    }
}

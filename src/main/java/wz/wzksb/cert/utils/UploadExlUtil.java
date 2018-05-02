package wz.wzksb.cert.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import wz.wzksb.cert.domain.Cert;
import wz.wzksb.cert.service.CertService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wu on 18-1-31.
 */
@Component
public class UploadExlUtil {

    @Autowired
    private CertService certService;

    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;

    //获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    //获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    //获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }


    public void getexl(MultipartFile exl, String name) throws Exception {

        boolean a = name.matches("^.+\\.(?i)(xls)$");//正则匹配文件后缀

        Workbook wb = null;
        if (a) {
            wb = new HSSFWorkbook(exl.getInputStream());
        } else {//当excel是2007时
            wb = new XSSFWorkbook(exl.getInputStream());
        }


        //得到第一个shell
        Sheet sheet = wb.getSheetAt(0);

        //得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();

        //得到Excel的列数(前提是有行数)
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }

        List<Cert> certList = new ArrayList<>();
        List<String> certidList = new ArrayList<>();
        //循环Excel行数,从第二行开始。标题不入库
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) continue;
            if (row.getCell(3).getCellTypeEnum()==CellType.BLANK||row.getCell(3).getCellTypeEnum()==CellType._NONE) continue;
            Cert cert = new Cert();

            //循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null !=cell) {
                    if (c == 0) {
                        cell.setCellType(CellType.STRING);
                        cert.setName(cell.getStringCellValue());//获取名字
                    } else if (c == 1) {
                        cell.setCellType(CellType.STRING);
                        cert.setIdCard(cell.getStringCellValue());//身份证号
                    } else if (c == 2) {
                        cell.setCellType(CellType.STRING);
                        cert.setQualification(cell.getStringCellValue());//考试名称
                    } else if (c == 3) {
                        cell.setCellType(CellType.STRING);
                        cert.setAppropriatetime(cell.getStringCellValue());//合格时间
                    } else if (c == 4) {
                        cell.setCellType(CellType.STRING);
                        cert.setCertNumber(cell.getStringCellValue());//证书号码
                    } else if (c == 5) {
                        cell.setCellType(CellType.STRING);
                        cert.setMailAddr(cell.getStringCellValue());//邮寄地址
                    } else if (c == 6) {
                        cell.setCellType(CellType.STRING);
                        cert.setMailMobile(cell.getStringCellValue());//手机号码
                    } else if (c == 7) {
                        cell.setCellType(CellType.STRING);
                        if (StringUtils.hasText(cell.getStringCellValue())) {
                            cert.setMailReqDate(TimeConversionUtil.longdate2millis(cell.getStringCellValue()));//申请日期
                        } else {
                            cert.setMailReqDate("");
                        }
                    } else if (c == 8) {
                        cell.setCellType(CellType.STRING);
                        if (!StringUtils.isEmpty(cell.getStringCellValue())) {
                            cert.setMailStatus(cell.getStringCellValue());//状态
                        }
                    } else if (c == 9) {
                        cell.setCellType(CellType.STRING);
                        cert.setEmsNumber(cell.getStringCellValue());//快递单号
                    } else if (c == 10) {
                        cell.setCellType(CellType.STRING);
                        if (StringUtils.hasText(cell.getStringCellValue())) {
                            cert.setEmsDate(TimeConversionUtil.date2millis(cell.getStringCellValue()));//寄送日期
                        } else {
                            cert.setEmsDate("");
                        }
                    }
                }
            }
            certList.add(cert);
            certidList.add(cert.getCertNumber());
        }
        this.certService.save(certList, certidList);
    }
}

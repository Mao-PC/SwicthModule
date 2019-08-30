package com.nnlight.utils;

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.nnlight.Client.allIMEI;
import static com.nnlight.Client.record;

/**
 * @Auther: maopch
 * @Date: 2019/8/29
 * @Description: excel 工具类
 */
public class ExcelUtil {
    /**
     * 获取最新的IMEI
     *
     * @return
     */
    public static String getLastIMEI() {
        String lastIMEI = "00000000000000000";

        //  Excel
        String fileName = record.getFullSavePath();

        FileOutputStream out = null;
        FileInputStream in = null;
        XSSFWorkbook workbook = null;

        File file = new File(fileName);
        if (!file.exists()) return lastIMEI;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(file));

            XSSFSheet lastSheet = workbook.getSheetAt(0);
            int lastRowNum = lastSheet.getLastRowNum();

            XSSFCell cell = lastSheet.getRow(lastRowNum).getCell(0);
            lastIMEI = cell.getStringCellValue();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                if (workbook != null)
                    workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lastIMEI;
    }

    /**
     * 保存Excel
     *
     * @throws IOException
     */
    public static void writeExcel() throws IOException {

        // 创建 Excel
        String fileName = record.getFullSavePath();

        FileOutputStream out = null;
        FileInputStream in = null;
        XSSFWorkbook workbook = null;

        try {
            workbook = new XSSFWorkbook();

            File savePath = new File(record.getSavePath());
            if (!savePath.exists()) savePath.mkdirs();

            if (new File(fileName).exists()) {
                in = new FileInputStream(fileName);
                workbook = new XSSFWorkbook(in);
            }

            // 文本格式
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFDataFormat format = workbook.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("@"));

            XSSFSheet sheet = workbook.createSheet();;
            XSSFRow titleRow = sheet.createRow(0);
            XSSFCell title0 = titleRow.createCell(0);
            title0.setCellValue("IMEI");

            // 设置列宽
            sheet.setColumnWidth(0, (allIMEI.get(0).length() + 1) * 256);

            for (int i = 0; i < allIMEI.size(); i++) {
                XSSFRow row = sheet.createRow(i + 1);

                XSSFCell cell = row.createCell(0);

                cell.setCellValue(allIMEI.get(i));
                cell.setCellStyle(cellStyle);
            }

            out = new FileOutputStream(fileName);
            workbook.write(out);//保存Excel文件
            out.close();//关闭文件流
            workbook.close();

            System.out.println("OK!");

        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                if (workbook != null)
                    workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

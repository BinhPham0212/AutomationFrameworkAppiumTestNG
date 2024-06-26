//package com.hust.helpers;
//
//import java.awt.Color;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.Map;
//
//import VLU.utils.LogUtils;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelHelper {
//
//    private FileInputStream fis;
//    private FileOutputStream fileOut;
//    private Workbook wb;
//    private Sheet sh;
//    private Cell cell;
//    private Row row;
//    private CellStyle cellstyle;
//    private Color mycolor;
//    private String excelFilePath;
//    private Map<String, Integer> columns = new HashMap<>();
//
//    public String setExcelFile(String ExcelPath, String SheetName) {
//        try {
//            File f = new File(ExcelPath);
//
//            if (!f.exists()) {
//                f.createNewFile();
//                System.out.println("File doesn't exist, so created!");
//            }
//
//            fis = new FileInputStream(ExcelPath);
//            wb = WorkbookFactory.create(fis);
//            sh = wb.getSheet(SheetName);
//            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
//            if (sh == null) {
//                sh = wb.createSheet(SheetName);
//            }
//
//            this.excelFilePath = ExcelPath;
//
//            //adding all the column header names to the map 'columns'
//            sh.getRow(0).forEach(cell ->{
//                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
//            });
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return ExcelPath;
//    }
//    //Gọi data từ Excel file theo từng ô
//    public String getCellData(int rownum,  int colnum) {
//        try{
//            cell = sh.getRow(rownum).getCell(colnum);
//            String CellData = null;
//            switch (cell.getCellType()){
//                case STRING:
//                    CellData = cell.getStringCellValue();
//                    break;
//                case NUMERIC:
//                    if (DateUtil.isCellDateFormatted(cell))
//                    {
//                        CellData = String.valueOf(cell.getDateCellValue());
//                    }
//                    else
//                    {
//                        CellData = String.valueOf((long)cell.getNumericCellValue());
//                    }
//                    break;
//                case BOOLEAN:
//                    CellData = Boolean.toString(cell.getBooleanCellValue());
//                    break;
//                case BLANK:
//                    CellData = "";
//                    break;
//            }
//            return CellData;
//        }catch (Exception e){
//            return"";
//        }
//    }
//
//    //Gọi ra hàm này sẽ cụ thể tên Cột và vị trí dòng
//    public String getCellData(String columnName, int rownum){
//        return getCellData(rownum, columns.get(columnName));
//    }
//
////    public int getCellDataInt(String columnName, int rownum){
////        return Integer.parseInt(getCellData(String.valueOf(rownum)), columns.get(columnName));
////    }
//
//    public String getCellData(String columnName){
//        return getCellData(String.valueOf(columns.get(columnName)));
//    }
//
//    //set by column index
//    public void setCellData(String text, int rowNumber, int colNumber) {
//        try {
//            row = sh.getRow(rowNumber);
//            if (row == null) {
//                row = sh.createRow(rowNumber);
//            }
//            cell = row.getCell(colNumber);
//
//            if (cell == null) {
//                cell = row.createCell(colNumber);
//            }
//            cell.setCellValue(text);
//
//            XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
//            style.setFillPattern(FillPatternType.NO_FILL);
//            style.setAlignment(HorizontalAlignment.CENTER);
//            style.setVerticalAlignment(VerticalAlignment.CENTER);
//
//            cell.setCellStyle(style);
//
//            fileOut = new FileOutputStream(excelFilePath);
//            wb.write(fileOut);
//            fileOut.flush();
//            fileOut.close();
//        } catch (Exception e) {
//            e.getMessage();
//        }
//    }
//
//    //set by column name
//    public void setCellData(String text, int rowNumber, String columnName) {
//        try {
//            row = sh.getRow(rowNumber);
//            if (row == null) {
//                row = sh.createRow(rowNumber);
//            }
//            cell = row.getCell(columns.get(columnName));
//
//            if (cell == null) {
//                cell = row.createCell(columns.get(columnName));
//            }
//            cell.setCellValue(text);   //Lưu giá trị vào ô
//
//            XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
//            style.setFillPattern(FillPatternType.NO_FILL);
//            style.setAlignment(HorizontalAlignment.CENTER);
//            style.setVerticalAlignment(VerticalAlignment.CENTER);
//
//            cell.setCellStyle(style);
//
//            fileOut = new FileOutputStream(excelFilePath);
//            wb.write(fileOut);
//            fileOut.flush();
//            fileOut.close();
//        } catch (Exception e) {
//            e.getMessage();
//        }
//    }
//
//    public Object[][] getExcelData(String excelPath, String sheetName) {
//        Object[][] data = null;
//        Workbook workbook = null;
//
//        LogUtils.info("Excel File: " + excelPath);
//        LogUtils.info("Sheet Name: " + sheetName);
//        try {
//            // load the file
//            FileInputStream fis = new FileInputStream(excelPath);
//
//            // load the workbook
//            workbook = new XSSFWorkbook(fis);
//
//            // load the sheet
//            Sheet sh = workbook.getSheet(sheetName);
//
//            // load the row
//            Row row = sh.getRow(0);
//
//            //
//            int noOfRows = sh.getPhysicalNumberOfRows();
//            int noOfCols = row.getLastCellNum();
//
//            LogUtils.info("No Row: "+ (noOfRows - 1) + " - " + "No Col: " + noOfCols);
//
//            Cell cell;
//            data = new Object[noOfRows - 1][noOfCols];
//
//            //
//            for (int i = 1; i < noOfRows; i++) {          //Dòng thứ 2 trong file  i = 1
//                for (int j = 0; j < noOfCols; j++) {    //Dòng đầu tiên trong file j = 0
//                    row = sh.getRow(i);
//                    cell = row.getCell(j);
//
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            data[i - 1][j] = cell.getStringCellValue();
//                            break;
//                        case NUMERIC:
//                            data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
//                            break;
//                        case BLANK:
//                            data[i - 1][j] = cell.getStringCellValue();
//                            break;
//                        default:
//                            data[i - 1][j] = cell.getStringCellValue();
//                            break;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("The exception is:" + e.getMessage());
//            throw new RuntimeException(e);
//        }
//        return data;
//    }
//
//    /*Hàm này dùng cho trường hợp nhiều Field trong File Excel. Truyền thêm tham số start row và end row
//    **/
//    public int getColumns() {
//        try {
//            row = sh.getRow(0);
//            return row.getLastCellNum();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw (e);
//        }
//    }
//
//    //Get last row number (lấy vị trí dòng cuối cùng tính từ 0)
//    public int getLastRowNum() {
//        return sh.getLastRowNum();
//    }
//
//    //Lấy số dòng có data đang sử dụng
//    public int getPhysicalNumberOfRows() {
//        return sh.getPhysicalNumberOfRows();
//    }
//
//    public Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow) {
//        LogUtils.info("Excel File: " + excelPath);
//        LogUtils.info("Sheet Name: " + sheetName);
//        Object[][] data = null;
//
//        try {
//            File f = new File(excelPath);
//            if (!f.exists()) {
//                try {
//                    LogUtils.info("File Excel path not found.");
//                    throw new IOException("File Excel path not found.");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            fis = new FileInputStream(excelPath);
//
//            wb = new XSSFWorkbook(fis);
//
//            sh = wb.getSheet(sheetName);
//
//            int rows = getLastRowNum();
//            int columns = getColumns();
//
//            System.out.println("Row: " + rows + " - Column: " + columns);
//            System.out.println("StartRow: " + startRow + " - EndRow: " + endRow);
//
//            data = new Object[(endRow - startRow) + 1][1];
//            Hashtable<String, String> table = null;
//            for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
//                table = new Hashtable<>();
//                for (int colNum = 0; colNum < columns; colNum++) {
//                    table.put(getCellData(0, colNum), getCellData(rowNums, colNum));
//                }
//                data[rowNums - startRow][0] = table;
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            LogUtils.error(e.getMessage());
//        }
//
//        return data;
//    }
//
//
//}
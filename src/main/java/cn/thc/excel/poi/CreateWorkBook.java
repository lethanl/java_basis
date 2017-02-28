package cn.thc.excel.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * Created by thc on 2017/1/11
 */
public class CreateWorkBook {

    public static void main(String[] args) throws Exception {
//        createWorkBook();
//        readWorkBook();
//        createExcel();
//        Readsheet();
            typesofCells();
    }

    public static void typesofCells() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("cell types");
        XSSFRow row = sheet.createRow(2);
        row.createCell(0).setCellValue("Type of Cell");
        row.createCell(1).setCellValue("Cell value");
        row = sheet.createRow((short)3);
        row.createCell(0).setCellValue("set cell type BLANK");
        row.createCell(1);
        row = sheet.createRow((short)4);
        row.createCell(0).setCellValue("set cell type BOOLEAN");
        row.createCell(1).setCellValue(true);
        row = sheet.createRow((short)5);
        row.createCell(0).setCellValue("set cell type ERROR");
        row.createCell(1).setCellValue(XSSFCell.CELL_TYPE_ERROR );
        row = sheet.createRow((short) 6);
        row.createCell(0).setCellValue("set cell type date");
        row.createCell(1).setCellValue(new Date());
        row = sheet.createRow((short) 7);
        row.createCell(0).setCellValue("set cell type numeric" );
        row.createCell(1).setCellValue(20 );
        row = sheet.createRow((short) 8);
        row.createCell(0).setCellValue("set cell type string");
        row.createCell(1).setCellValue("A String");
        FileOutputStream out = new FileOutputStream(
                new File("E:/typesofcells.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println(
                "typesofcells.xlsx written successfully");
    }

    public static void Readsheet() throws Exception {
        XSSFRow row;
        FileInputStream fis = new FileInputStream(new File("E:/createWorkBook.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()){
            row = (XSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                switch (cell.getCellType()){
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.println(cell.getNumericCellValue()+"\t\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.println(cell.getStringCellValue()+"\t\t");
                        break;
                }
            }
            System.out.println();
        }
        fis.close();
    }

    public static void createExcel(){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Emplyee info");
        XSSFRow row;
        Map<String,Object[]> empinfo = new TreeMap<String, Object[]>();
        empinfo.put( "1", new Object[] {
                "EMP ID", "EMP NAME", "DESIGNATION" });
        empinfo.put( "2", new Object[] {
                "tp01", "Gopal", "Technical Manager" });
        empinfo.put( "3", new Object[] {
                "tp02", "Manisha", "Proof Reader" });
        empinfo.put( "4", new Object[] {
                "tp03", "Masthan", "Technical Writer" });
        empinfo.put( "5", new Object[] {
                "tp04", "Satish", "Technical Writer" });
        empinfo.put( "6", new Object[] {
                "tp05", "Krishna", "Technical Writer" });
        Set<String> keyid = empinfo.keySet();
        int rowid = 0;
        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = empinfo.get(key);
            int cellid = 0;
            for (Object o : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) o);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("E:/createWorkBook.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Writesheet.xlsx written successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readWorkBook(){
        File file = new File("E:/createWorkBook.xlsx");
        try {
            FileInputStream fIp = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fIp);
            if(file.isFile()&&file.exists()){
                System.out.println("openworkbook.xlsx file open successfully.");
            }else {
                System.out.println("Error to open openworkbook.xlsx file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createWorkBook() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File("E:/createWorkBook.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("createworkbook.xlsx written successfully!");
    }



}

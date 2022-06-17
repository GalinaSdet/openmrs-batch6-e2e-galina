package com.academy.techcenture.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private XSSFWorkbook workBook;
    private XSSFSheet workSheet;


    public ExcelReader(String path, String sheetName){
        File file = new File(path);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            workBook = new XSSFWorkbook(inputStream);
            workSheet = workBook.getSheet(sheetName);
            workBook.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public Object[][] getData(){

        int rows = workSheet.getLastRowNum(); // returns number of rows
        int cols = workSheet.getRow(0).getLastCellNum(); //returns number of cols

        Object[][] data = new Object[rows][1];

        for (int i = 0; i < rows; i++) {
            Map<String,String> map = new HashMap<>();

            for (int j = 0; j < cols; j++) {
                //each column name is a key
                XSSFCell cell = workSheet.getRow(i + 1).getCell(j);// might be null sometimes if the cell is empty

                if (cell == null){
                    System.out.println();
                }

                map.put(workSheet.getRow(0).getCell(j).toString(),
                        // each cell under column name will be value
                        cell == null  ? "" : cell.toString() );
            }
            data[i][0] = map;
        }
        return data;
    }

}

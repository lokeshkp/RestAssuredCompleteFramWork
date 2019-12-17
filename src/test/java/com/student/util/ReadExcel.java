package com.student.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class ReadExcel {

	private static FileInputStream fis;
	private static HSSFWorkbook ExcelWBook;
	private static HSSFSheet ExcelWSheet;
	private static HSSFCell Cell;

	public static Object[][] getExcelData(String filePath, String sheetName) throws Exception {   

		String[][] tabArray = null;

		try {

			fis = new FileInputStream(filePath);
			ExcelWBook = new HSSFWorkbook(fis);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			int startRow = 1;
			int startCol = 0;
			int ci,cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			int totalCols = ExcelWSheet.getRow(1).getLastCellNum();

			tabArray=new String[totalRows][totalCols];
			ci=0;

			for (int i=startRow;i<=totalRows;i++, ci++) {              
				cj=0;
				for (int j=startCol;j<=totalCols-1;j++, cj++){
					tabArray[ci][cj]=getCellData(i,j);
					System.out.println(tabArray[ci][cj]);  
				}
			}
		}catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return(tabArray);
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			CellType dataType = Cell.getCellType();
			if  (dataType.equals(1)) {
				return "";

			}else{
				String CellData = Cell.getStringCellValue();
				return CellData;
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
			throw (e);
		}
	}
}
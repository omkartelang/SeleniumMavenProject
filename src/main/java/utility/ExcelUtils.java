package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;



import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.testng.annotations.DataProvider;

import utility.Constant;

public class ExcelUtils {
	
	// Initialize Log4j logs
	 
	private static Logger logger = Logger.getLogger(ExcelUtils.class.getName());//
	
	private static XSSFWorkbook xlWBook = null;
	private static XSSFSheet xlSheet = null;
	private static XSSFRow xlRow = null;
	private static XSSFCell xlCell = null;
	
	//set test cases from running test case......
	public static String rnTestName = null;
	
	//assign runTestName during runtime....
	public static void setTestName(String rnTest)
	{
		rnTestName = rnTest;
		logger.info("set test case name");
		
	}
		
	//get excel data in array...........
	@DataProvider(name = "dpExcelData")
	public static Object[][] getXLData()throws Exception
	{
				
		String[][] xlTableArr = null;
		
		int totalRowArrSize = 0;
		
		//row and colmn indexes....
		int ri = 0, ci;
		try{
			
			logger.debug("set excel file path and sheet");
			//set file path and sheet name....
			ExcelUtils.setExcelFile(Constant.pathTestData+Constant.fileTestData, Constant.fileShName);
			logger.info("done setting up filepath and sheet name successfully");;
			
			logger.debug("get test case name");
			String tcsName = ExcelUtils.getTestCaseName(rnTestName);
			
			int tcsNameColmn = 0;
			
			logger.debug("get test case row number");
			int tcsRowNum = ExcelUtils.getTestCaseRowNums(tcsName,tcsNameColmn);
			
			//int totalRowsUsed = ExcelUtils.getRowUsed();
			logger.debug("get total column used for running test cases");
			int totalColmnUsed = ExcelUtils.getColmnUsed(tcsRowNum);
			
			logger.debug("get row count for multiple data for tcs");
			int tcsMatchRows = ExcelUtils.getTestCaseMatchingRows(tcsName, tcsNameColmn);
			
			//number of data per test cases
			logger.debug("check number of rows");
			if(tcsRowNum == tcsMatchRows)
			{
				logger.info("found single data for this tcs");
			}else
			{
				logger.info("found multiple data for this tcs");
				totalRowArrSize = tcsMatchRows - tcsRowNum;
				
			}
			
			//set array size....
			
			//row counting based on 0, so set add 1 to set arraysize.....
			int addOneInRowArr = 1;
			//we start reading data from 2nd column.
			int minusFirstColmn = 1;
			logger.info("set array size for array table");
			xlTableArr = new String[totalRowArrSize+addOneInRowArr][totalColmnUsed-minusFirstColmn];
			
			logger.info("Data reading and fetching started...");
			for(int rwNum = tcsRowNum; rwNum<=tcsMatchRows; rwNum++,ri++ )
			{
				ci =0;
				for(int clNum = 1; clNum<totalColmnUsed; clNum++,ci++)
				{
					
					xlTableArr[ri][ci] = ExcelUtils.getCellData(rwNum, clNum);
					logger.debug("data fecting is : " + xlTableArr[ri][ci]);
					
				}
			}
		
		}catch(FileNotFoundException e)
		{
			logger.error("file not found", e);
			e.printStackTrace();
			
		}catch(IOException e)
		{
			logger.error("IO exception", e);
			e.printStackTrace();
		}catch(Exception e)
		{
			logger.error("exception is occured", e);
			e.printStackTrace();
		}
		
		logger.info("Data feteched and stored successfully in arraytable" );
		return (xlTableArr);
	}
	
	//pass raw test case name and return test case name
	public static String getTestCaseName(String sTestCaseName)
	{
		logger.info("Parasing test case name");
		String value = sTestCaseName;
		
		int posi = value.indexOf("@");
		
		value = value.substring(0,posi);
		
		posi = value.lastIndexOf(".");
		
		value = value.substring(posi+1);
		logger.info("test case name parased successfully");
		return value;
	}
	
	//set file path and sheet name.................
	public static void setExcelFile(String filePath, String shName)
	{
		//open excel file
		try {
			logger.info("creating file inputstream object and setting up file path and sheet");
			FileInputStream fis = new FileInputStream(filePath);
			
			logger.debug("creating new object for workbook");
			
			xlWBook = new XSSFWorkbook(fis); 
			
			logger.debug("get sheet from workbook object");
			xlSheet = xlWBook.getSheet(shName);
			logger.info("created xl sheet object");
						
			
		} catch (FileNotFoundException e) {
			
			logger.error("Function - setExcelFile: file not found", e);
			e.printStackTrace();
		}
		catch(IOException e)
		{
			logger.error("Function - setExcelFile: IO exception occured", e);
			e.printStackTrace();
		}
		
		
	}
	
	//get test case row number..................
	public static int getTestCaseRowNums(String sTestCaseName, int colNum) throws Exception
	{
		int rowNum;
		try{
				logger.info("get total rows in Function - getTestCaseRowNums");
				int totalRows = ExcelUtils.getRowUsed();				
		
				for(rowNum = 0; rowNum<totalRows; rowNum++)
				{
					logger.info("get cell value from get cell data ->Function - getTestCaseRows Nums");
					String cellVal = ExcelUtils.getCellData(rowNum, colNum);
					
					if(sTestCaseName.equalsIgnoreCase(cellVal))
					{
						logger.info("No matching test case found - getTestCaseRowNums");
						break;
					}
					logger.debug("Matching test case found");
				}
				
				return rowNum;
		}
		catch(Exception e)
		{
			logger.error("exception occured in Function - getTestCaseRowNums", e);
			e.printStackTrace();
			throw(e);
		}
		
	}
	
	//get matching test cases row number................
	public static int getTestCaseMatchingRows(String sTestCaseName, int colNum) throws Exception
	{
		int rowNum;
		try{
				
				logger.info("get total row used");
				int totalRows = ExcelUtils.getRowUsed();
				
				logger.info("get Test Case row nums");
				int tcsRowNum = ExcelUtils.getTestCaseRowNums(sTestCaseName, colNum);
				
				for(rowNum = tcsRowNum; rowNum<=totalRows; rowNum++)
				{
					String cellVal = ExcelUtils.getCellData(rowNum, colNum);
					
					if(!sTestCaseName.equalsIgnoreCase(cellVal))
					{
						
						break;
					}
					logger.info("found matching test cses rows");
				}
				return rowNum-1;
		}
		catch(Exception e)
		{
			logger.error("exception occured in Function - getTestCaseMatchingRows", e);
			e.printStackTrace();
			throw(e);
		}
		
	}
	
	//get column used in excel data sheet for particular test cases...........
	public static int getColmnUsed(int tcsRowNum)
	{
		
		int colmnUsed = 0;
		
		xlRow = xlSheet.getRow(tcsRowNum);
		logger.info("get count for column used in excel");
		colmnUsed = xlRow.getLastCellNum();
		logger.info("got successfully count for column used in excel");
		return colmnUsed;
	}
	
	//get totla row used in excel sheet...............
	public static int getRowUsed()throws Exception
	{
		try{
			int rowCount = 0;
			logger.info("get total column in excel sheet");
			rowCount = xlSheet.getLastRowNum();
			//rowCount = xlSheet.getPhysicalNumberOfRows();
			logger.info("got total column in excel sheet"+rowCount);
			return rowCount;
			
		}catch(Exception e)
		{
			logger.error("exception occured in Function - getRowUsed", e);
			e.printStackTrace();
			throw(e);
		}	
	}
	
	//get cell data from sheet....................
	public static String getCellData(int rowNum,int colmnNUm)
	{
		
		xlRow = xlSheet.getRow(rowNum);
		
		xlCell = xlRow.getCell(colmnNUm);
		logger.debug("get cell data");
		String cellData = xlCell.getStringCellValue();
		logger.debug("cell data fetched : " + cellData);
		return cellData;
	}
	
}

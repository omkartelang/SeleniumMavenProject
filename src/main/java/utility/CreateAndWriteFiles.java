package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateAndWriteFiles {
	
	//private static File file=null;
	private static PrintWriter pw=null;
	private static FileOutputStream fOut=null;
	private static XSSFWorkbook xlWorkBook = null;
	private static XSSFSheet xlSheet = null;
	private static XSSFRow xlRow=null;
	private static XSSFCell xlCell=null;
	private static Logger logger = Logger.getLogger(CreateAndWriteFiles.class.getName());
	
	public static File createTxtFileObject(String fName)
	{
		logger.info("File object is getting created..");		
		File file = new File("../SeleniumMavenProject/outputFiles", fName+".txt");
		return file;
	}
	
	public static PrintWriter createTxtFile(String txtFileName)
	{
		logger.info("Cretating file object for txt file...");
		File f = createTxtFileObject(txtFileName);
		
		//create object of print writer...
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			
			logger.error("File not found exception:", e);
		}
		return pw;
	}
	
	public static void addDataInTxtFile(String newFileName, String txtData)
	{
		//call create Txt file....
		logger.info("calling create txt file function");
		PrintWriter txtFileObj = createTxtFile(newFileName);
		
		logger.info("Adding txt data in text file...");
		txtFileObj.write(txtData);
	}
	
	public static void addMultipleDataInTxtFile(String newFileName, String[] txtDataArr)
	{
		//call create txt file function....
		logger.info("calling create txt file function");
		PrintWriter txtFileObj = createTxtFile(newFileName);
		
		logger.info("Adding multiple txt data in text file....");
		for(String str:txtDataArr)
		{
			//logger.info("text data array : " + str );
			txtFileObj.println(str);
		}
		//flush out data...
		txtFileObj.flush();
		
		//close the file...
		txtFileObj.close();
		
	}
	
	public static File createXLFileObject(String xlFileName)
	{
		logger.info("creating file object for xl files...");
		
		File fxlsx = new File("../SeleniumMavenProject/outputFiles", xlFileName+".xlsx");
		
		return fxlsx;
	}
	
	public static FileOutputStream createXLFile(String xlsxFileName)
	{
		logger.info("calling create xlsx file object function.....");
		//File fxlsx = createXLFileObject(xlsxFileName);
		
		//create object for FileOutputStream....
		try {
			fOut = new FileOutputStream("../SeleniumMavenProject/outputFiles/"+xlsxFileName+".xlsx");
		} catch (FileNotFoundException e) {
			logger.error("File not found exception: ", e);
		}
		return fOut;
	}
	
	public static void addDataInXLFile(String xlsxFileName, String xlData)
	{
		logger.info("Adding data in xl file....");
		//calling FileOutputStream function....
		FileOutputStream fOutS = createXLFile(xlsxFileName);
		
		xlWorkBook = new XSSFWorkbook();
		
		//create work sheet....
		xlSheet = xlWorkBook.createSheet("xlData");
		
		//create row in xl sheet...
		xlRow = xlSheet.createRow(1);
		
		//write data in 
		xlCell = xlRow.createCell(1);
		
		xlCell.setCellValue(xlData);
		
	}
	
	public static void addMultipleDataInXLFile(String xlsxFileName, String[] xlDataArr)
	{
		logger.info("Adding data in xl file....");
		
		//length of string array.....
		int sizeArr = xlDataArr.length;
		
		//calling FileOutputStream function....
		FileOutputStream fOutS = createXLFile(xlsxFileName);
		
		xlWorkBook = new XSSFWorkbook();
		
		
		
		//create work sheet....
		xlSheet = xlWorkBook.createSheet("xlData");
		
		//create row in xl sheet...
		//xlRow = xlSheet.createRow(sizeArr);
		
		//write data in 
		//xlCell = xlRow.createCell(sizeArr);
		
		for(int rwNum=0; rwNum<sizeArr; rwNum++)
		{
			xlRow = xlSheet.createRow(rwNum);
			xlCell = xlRow.createCell(0);
			xlCell.setCellValue(xlDataArr[rwNum]);
			
		}
		
		try {
			xlWorkBook.write(fOutS);
			xlWorkBook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

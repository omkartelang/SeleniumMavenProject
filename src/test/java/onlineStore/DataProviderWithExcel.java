package onlineStore;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utility.ExcelUtils;

public class DataProviderWithExcel {

	public static WebDriver driver = null;
	
	
	/*@Test
	 public void beforeMethod() throws Exception {

		//pass test case name.....
		ExcelUtils.setTestName(this.toString());
		
	    

        
       String[][] strArray = (String[][]) ExcelUtils.getXLData();
       for(String[] str:strArray)
       {
    	   for(String st1:str)
    	   {
    		   System.out.println("print array is : " + st1);
    	   }
       }

	}*/
	static private Logger logger = Logger.getLogger(DataProviderWithExcel.class.getName());
	//private static Logger logger = Logger.getLogger(DataProviderWithExcel.class.getName());
	@BeforeTest
	public void testBeforeMethod()throws Exception
	{
		
		 BasicConfigurator.configure();
		  
		logger.debug("Hello.. im in Debug method");
		//pass test case name.....
		ExcelUtils.setTestName(this.toString());
		
		String[][] strArray = (String[][]) ExcelUtils.getXLData();
	       for(String[] str:strArray)
	       {
	    	   for(String st1:str)
	    	   {
	    		   System.out.println("print array is : " + st1);
	    	   }
	       }
		
	   
	}

	@Test(dataProvider = "dpExcelData", dataProviderClass = ExcelUtils.class)

	public void Registration_data(String sUserName,String sPassword)throws  Exception{
		
		 /*	driver = new FirefoxDriver();
			
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		    driver.get("http://www.store.demoqa.com");	 
		    
		
		    driver.findElement(By.xpath(".//*[@id='account']/a")).click();
			
			driver.findElement(By.id("log")).sendKeys(sUserName);
			
			System.out.println(sUserName);
			
			driver.findElement(By.id("pwd")).sendKeys(sPassword);
			
			System.out.println(sPassword);
			
			driver.findElement(By.id("login")).click();
			
			System.out.println(" Login Successfully, now it is the time to Log Off buddy.");
			
			//driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();
				
			driver.quit();*/
	
		}
	
	
}

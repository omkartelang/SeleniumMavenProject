package googleTestCases;

import java.io.File;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.GooglePage;
import userDefinedFuns.UserDefinedFunction;
import utility.CreateAndWriteFiles;
import utility.SetBrowser;

public class Google_TCs_02 {

	private static Logger logger = Logger.getLogger(Google_TCs_02.class.getName());
	private static WebDriver driver=null;
	private static WebElement element=null;
	private static List<WebElement> elementList=null;
	private static File file=null;
	
	@BeforeTest
	public void InitTest()
	{
		BasicConfigurator.configure();
		file = new File("test.txt");
		
	}
	
	@Test
	public void google_TC_02() throws InterruptedException{
		
		logger.info("Launch firefox borwoser");
		driver = SetBrowser.setUpBrowser(driver);
		
		logger.info("Opening webpage...");
		//SetBrowser.openWebPage(driver);
		driver.get("https://www.google.co.in/");
		
		//wait for page to load...
		UserDefinedFunction.waitForPageLoaded(driver);
		
		driver.manage().window().maximize();
		
		logger.info("Enter text in google text field");
		element=GooglePage.googleSrchTxtField(driver);
		
		//enter text in google srch text field...
		element.sendKeys("Selenium Tutorial");
		
		logger.info("Hit enter key");
		element.sendKeys(Keys.ENTER);
		
		//wait for next element is present
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//get element list....
		elementList = UserDefinedFunction.getElementList(driver, "google_SrchResultsLink");
		
		//size of array list...
		int sizeOfEleList = elementList.size();
		
		//loop element list and click on 3rd result link....
		//set 3rd web element...
		WebElement thirdResltsLink=null;
		
		//create string array and set dimension.....
		int index =0, i=1;
		String resltsArr[]=new String[sizeOfEleList];
		
		for(WebElement eles:elementList)
		{
			resltsArr[index]= eles.getText();
			//logger.info("results string :" + resltsArr[index]);
			
			if(i==3)
			{
				//click on 3rd link....
				thirdResltsLink= eles;
			}
			
			//increment index....
			index++;
			i++;
		}
		
		//add multiple data in txt file.....
		CreateAndWriteFiles.addMultipleDataInTxtFile("SrhResults", resltsArr);
		CreateAndWriteFiles.addMultipleDataInXLFile("SrhResults", resltsArr);
		
		//click on thirdResltslink....
		thirdResltsLink.click();
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//close browser
		logger.info("Closing all browser....");
		driver.quit();
		
	}
}

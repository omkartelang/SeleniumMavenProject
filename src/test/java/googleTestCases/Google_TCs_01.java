package googleTestCases;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.GooglePage;
import userDefinedFuns.UserDefinedFunction;
import utility.SetBrowser;


public class Google_TCs_01 {

	private static Logger logger = Logger.getLogger(Google_TCs_01.class.getName());
	private static WebDriver driver=null;
	private static WebElement element=null;
	private static List<WebElement> elementList=null;
	
	@BeforeTest
	public void InitTest()
	{
		BasicConfigurator.configure();
		
	}
	
	@Test
	public void google_TC_01() throws InterruptedException{
		
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
		UserDefinedFunction.waitForElementPresent(driver, "pageNumLinks");
		
		//scroll down search results page....
		UserDefinedFunction.scrollDwnPage(driver);
		
		
		
		//get page number link and click on each one....
		logger.info("Get page number links");
		elementList = UserDefinedFunction.getElementList(driver, "pageNumLinks");
		
		//get string array for Web Elements txt...
		UserDefinedFunction.getTextFromElementList(elementList);
		
		//initialize page number txt string...
		int pageNumTxt = 2;
		
		for(WebElement eles: elementList)
		{
			UserDefinedFunction.clickByLinkTxt(driver, String.valueOf(pageNumTxt));
			
			//wait for page to load...
			UserDefinedFunction.waitForPageLoaded(driver);
			
			//scroll down search results page....
			UserDefinedFunction.scrollDwnPage(driver);
			
			//increment page number txt link....
			pageNumTxt++;
		}
		
		//close browser
		logger.info("Closing all browser....");
		driver.quit();
		
	}
	
}

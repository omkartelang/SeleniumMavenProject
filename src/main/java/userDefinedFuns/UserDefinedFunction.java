package userDefinedFuns;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.Constant;
import utility.RepositoryParser;

public class UserDefinedFunction {

	private static Logger logger=Logger.getLogger(UserDefinedFunction.class.getName());
	private static WebElement element=null;
	private static List<WebElement> elementList = null;
	private static RepositoryParser objLocator = null;
	private static Select oSelect = null;
	private static Actions action = null;
	private static String mouseHoverjavaScript = "var evObj = document.createEvent('MouseEvents');" +
            "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
            "arguments[0].dispatchEvent(evObj);";
	
	private static String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"+
			"evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('onmouseover');}";
	
	private static String mouseClickScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"+
			"evObj.initEvent('click',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('onclick');}";
	
	
	
	static
	{
		try {
			objLocator = new RepositoryParser(Constant.objRepFilePath);
		} catch (Exception e) {
		
		logger.error("Exception is rasied for RepositoryParser",e);
	}
	}
	
	
	public static void scrollDwnPage(WebDriver driver)
	{
		logger.info("scrolling down window");
		//((JavascriptExecutor)driver).executeScript("window.ScrollTo(0, document.body.scrollHeight)");
		//((JavascriptExecutor)driver).executeScript("window.ScrollTo(0, 400)");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
	}
	
	public static void waitForElementPresent(WebDriver driver,String locator)throws InterruptedException
	{
		boolean bValue = true;
		boolean eleFound = false;
		
		logger.info("checking element is present");
		element = driver.findElement(objLocator.getObjectLocatior(locator));
		for(int i=0; i<=10; i++)
		{
			Thread.sleep(10000);
			if(element.isDisplayed())
			{
				logger.info("Element is present");
				
				eleFound = true;
				break;
			}
		}
		try{
			assertEquals(bValue, eleFound);
		}catch(AssertionError e)
		{
			logger.error("Element is not found in webpage", e);
		}
	}
	
	public static List<WebElement> getElementList(WebDriver driver, String locator)
	{
		
		try{
			logger.info("Getting webelement list");
			elementList = driver.findElements(objLocator.getObjectLocatior(locator));
			
			int elementCnt = elementList.size();
			logger.info("Elements found : " + elementCnt);
		}catch(NoSuchElementException e)
		{
			logger.error("No element is found", e);
		}catch(Exception e)
		{
			logger.error("exception is occured...", e);
		}
			
		return elementList;
		
	}
	
	public static boolean retryingFindClick(WebElement element) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                element.click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
}
	
	public static String[]getTextFromElementList(List<WebElement> elementList)
	{
		logger.info("Getting text from web elements");
		
		int eleSize = elementList.size();
		logger.info("Number of Web Elements is :" + eleSize);
		
		//set String array to store elements text....
		String[] eleTxtArr = new String[eleSize];
		
		//set intital araay index value....
		int indxVal =0;
		
		for(WebElement element:elementList)
		{
			logger.info("Storing Web Element txt in string array...");
			eleTxtArr[indxVal] = element.getAttribute("aria-label");
			
			logger.info("Web Elements Txt :" + eleTxtArr[indxVal]);
			
			//increment index....
			indxVal++;
		}
		
		//print string array.....
		logger.info("Print String Web Element txt array ..:" + eleTxtArr.toString());
		
		return eleTxtArr;
	}
	
	public static void waitForPageLoaded(WebDriver driver) {
        
		logger.info("Checking Page Load status...");
		
		ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(pageLoadCondition);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
	
	public static void mouseHoverAction(WebDriver driver, WebElement element)
	{
		logger.info("instantiating Actions class....");
		action = new Actions(driver);
		
		//mouse hover to element...
		logger.info("mouse move to web element..");
		action.moveToElement(element);
		
		//action click...
		action.click();
		
		//perform....
		action.build();
	
		
	}
	
	public static void mouseHoverJScript(WebDriver driver, WebElement element)
	{
		logger.info("JS Mouse hovering on web element......");
		((JavascriptExecutor)driver).executeScript(mouseOverScript, element);
	}
	
	public static void mouseClickJScript(WebDriver driver, WebElement element)
	{
		logger.info("JS Mouse Clicking on web element......");
		((JavascriptExecutor)driver).executeScript(mouseClickScript, element);
	}
	
	public static void selectAutoSuggestionOptionsWithText(WebDriver driver, String locator ,String autoSuggText)
	{
		logger.info("select value from drop down box....");
		
		try{
			elementList = driver.findElements(objLocator.getObjectLocatior(locator));
			
			for(WebElement ele : elementList)
			{
				String getAutoSuggstDelivLocation = ele.getText();
				if(getAutoSuggstDelivLocation.equals(autoSuggText))
				{
					logger.info("Selecting auto suggestion options : " + getAutoSuggstDelivLocation);
					ele.click();
					//break the loop....
					break;
				}
			}
		}catch(NoSuchElementException e)
		{
			logger.error("No element is found", e);
		}catch(Exception e)
		{
			logger.error("exception is occured...", e);
		}
		
	}
	
		public static boolean checkDoubleNumbers(String input) {
	        logger.info("Checking double numbers....");
			try{
				Double.parseDouble(input);
					
			}catch(NumberFormatException e)
			{
				return false;
			}
			return true;
		}
		
		public static void toolTipImplementedUsingTitleAttribute(WebDriver driver, WebElement element)
		{
			logger.info("Checking tool tip message....");
			action = new Actions(driver);
			
			Action mouseOver = action.moveToElement(element).build();
			mouseOver.perform();
			
		}
		
		public static void clickByLinkTxt(WebDriver driver, String linkTxt)
		{
			logger.info("Clicking by Link name.....");
			driver.findElement(By.linkText(linkTxt)).click();;
		}
	
}

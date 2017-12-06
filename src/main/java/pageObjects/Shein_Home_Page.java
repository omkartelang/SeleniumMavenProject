package pageObjects;

import java.sql.Driver;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Constant;
import utility.RepositoryParser;
import utility.SetBrowser;

public class Shein_Home_Page {

	private static List<WebElement> elementList = null;
	private static WebElement element = null;
	private static Logger logger = Logger.getLogger(Shein_Home_Page.class.getName());
	private static RepositoryParser objLocator = null;
	
	static{
		try {
				objLocator = new RepositoryParser(Constant.objRepFilePath);
		} catch (Exception e) {
			
			logger.error("Exception is rasied for RepositoryParser",e);
		}
	}
	
	public static WebElement NewArrivalBannerLink(WebDriver driver)
	{
		logger.info("New arrival banner link...");
		element = driver.findElement(objLocator.getObjectLocatior("newArriavalLink"));
		
		return element;
	}
	
	public static WebElement getDropListElement(WebDriver driver)
	{
		logger.info("Get element for sort by drop down list...");
		
		element = driver.findElement(objLocator.getObjectLocatior("sortByDrpDwnList"));
		
		return element;
	}
	
	public static WebElement getNewArriValFrmDrpDwnList(WebDriver driver)
	{
		logger.info("Get web element for new arrival value in drop down list");
		element = driver.findElement(objLocator.getObjectLocatior("newArriValFrmDrpDwnList"));
		return element;
	}
	
	public static WebElement getElemNewItmInNewArrival(WebDriver driver)
	{
		logger.info("Get WebElement for item in new arrival....");
		element = driver.findElement(objLocator.getObjectLocatior("itemInNewArrival"));
		return element;
	}
	
	public static WebElement getElementForAddToBagForSelectedItem(WebDriver driver)
	{
		logger.info("get web element - add to bag for selected element");
		element = driver.findElement(objLocator.getObjectLocatior("addToBagForSelectedItm"));
		return element;
	}
	
	public static WebElement getElementForSetSize(WebDriver driver)
	{
		logger.info("get web element - set size for selected item...");
		element = driver.findElement(objLocator.getObjectLocatior("selectSizeForSelectedItm"));
		return element;
	}
	
	public static WebElement getElementForSubmitBtnForSelectedItem(WebDriver driver)
	{
		logger.info("get web element for submit button for selected item...");
		element = driver.findElement(objLocator.getObjectLocatior("subitBtnForselectedItm"));
		return element;
	}
	
	public static WebElement getElementForShopBagIcon(WebDriver driver)
	{
		logger.info("get web element for shot bag icon...");
		element = driver.findElement(objLocator.getObjectLocatior("shopBagIcon"));
		return element;
	}
	
	public static WebElement getElementForSetSizeItm(WebDriver driver)
	{
		logger.info("get web element for set size item");
		element = driver.findElement(objLocator.getObjectLocatior("setSize"));
		return element;
	}
	
	public static WebElement getElementForQtyItem(WebDriver driver)
	{
		logger.info("get web element for qty item..");
		element = driver.findElement(objLocator.getObjectLocatior("ItmQty"));
		return element;
	}

}

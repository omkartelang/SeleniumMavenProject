package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Constant;
import utility.RepositoryParser;

public class Zomato_Page {

	private static WebDriver driver=null;
	private static WebElement element=null;
	private static Logger logger = Logger.getLogger(Zomato_Page.class.getName());
	private static RepositoryParser objLocator = null;
	
	static{
		try {
				objLocator = new RepositoryParser(Constant.objRepFilePath);
		} catch (Exception e) {
			
			logger.error("Exception is rasied for RepositoryParser",e);
		}
	}
	
	public static class ZomatoHomePage
	{
		
		public static WebElement getElementForZomatoLogo(WebDriver driver)
		{
			logger.info("Getting web element for zomato logo...");
			element = driver.findElement(objLocator.getObjectLocatior("zomatoLogo"));
			return element;
		}
		
		public static WebElement getElementForZomatoPopularLocation(WebDriver driver)
		{
			logger.info("Getting web element for zomato popular lcoation - pune...");
			element = driver.findElement(objLocator.getObjectLocatior("zomatoPopularPuneLocation"));
			return element;
		}
		
		
	}
	
	public static class OrderFoodOnlinePage
	{
		public static WebElement getElementForDelivryLocation(WebDriver driver)
		{
			logger.info("Getting web element for deliver location.....");
			element = driver.findElement(objLocator.getObjectLocatior("deliLocationTxtBox"));
			return element;
		}
		
		public static WebElement getElementForOrderFoodOnline(WebDriver driver)
		{
			logger.info("Getting web element for Order Food Online Button....");
			element = driver.findElement(objLocator.getObjectLocatior("orderFoodOnlineBtn"));
			return element;
		}
	}
	
	public static class ResturantListPage
	{
		public static WebElement getElementForPurgVegQuikFilters(WebDriver driver)
		{
			logger.info("Getting web element for Pure Veg quik filter....");
			element = driver.findElement(objLocator.getObjectLocatior("quikVegFilterChkBox"));
			return element;
		}
	}
	
	public static class OrderMenuFood
	{
		public static WebElement getElementForMainCourseTab(WebDriver driver)
		{
			logger.info("Getting web Element for main coursee Tab...");
			element = driver.findElement(objLocator.getObjectLocatior("menuListForFood"));
			return element;
		}
		
		public static WebElement getElementForYourOrderContinueBtn(WebDriver driver)
		{
			logger.info("Getting web Element for your order continue button...");
			element = driver.findElement(objLocator.getObjectLocatior("yourOrdrContinueBtn"));
			return element;
		}
	}
	
	public static class AddDeliverAddressPopupWindow
	{
		public static WebElement getElementForCompleteAddress(WebDriver driver)
		{
			logger.info("Getting web Element for complete address text field....");
			element = driver.findElement(objLocator.getObjectLocatior("completeAddressTxtBox"));
			return element;
					
		}
		
		public static WebElement getElementForNickNameOption(WebDriver driver)
		{
			logger.info("Getting Web Element for NickName Option...");
			element = driver.findElement(objLocator.getObjectLocatior("nickNameWorkOptions"));
			return element;
		}
		
		public static WebElement getElementForCloseIconBox(WebDriver driver)
		{
			logger.info("Getting Web Element for Close Icon Box...");
			element = driver.findElement(objLocator.getObjectLocatior("addDelivAddrsCloseIcon"));
			return element;
		}
	}
	
	

}
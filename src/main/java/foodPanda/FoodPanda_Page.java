package foodPanda;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Constant;
import utility.RepositoryParser;

public class FoodPanda_Page {

	private static WebDriver driver=null;
	private static WebElement element=null;
	private static Logger logger = Logger.getLogger(FoodPanda_Page.class.getName());
	private static RepositoryParser objLocator=null;
	
	static{
		try {
				objLocator = new RepositoryParser(Constant.objRepFilePath);
		} catch (Exception e) {
			
			logger.error("Exception is rasied for RepositoryParser",e);
		}
	}
	
	public static class HomePage
	{
		public static WebElement getElementForLocationTxtField(WebDriver driver)
		{
			logger.info("Getting web element for Location Txt Field...");
			element = driver.findElement(objLocator.getObjectLocatior("fp_LocationTxtFiled"));
			return element;
		}
		
		public static WebElement getElementForAreaTxtField(WebDriver driver)
		{
			logger.info("Getting Web Element for Area Text Field..");
			element = driver.findElement(objLocator.getObjectLocatior("fp_AreaTxtField"));
			return element;
		}
		
		public static WebElement getElementForShowRestrntsBtn(WebDriver driver)
		{
			logger.info("Getting Web Element for Show Resatrunts Button....");
			element = driver.findElement(objLocator.getObjectLocatior("fp_ShowRestrntsBtn"));
			return element;
		}
	}
	
	public static class RestaurantProductListPage
	{
		public static WebElement getElementForProceedToCheckOutBtn(WebDriver driver)
		{
			logger.info("Get Web Element for Proceed to checkout button...");
			element = driver.findElement(objLocator.getObjectLocatior("fp_ProceedToCheckOutBtn"));
			return element;
		}
	}
	
	public static class ChooseProductChoicesPage
	{
		public static WebElement getElementForItalianBreadRadioBtn(WebDriver driver)
		{
			logger.info("Getting Web Element for Italian bread radio button...");
			element = driver.findElement(objLocator.getObjectLocatior("fp_ItalianBreadOptionRadioBtn"));
			return element;
		}
		
		public static WebElement getElementForMustardSauceCheckBox(WebDriver driver)
		{
			logger.info("Getting Web Element for Mustart Sauce Check box.....");
			element = driver.findElement(objLocator.getObjectLocatior("fp_MustartSauce"));
			return element;
		}
		
		public static WebElement getElementForExtraCheeseRadioBtn(WebDriver driver)
		{
			logger.info("Getting Web Element for Extra Cheese Radio Button...");
			element = driver.findElement(objLocator.getObjectLocatior("fp_ExtraCheeseRadioBtn"));
			return element;
		}
		
		public static WebElement getElementForToastedRadioBtn(WebDriver driver)
		{
			logger.info("Getting Web Element for toasted Radio Button...");
			element = driver.findElement(objLocator.getObjectLocatior("fp_ToastedRadioBtn"));
			return element;
		}
		
		public static WebElement getElementForContinueBtn(WebDriver driver)
		{
			logger.info("Getting Web Element for Continue button...");
			element = driver.findElement(objLocator.getObjectLocatior("fp_ChooseProductChoicesContinueBtn"));
			return element;
		}
		
		public static WebElement getElementForCucumberCheckBox(WebDriver driver)
		{
			logger.info("Getting Web Element for Cucumber check box...");
			element = driver.findElement(objLocator.getObjectLocatior("fp_VeggieMenuChkBoxCucumber"));
			return element;
		}
		
		public static WebElement getElementForSaltAndPepperRadioBtn(WebDriver driver)
		{
			logger.info("Getting Web Element for Salt and Pepper radio button...");
			element = driver.findElement(objLocator.getObjectLocatior("fp_SaltAndPepperRadioBtn"));
			return element;
		}
	}
	
	public static class LoginFoodPandaPage
	{
		public static WebElement getElementForLoginBtn(WebDriver driver)
		{
			logger.info("Getting Web Element for Login Button....");
			element = driver.findElement(objLocator.getObjectLocatior("fp_LoginBtn"));
			return element;
		}
	}
	
	
	
}

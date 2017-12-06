package foodPanda;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import pageObjects.Zomato_Page;
import userDefinedFuns.UserDefinedFunction;
import utility.SetBrowser;

import java.util.Iterator;
import java.util.List;

public class Assig_TCs05 {

	
	private static WebDriver driver=null;
	private static WebElement element=null;
	private static List<WebElement> elementList=null;
	private static Logger logger = Logger.getLogger(Assig_TCs05.class.getName());
	
	@BeforeTest
	public void setUp()
	{
		BasicConfigurator.configure();
	}
	
	@Test
	public void Test_TCs05()throws InterruptedException
	{
		//lauch browser....
		driver = SetBrowser.setUpBrowser(driver);
		
		//maximize the window...
		driver.manage().window().maximize();
		
		//open food pange web site....
		driver.get(" https://www.foodpanda.in/");
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//enter city location, area and then click on Show restaurant button....
		FoodPanda_Page.HomePage.getElementForLocationTxtField(driver).sendKeys("Pune");
		
		//sleep time
		Thread.sleep(5000);
		
		//enter area....
		FoodPanda_Page.HomePage.getElementForAreaTxtField(driver).sendKeys("Hinjewadi Phase 1");
		
		//sleep time
		Thread.sleep(5000);
		
		//click on show reastaurant button...
		FoodPanda_Page.HomePage.getElementForShowRestrntsBtn(driver).click();
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//get web element list for restaurants....
		elementList = UserDefinedFunction.getElementList(driver, "fp_RestaruntsList");
		
		for(WebElement ele:elementList)
		{
			//get restaurant name from list...
			String getRestaurantName = ele.getText();
			
			if(getRestaurantName.equalsIgnoreCase("Subway (Blue Ridge)"))
			{
				//select restaurant...
				ele.click();
				break;
			}
			
		}
		
		List<WebElement> menuList = UserDefinedFunction.getElementList(driver, "fp_ProductItemTitle");
		List<WebElement> addCartList = UserDefinedFunction.getElementList(driver, "fp_FirstAddToCardMenuBtn");
		
		Iterator<WebElement> menuListItr = menuList.iterator();
		
		Iterator<WebElement> addCartListItr = addCartList.iterator();
		
		while(menuListItr.hasNext() && addCartListItr.hasNext())
		{
			//get Menu item name...
			String getMenuItem = menuListItr.next().getText();
			WebElement eles = addCartListItr.next();
			logger.info("menu list" + getMenuItem);
			
			if(getMenuItem.contains("Hara Bhara Kebab Sub"))
			{
				//add menu item in cart....
				logger.info("got menu item list");
				eles.click();
				break;
			}
			
		}
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//select italian bread...
		FoodPanda_Page.ChooseProductChoicesPage.getElementForItalianBreadRadioBtn(driver).click();
		
		
		//select cucumber...
		FoodPanda_Page.ChooseProductChoicesPage.getElementForCucumberCheckBox(driver).click();
		
		//select mustartd sauce...
		FoodPanda_Page.ChooseProductChoicesPage.getElementForMustardSauceCheckBox(driver).click();
		
		//select salt and pepper....
		FoodPanda_Page.ChooseProductChoicesPage.getElementForSaltAndPepperRadioBtn(driver).click();
		
		//select extra cheese....
		FoodPanda_Page.ChooseProductChoicesPage.getElementForExtraCheeseRadioBtn(driver).click();
		
		//select toasted bread....
		FoodPanda_Page.ChooseProductChoicesPage.getElementForToastedRadioBtn(driver).click();
		
		//click on continue button.....
		FoodPanda_Page.ChooseProductChoicesPage.getElementForContinueBtn(driver).click();
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//click on checkout button....
		FoodPanda_Page.RestaurantProductListPage.getElementForProceedToCheckOutBtn(driver).click();
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//verify the it asks login.....
		element = FoodPanda_Page.LoginFoodPandaPage.getElementForLoginBtn(driver);
		assertEquals(element.isDisplayed(), true);
		
		//quite browser....
		driver.quit();
	}
	
}

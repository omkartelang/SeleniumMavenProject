package zomato;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.Zomato_Page;
import userDefinedFuns.UserDefinedFunction;
import utility.SetBrowser;

public class Assig_TCs_04 {

	private static WebDriver driver = null;
	private static WebElement element = null;
	private static List<WebElement> elementList = null;
	private static Logger logger = Logger.getLogger(Assig_TCs_04.class.getName());
	
	@BeforeTest
	public void setUp()
	{
		BasicConfigurator.configure();
	}
	
	@Test
	public void testAssigTC04() throws InterruptedException
	{
		logger.info("Launch firefox browser.....");
		driver = SetBrowser.setUpBrowser(driver);
		
		logger.info("Open zomato page....");
		driver.get("https://www.zomato.com/india");
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//maximize window...
		driver.manage().window().maximize();
		
		//mosue hover Zomato logo...
		element = Zomato_Page.ZomatoHomePage.getElementForZomatoLogo(driver);
		
		UserDefinedFunction.toolTipImplementedUsingTitleAttribute(driver, element);
		
		//get tool tip message....
		String toolTipMsg = element.getAttribute("title");
		logger.info("Tool tip message : " + toolTipMsg);
		
		//sleep time..
		Thread.sleep(5000);
		
		//click on pune restaurant link.....
		Zomato_Page.ZomatoHomePage.getElementForZomatoPopularLocation(driver).click();;
		
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//enter your delivery location....
		Zomato_Page.OrderFoodOnlinePage.getElementForDelivryLocation(driver).sendKeys("Hinjewadi");
		
		//sleep time...
		Thread.sleep(5000);
			
		//select hinjewadi phase 3 location....
		UserDefinedFunction.selectAutoSuggestionOptionsWithText(driver, "autoSuggestionDelivLocation", "Hinjawadi Phase III");
		
		//sleep time...
		Thread.sleep(1000);
		
				
		//click on Order food online....
		element = Zomato_Page.OrderFoodOnlinePage.getElementForOrderFoodOnline(driver);
		
		element.click();
		
		//wait for page to load.....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//get web element list for restaurant name and ratings....
		List<WebElement> restrntNameList = UserDefinedFunction.getElementList(driver, "restrntName");
		List<WebElement> restrntNameRatingList = UserDefinedFunction.getElementList(driver, "restrntNameRating");
		
		Iterator<WebElement> restrntName = restrntNameList.iterator();
		Iterator<WebElement> restrntRating = restrntNameRatingList.iterator();
		
		while(restrntName.hasNext() && restrntRating.hasNext())
		{
			String getTxtRating = restrntRating.next().getText();
			String restName = restrntName.next().getText();
			
			if(UserDefinedFunction.checkDoubleNumbers(getTxtRating))
			{
				Double rating = Double.parseDouble(getTxtRating);
				//logger.info("rating is : " + rating);
				
				if(rating < 3.0)
				{
					logger.info("Resurant name <=3.0 rating :" + restName);
				}
			}
			
			
		}
		
		//select quick filter - pure veg
		//Zomato_Page.ResturantListPage.getElementForPurgVegQuikFilters(driver).click();
		
		//wait for page to load....
		//UserDefinedFunction.waitForPageLoaded(driver);
		
		//get element list for resutants with pure veg filter..
		elementList = UserDefinedFunction.getElementList(driver, "restrntName");
		
		//set web element for restaurant - Tamanna Cafeteria”
		WebElement selectedRestaurant = null;
		
		for(WebElement ele: elementList)
		{
			logger.info("Resturant with Pure Veg....");
			logger.info(ele.getText());
			if(ele.getText().equals("Tamanna Cafeteria"))
			{
				selectedRestaurant = ele;
			}
		}
		
		//select restaurant Tamanna Cafeteria
		selectedRestaurant.click();
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//click on main course tab....
		Zomato_Page.OrderMenuFood.getElementForMainCourseTab(driver).click();
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//get element list....
		List<WebElement> menuFoodItemsList = UserDefinedFunction.getElementList(driver, "menuFoodItems");
		
		List<WebElement> menuFoodOptionBtnList = UserDefinedFunction.getElementList(driver, "menuVegTagOptionBtn");
		
		Iterator<WebElement> menuFoodItemItr = menuFoodItemsList.iterator();
		
		Iterator<WebElement> menuFoodOptionBtnItr = menuFoodOptionBtnList.iterator();
		
		//looping two list and select food items....
		while(menuFoodItemItr.hasNext() && menuFoodOptionBtnItr.hasNext())
		{
			String getTxtFoodMenuName = menuFoodItemItr.next().getText();
			WebElement eleOptionBtn = menuFoodOptionBtnItr.next();
			
			if(getTxtFoodMenuName.equalsIgnoreCase("Tamanna Special Veg"))
			{
				//click on menu option button .....
				eleOptionBtn.click();
				break;
			}
					
		}
		
		//wait for page to load....
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//click on Cotinue button to continue order....
		Zomato_Page.OrderMenuFood.getElementForYourOrderContinueBtn(driver).click();
		
		//enter deliver address.......
		Zomato_Page.AddDeliverAddressPopupWindow.getElementForCompleteAddress(driver).sendKeys("SQS India office Phase 3");
		
		//sleep time...
		Thread.sleep(1000);
		
		//click on close icon box...
		Zomato_Page.AddDeliverAddressPopupWindow.getElementForCloseIconBox(driver).click();
		
		//closing the browser...
		driver.quit();
		
	}
	
}

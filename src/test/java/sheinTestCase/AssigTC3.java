package sheinTestCase;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.Shein_Home_Page;
import userDefinedFuns.UserDefinedFunction;
import utility.SetBrowser;


public class AssigTC3 {

	private static WebDriver driver=null;
	private static WebElement element=null;
	private static List<WebElement> elementList=null;
	private static Actions action = null;
	private static Logger logger = Logger.getLogger(AssigTC3.class.getName());
	
	@BeforeTest
	public void setUp()
	{
		//setup configuration log4j...
		BasicConfigurator.configure();
	}
	
	@Test
	public void assg_TCs_3() throws InterruptedException
	{
		//launch firefox browser...
		logger.info("Launching firefox browser.....");
		driver = SetBrowser.setUpBrowser(driver);
		
		//maximize window...
		driver.manage().window().maximize();
		
		
		//launch webpage...
		logger.info("Launching shein website...");
		//SetBrowser.openWebPage(driver);
		driver.get("http://www.shein.in/");
		
		//wait for page to load
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//get main menus list.....
		elementList = UserDefinedFunction.getElementList(driver, "sheinMenuList");
		
		int listSize = elementList.size();
		
		logger.info("list size is : " + listSize);
		int lisIndex =1;
		for(WebElement ele:elementList)
		{
			logger.info("Mouse hover on main menus....");
			UserDefinedFunction.mouseHoverJScript(driver, ele);
			
			String getTextMainMenu = ele.getText();
			logger.info("Main menu is :" + getTextMainMenu);
			Thread.sleep(5000);
			
			List<WebElement>elementSubMenuList = driver.findElements(By.xpath("//ul[@class='nav-wrap j-nav-wrap']/li["+lisIndex+"]/div/div/div[2]//child::a"));
			
			for(WebElement eles: elementSubMenuList)
			{
				String getTextSubMenu = eles.getText();
				logger.info("Sub menu is :" + getTextSubMenu);
			}
			
			//increment list index...
			lisIndex++;
		}
		
		//click on what's new arrival link....
		logger.info("Click on new arrival slink ....");
		element = Shein_Home_Page.NewArrivalBannerLink(driver);
		
		UserDefinedFunction.mouseClickJScript(driver, element);
		
		//wait for page to load
		UserDefinedFunction.waitForPageLoaded(driver);
		
		UserDefinedFunction.waitForElementPresent(driver, "sortByDrpDwnList");
		
		logger.info("Click on sort by drop down list");
		Shein_Home_Page.getDropListElement(driver).click();
		
		logger.info("select new arrival from sory by drop down list");
		Shein_Home_Page.getNewArriValFrmDrpDwnList(driver).click();
		
		//wait for page to load
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//select any item from new arrival.....
		element = Shein_Home_Page.getElemNewItmInNewArrival(driver);
		//element=driver.findElement(By.xpath("//*[@id='productsContent1_goods']/div[1]/div[3]/div[1]/div[1]/a/img"));
		
		//mouse hover on selected item in new arrival....
		UserDefinedFunction.mouseHoverJScript(driver, element);
		
		//((JavascriptExecutor)driver).executeScript(javaScript, element);
		
		//click on Add to Bag.....
		Shein_Home_Page.getElementForAddToBagForSelectedItem(driver).click();
		//driver.findElement(By.xpath("//*[@id='productsContent1_goods']/div[1]/div[3]/div[2]/div/div[contains(@onclick,'addToBagListPage')]")).click();
		
		//get web element for set size medium - m....
		WebElement ele = Shein_Home_Page.getElementForSetSize(driver);
		
		//mouse hover on selected item in new arrival....
		UserDefinedFunction.mouseHoverJScript(driver, element);
		
		//String getActualPrice = driver.findElement(By.xpath("//*[@id='productsContent1_goods']/div[1]/div[3]//child::span[contains(@id,'special_price_member_price_off')]/strong")).getText();
		
		//js mouse click on set size medium....
		UserDefinedFunction.mouseClickJScript(driver, ele);
		
		//get web element for submit button...
		element = Shein_Home_Page.getElementForSubmitBtnForSelectedItem(driver);
		
		//js mouse click on submit button....
		UserDefinedFunction.mouseClickJScript(driver, element);
		
		//wait for page to load...
		UserDefinedFunction.waitForPageLoaded(driver);
		
		//get element for shop bag icon
		element = Shein_Home_Page.getElementForShopBagIcon(driver);
		
		//mouse hover on shot bag icon....
		UserDefinedFunction.mouseHoverJScript(driver, element);
		
		//js mouse click on shop bag icon....
		UserDefinedFunction.mouseClickJScript(driver, element);
		
		//wait for page to load...
		UserDefinedFunction.waitForPageLoaded(driver);
		
		element = Shein_Home_Page.getElementForSetSizeItm(driver);	
		String getSize = element.getText();
		//String getSize = driver.findElement(By.xpath("//*[@method='post']//child::span[@class='item_content']")).getText();

		assertEquals(getSize, "L");
		element = Shein_Home_Page.getElementForQtyItem(driver);
		
		String getQty = element.getText();
		//String getQty = driver.findElement(By.xpath("//*[@method='post']//child::input[contains(@name,'quantity')]")).getText();
		
		assertEquals(getQty,"1");
		
		//String getPrice = driver.findElement(By.xpath("//*[@method='post']//child::*[contains(@class,'item_total goods_price_current_price')]")).getText();
		
		//assertEquals(getPrice,getActualPrice);
		
		//quit the browser....
		driver.quit();
	}
	
}

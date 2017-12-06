package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
public class Home_Page {

	private static WebElement element = null;
	
	public static WebElement lnk_MyAccount(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//a[@class='account_icon']"));;
		return element;
	}
	
	public static WebElement link_LogOut(WebDriver driver)
	{
		element = driver.findElement(By.id("account_logout"));
		return element;
	}
}


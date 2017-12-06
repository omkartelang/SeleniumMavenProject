package appModule;

import org.openqa.selenium.WebDriver;

import pageObjects.Home_Page;
import pageObjects.LogIn_Page;

public class SignIn_Action {

	public static void execute(WebDriver driver, String sUserName, String sPassword)
	{
		Home_Page.lnk_MyAccount(driver).click();
		
		LogIn_Page.txtbx_UserName(driver).sendKeys(sUserName);
		
		LogIn_Page.txtbx_Password(driver).sendKeys(sPassword);
		
		LogIn_Page.btn_Login(driver).click();
	}
}

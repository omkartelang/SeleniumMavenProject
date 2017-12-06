package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LogInPage extends TestBase {
	
	//page factor - OR...
	@FindBy(name="username")
	WebElement userName;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//*[@id='loginForm']/div/div/input")
	WebElement loginBtn;
	
	@FindBy(xpath="//*[text()='Sign Up']")
	WebElement singUpBtn;
	
	@FindBy(xpath="//*[@class='navbar-brand']/img")
	WebElement CRMLogo;
	
	//initializing the page objects....
	public LogInPage()
	{
		//PageFactory.initElements(driver, LogInPage.class); //-- this points all class objects (nothing but all web element variables..
		PageFactory.initElements(driver, this);
	}
	
	//Actions....
	public String validationLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean validateCRMLogo()
	{
		return CRMLogo.isDisplayed();
	}
	
	public HomePage login(String username, String pwd)
	{
		userName.sendKeys(username);
		password.sendKeys(pwd);
		loginBtn.click();
		
		return new HomePage();
	}

}

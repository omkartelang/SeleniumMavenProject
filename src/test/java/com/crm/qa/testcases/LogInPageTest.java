package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LogInPage;

public class LogInPageTest extends TestBase {
	
	LogInPage logInPage;
	HomePage homePage;
	
	public LogInPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		logInPage = new LogInPage();
	}
	
	@Test(priority=1)
	public void logInPageTitleTest()
	{
		String title = logInPage.validationLoginPageTitle();
		System.out.println("Title is : " + title);
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority=2)
	public void CRMLogoImageTest()
	{
		boolean flag = logInPage.validateCRMLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void logInTest()
	{
		homePage = logInPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}

package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.crm.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver=null;
	public static Properties prop=null;
	private static Logger logger = Logger.getLogger(TestBase.class.getName());
	private static FileInputStream fiStream = null;
	public static final String envPropFileName = "C:\\ToolsQA\\SeleniumMavenProject\\src\\main\\java\\com\\crm\\qa\\config\\config.properties";
	
	public TestBase()
	{
		//method to read properties file
		try
		{
			logger.info("reading environment properties file");
			fiStream = new FileInputStream(envPropFileName);
			prop = new Properties();
			prop.load(fiStream);
			
		}catch(FileNotFoundException e)
		{
			logger.error("Environment prop file is not found", e);
		}catch(IOException e)
		{
			logger.error("Environment prop file is not loaded", e);
		}
	}
	
	public static void initialization()
	{
		String browserName = prop.getProperty("browser");
		
			if(browserName.equalsIgnoreCase("Mozilla"))
			{
				driver = new FirefoxDriver();
				
			}
			
			if(browserName.equalsIgnoreCase("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver",
						"C://eclipse-standard-kepler-SR2-win32-x86_64//chromedriver_win32//chromedriver.exe");
				driver = new ChromeDriver();
				
			}
			if(browserName.equalsIgnoreCase("Iexplore"))
			{
				System.setProperty("webdriver.ie.driver",
						"D:/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
			}
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			
			driver.get(prop.getProperty("url"));
	}

}

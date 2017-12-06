package com.crm.qa.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;

public class Test1 {

	public static WebDriver driver=null;
	public static Properties prop=null;
	//private static Logger logger = Logger.getLogger(TestBase.class.getName());
	private static FileInputStream fiStream = null;
	public static final String envPropFileName = "C:\\ToolsQA\\SeleniumMavenProject\\src\\main\\java\\com\\crm\\qa\\config\\config.properties";
	
	@Test
	public void TestClass()
	{
		//method to read properties file
		try
		{
			//logger.info("reading environment properties file");
			fiStream = new FileInputStream(envPropFileName);
			//prop.load(fiStream);
			
		}catch(FileNotFoundException e)
		{
			
		}catch(IOException e)
		{
			
		}
	}
	
}

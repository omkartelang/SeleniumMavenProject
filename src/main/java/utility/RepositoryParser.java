package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class RepositoryParser {
	
	private static Logger logger = Logger.getLogger(RepositoryParser.class.getName());
	private static FileInputStream stream = null;
	private Properties propFile = new Properties();
	
	public RepositoryParser(String fileName)throws Exception
	{
		try {
			//open Object Repository properties file.....
			logger.info("Opening ObjectRepo.properties file");
			stream = new FileInputStream(fileName);
			
			logger.info("Object repository file gets loading");
			propFile.load(stream);
			
		} catch (FileNotFoundException e) {
			//catch file not found exception
			logger.error("File not found - ObjectRepo.properties file",e);
			
		}catch(IOException e)
		{
			//catch file not found exception
			logger.error("Property file not loading IO exception - ObjectRepo.properties fiel",e);
		}
	}
	
	public By getObjectLocatior(String locatorName)
	{
		String locatorProperty = propFile.getProperty(locatorName);
		logger.info(locatorProperty.toString());
		String locatorType = locatorProperty.split(";")[0];
		String locatorValue = locatorProperty.split(";")[1];
		
		By locator = null;
		switch(locatorType)
		{
		case "Id":
			logger.info("locator Type - ID");
			locator = By.id(locatorValue);
			break;
		case "Name":
			logger.info("locator Type - Name");
			locator = By.name(locatorValue);
			break;
		case "CssSelector":
			logger.info("locator Type - CssSelector");
			locator = By.cssSelector(locatorValue);
			break;
		case "LinkText":
			logger.info("locator Type - LinkText");
			locator = By.linkText(locatorValue);
			break;
		case "PartialLinkText":
			logger.info("locator Type - PartialLinkText");
			locator = By.partialLinkText(locatorValue);
			break;
		case "TagName":
			logger.info("locator Type - TagName");
			locator = By.tagName(locatorValue);
			break;
		case "Xpath":
			logger.info("locator Type - Xpath");
			locator = By.xpath(locatorValue);
			break;
		}
		logger.info("locator is dervied");
		return locator;
		
	}
}

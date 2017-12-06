package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Constant;
import utility.RepositoryParser;

public class GooglePage {

	private static WebDriver driver = null;
	private static WebElement element = null;
	private static RepositoryParser objLocator = null;
	private static Logger logger = Logger.getLogger(GooglePage.class.getName());
	
	static{
			try {
					objLocator = new RepositoryParser(Constant.objRepFilePath);
			} catch (Exception e) {
				
				logger.error("Exception is rasied for RepositoryParser",e);
			}
	}
	
	public static WebElement googleSrchTxtField(WebDriver driver)
	{
		logger.info("entering txt in google search field");
		element = driver.findElement(objLocator.getObjectLocatior("googleSrchTxtBox"));
		return element;
		
	}
	
	
}

package utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SetBrowser {

	//private static WebDriver driver = null;
	//private static WebElement element = null;
	private static EnvironmentParser objEnvVar = null;
	private static Logger logger = Logger.getLogger(SetBrowser.class.getName());
	
	static{
			try {
				
					objEnvVar = new EnvironmentParser(Constant.objEnvFilePath);
				
			} catch (Exception e) {
				
				logger.error("Exception is rasied for EnvironmentParser",e);
			}
	}
	
	public static WebDriver setUpBrowser(WebDriver driver)
	{
		if(objEnvVar.getEnvironmentVar("browser").equalsIgnoreCase("Mozilla"))
		{
			driver = new FirefoxDriver();
			
		}
		
		if(objEnvVar.getEnvironmentVar("browser").equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver",
					"C://eclipse-standard-kepler-SR2-win32-x86_64//chromedriver_win32//chromedriver.exe");
			driver = new ChromeDriver();
			
		}
		if(objEnvVar.getEnvironmentVar("browser").equalsIgnoreCase("Iexplore"))
		{
			System.setProperty("webdriver.ie.driver",
					"D:/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
		}
		
		return driver;
	}
	
	public static void openWebPage(WebDriver driver)
	{
		logger.info("Opening WebPage....");
		driver.get(objEnvVar.getEnvironmentVar("browserURL"));
	}
	
}

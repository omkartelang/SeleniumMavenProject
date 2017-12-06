package multiBrowser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class ParallelTest {

	private static WebDriver fdriver;
	private static WebDriver cdriver;
	private static WebDriver iedriver;
	String baseURL = "http://www.google.com/";

	@Parameters({ "browser" })
	@BeforeTest
	public void openBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				fdriver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\eclipse-standard-kepler-SR2-win32-x86_64\\chromedriver_win32_2.23\\chromedriver.exe");
				cdriver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						"C:\\eclipse-standard-kepler-SR2-win32-x86_64\\IEDriverServer_x64_3.7.0\\IEDriverServer.exe");
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				  ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				iedriver = new InternetExplorerDriver(ieCapabilities);
				//ieCapabilities.setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true );
			}
		
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void login_TestCase() throws InterruptedException {
		Thread.sleep(5000);
		fdriver.navigate().to(baseURL);
		Thread.sleep(5000);
		cdriver.navigate().to(baseURL);
		Thread.sleep(5000);
		iedriver.navigate().to(baseURL);
		Thread.sleep(5000);
                //do something
	}

	/*@Test
	public void search_TestCase() {
		driver.navigate().to(baseURL);
             //do something
	}*/

	@AfterTest
	public void closeBrowser() {
		//driver.quit();
	}
}
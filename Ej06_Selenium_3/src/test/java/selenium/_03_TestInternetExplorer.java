package selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class _03_TestInternetExplorer {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.ie.driver", 
				"src/test/resources/drivers/IEDriverServer/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}
	
	@Test
	public void goToGoogle() {
		driver.get("http://www.google.com");
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}

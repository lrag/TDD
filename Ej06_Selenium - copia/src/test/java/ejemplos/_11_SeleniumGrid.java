package ejemplos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;


//Esto es para ejecutar los test a traves del grid hub
//hay que levantar el grid y una instancia de navegador chrome (o el que toque)
//
//java -jar selenium-server-standalone-<version>.jar -role hub
//
//podemos ver que todo esta bien en http://localhost:4444/grid/console
//
//Damos de alta un nodo con un máximo de dos instancias de Firefox
//java -Dwebdriver.gecko.driver=drivers/geckodriver/geckodriver.exe -jar selenium-server-standalone-3.141.59.jar -role webdriver -browser "browserName=firefox,maxInstances=2,platform=WINDOWS" -hubHost localhost –port 6666
//Si necesitamos chrome
//java -Dwebdriver.chrome.driver=drivers/chromedriver/chromedriver.exe -jar selenium-server-standalone-3.141.59.jar -role webdriver -browser "browserName=chrome,maxInstances=2,platform=WINDOWS" -hubHost localhost -port 7777

public class _11_SeleniumGrid {
	
	private static WebDriver driver;
	private static String nodeUrl;
	
	@BeforeAll
	public static void setUp() throws MalformedURLException {
		
		nodeUrl = "http://localhost:4444/wd/hub";
		
		//System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver/chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		
		//le decimos que es una instancia de windows
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		//ahora utilizamos la clase RemoteWebDriver
		driver = new RemoteWebDriver(new URL(nodeUrl), options);
	}
	
	@Test
	public void searchReact() {
		driver.get("http://www.google.com");
		assertEquals(driver.getTitle(), "Google");
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}

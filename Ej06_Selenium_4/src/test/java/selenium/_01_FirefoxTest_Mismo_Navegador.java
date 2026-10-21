package selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


//                                        //
// En estos test se comparte el navegador //
//                                        //

public class _01_FirefoxTest_Mismo_Navegador {
	
	private static WebDriver driver;
	
	//Crear el driver y abrir el navegador consume mucho tiempo
	//Es conveniente crearlo antes de todos los test (o usar selenium grid)
	@BeforeAll
	public static void setUp() {
		//System.setProperty("webdriver.gecko.driver", 
		//		"src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void goToGoogle() {
		driver.get("http://www.google.com");	
	}
	
	@Test
	public void goToWikipedia() {
		driver.get("http://www.wikipedia.com");	
	}	
	
	@AfterAll
	public static void tearDown() {
		//Comentamos esta línea para que no se cierre el navegador 
		//despues del test, pero es necesaria:		
		//driver.quit();
	}
	
}

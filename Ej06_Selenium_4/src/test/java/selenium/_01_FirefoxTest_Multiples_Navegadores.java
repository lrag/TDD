package selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


//                                //
// Cada test utiliza su navegador //
//                                //

public class _01_FirefoxTest_Multiples_Navegadores {
	
	private WebDriver driver;

	@BeforeEach
	public void abrirNavegador() {
		driver = new FirefoxDriver();
	}
	
	@AfterEach
	public void cerrarNavegador() {
		//driver.quit();
	}
	
	@Test
	public void goToGoogle() {
		driver.get("http://www.google.com");
	}
	
	@Test
	public void goToWikipedia() {
		driver.get("http://www.wikipedia.com");	
	}	
	
}

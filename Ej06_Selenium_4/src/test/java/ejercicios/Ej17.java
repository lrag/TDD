package ejercicios;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Ejercicio 17
 * Navegar a la p�gina https://es.wikipedia.org/wiki/Wikipedia:Portada
 * Crear instancia de WikipediaMainPage
 * Comprobar que el placeholder es correcto
 * Buscar 'Selenium'
 * Comprobar que estamos en la p�gina correcta
 * Crear clase WikipediaMainPage
 * Crear el constructor
 * Crear m�todo para obtener el campo de b�squeda
 * Crear m�todo para obtener el bot�n de b�squeda
 * Crear m�todo para obtener el link de crear cuenta
 * Crear m�todo para obtener el link con el id 'pt-anoncontribs'
 * Crear m�todo para establecer el texto a buscar
 * Crear m�todo para realizar la b�squeda
 * Crear m�todo para navegar al link de contributors
 * Crear m�todo para crear una cuenta
 * Crear m�todo para obtener el texto del placeholder
 */
public class Ej17 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(Ej17WikipediaMainPage.url);
	}

	@Test
	public void testSearch() {
		Ej17WikipediaMainPage page = new Ej17WikipediaMainPage(driver);
		assertEquals(page.getPlaceholderText(), "Buscar en Wikipedia");
		
		page.setSearchText("Selenium");
		page.search();
		
		assertEquals(driver.getTitle(), "Selenium - Wikipedia, la enciclopedia libre");
	}

	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}

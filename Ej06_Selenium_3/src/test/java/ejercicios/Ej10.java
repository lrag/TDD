package ejercicios;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Ejercicio 10
 * Navegar a 'http://es.wikipedia.org/wiki/Especial:Contribuciones'
 * Obtener los radio buttons
 * Comprobar que uno est� seleccionado y el otro no
 * Seleccionar el que no est� seleccionado
 * Comprobar que el que no estaba seleccionado ahora lo est�, y el otro no lo est�
 */
public class Ej10 {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://es.wikipedia.org/wiki/Especial:Contribuciones");
	}
	
	@Test
	public void testEjercicio10() {
		WebElement newbie = driver.findElement(By.id("newbie"));
		WebElement user = driver.findElement(By.id("user"));
		
		assertFalse(newbie.isSelected());
		assertTrue(user.isSelected());
		
		newbie.click();

		assertTrue(newbie.isSelected());
		assertFalse(user.isSelected());
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}

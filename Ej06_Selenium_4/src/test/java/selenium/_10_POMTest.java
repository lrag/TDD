package selenium;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//Esta clase utilizará _12_POM para hacer las pruebas
public class _10_POMTest {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	//@Test
	public void testSinObjetoPom() throws InterruptedException {
		
		driver.get("http://localhost:8080/Ej06_Selenium_4/formulario.html");
		
		WebElement tfTtitulo       = driver.findElement(By.name("titulo"));
		WebElement tfDirector     = driver.findElement(By.name("director"));
		WebElement tfGenero       = driver.findElement(By.name("genero"));
		WebElement tfFechaEstreno = driver.findElement(By.name("fechaEstreno"));
		
		WebElement btnInsertar  = driver.findElement(By.id("btnInsertar"));

		tfTtitulo.clear();
		tfDirector.clear();
		tfGenero.clear();
		tfFechaEstreno.clear();
		
		Thread.sleep(750);
		tfTtitulo.sendKeys("El último grán heroe");
		Thread.sleep(750);
		tfDirector.sendKeys("John McTiernan");
		Thread.sleep(750);
		tfGenero.sendKeys("Accion");
		Thread.sleep(750);
		tfFechaEstreno.sendKeys("1993");	
		
		btnInsertar.click();
		
		//Asertos...
		//...
	}
	
	@Test
	public void testInsertar() throws InterruptedException {

		driver.get("http://localhost:8080/Ej06_Selenium_4/formulario.html");
		
		_10_FormularioPeliculaPOM page = new _10_FormularioPeliculaPOM(driver);

		page.getTfTitulo().clear();
		page.getTfDirector().clear();
		page.getTfGenero().clear();
		page.getTfFechaEstreno().clear();		
		
		Thread.sleep(750);
		page.getTfTitulo().sendKeys("El último grán heroe");
		Thread.sleep(750);
		page.getTfDirector().sendKeys("John McTiernan");
		Thread.sleep(750);
		page.getTfGenero().sendKeys("Accion");
		Thread.sleep(750);
		page.getTfFechaEstreno().sendKeys("1993");
		Thread.sleep(1000);
		
		page.getBtnInsertar().click();
//		
		//asertos necesarios
	}
	
	@Test
	public void testSearchModificar() throws InterruptedException {
		
		driver.get("http://localhost:8080/Ej06_Selenium_4/formulario.html");
		
		_10_FormularioPeliculaPOM page = new _10_FormularioPeliculaPOM(driver);
		
		Thread.sleep(750);
		page.getTfTitulo().sendKeys("El último grán heroe");
		Thread.sleep(750);
		page.getTfDirector().sendKeys("John McTiernan");
		Thread.sleep(750);
		page.getTfGenero().sendKeys("Accion");
		Thread.sleep(750);
		page.getTfFechaEstreno().sendKeys("1993");
		Thread.sleep(1000);
		
		page.getBtnModificar().click();
		
		//asertos necesarios
	}

	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
}

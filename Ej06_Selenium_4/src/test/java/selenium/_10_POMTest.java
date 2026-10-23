package selenium;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//Esta clase utilizará _12_POM para hacer las pruebas
public class _10_POMTest {
	
	/*
	//Mismo navegador para todados los test de esta clase	
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		//System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
	*/
	
	//Un navegador para cada test de esta clase
	private WebDriver driver;
	
	@BeforeEach
	public void setUp() {
		//System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}	
	@AfterEach
	public void afterEach() {
		//driver.quit();
	}

	//@Test
	public void testSinObjetoPom() throws InterruptedException {
		
		driver.get("http://localhost:8080/Ej06_Selenium_4/formulario.html");
		
		WebElement tfTtitulo      = driver.findElement(By.name("titulo"));
		WebElement tfDirector     = driver.findElement(By.name("director"));
		WebElement tfGenero       = driver.findElement(By.name("genero"));
		WebElement tfFechaEstreno = driver.findElement(By.name("fechaEstreno"));
		
		WebElement btnInsertar  = driver.findElement(By.id("btnInsertar"));

		//Comprobamos que las cajas de texto están vacías
		Assertions.assertEquals("", tfTtitulo.getDomAttribute("value"));
		Assertions.assertEquals("", tfDirector.getDomAttribute("value"));
		Assertions.assertEquals("", tfGenero.getDomAttribute("value"));
		Assertions.assertEquals("", tfFechaEstreno.getDomAttribute("value"));
		
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

		//Comprobamos que las cajas de texto están vacías
		Assertions.assertEquals("", page.getTfTitulo().getDomAttribute("value"));
		Assertions.assertEquals("", page.getTfDirector().getDomAttribute("value"));
		Assertions.assertEquals("", page.getTfGenero().getDomAttribute("value"));
		Assertions.assertEquals("", page.getTfFechaEstreno().getDomAttribute("value"));	
		
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
	public void testModificar() throws InterruptedException {
		
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

}

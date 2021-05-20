package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

@Execution(ExecutionMode.SAME_THREAD)
public class _06_SeleniumAPI {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", 
				"src/test/resources/drivers/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void testDoubleClick() throws InterruptedException {
		//driver.get("http://cookbook.seleniumacademy.com/DoubleClickDemo.html");
		driver.get("http://localhost:8080/Ej06_Selenium/DoubleClickDemo.html");
		WebElement mensaje = driver.findElement(By.id("message"));
		
		Thread.sleep(1000);
		//La clase Action nos proporciona acciones que podemos hacer
		Actions actions = new Actions(driver);
		actions.doubleClick(mensaje).perform();
		assertEquals("rgba(255, 255, 0, 1)", mensaje.getCssValue("background-color"));

		Thread.sleep(2000);
	}
	
	@Test
	public void testDragAndDrop() throws InterruptedException {
		//driver.get("http://cookbook.seleniumacademy.com/DragDropDemo.html");
		driver.get("http://localhost:8080/Ej06_Selenium/DragAndDropDemo.html");
		
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		
		Thread.sleep(1000);
		
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).perform();
		assertEquals(target.getText(), "Dropped!");
	
		Thread.sleep(2000);
	}
	
	@Test
	public void testJavascript() throws InterruptedException {
		driver.get(Constantes.URL);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("saludar()");
		
		//seleccionamos el alert que se nos muestra en pantalla
		//con switchTo indicamos el elemento al que queremos acceder
		Alert alert = driver.switchTo().alert();
		assertEquals(alert.getText(), "Hola mundo!!!");
		
		Thread.sleep(2000);
		
		alert.accept();
	}

	//Este no tiene que fallar, generamos las capturas
	//beforeDragAndDrop.png
	//afterDragAndDrop.png
	@Test
	public void testScreenshots() throws IOException {
		//driver.get("http://cookbook.seleniumacademy.com/DragDropDemo.html");
		driver.get("http://localhost:8080/Ej06_Selenium/DragAndDropDemo.html");
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		
		//Utilizamos una referencia TakesScreenshot para tomar capturas de pantalla
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		//Tomamos una captura de la pantalla
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		//Copiamos la captura de pantalla de la memoria a un directorio fisico
		FileUtils.copyFile(srcFile, new File("src/test/resources/screenshots/beforeDragAndDrop.png"));
		
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).perform();
		assertEquals(target.getText(), "Dropped!");
		
		srcFile = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("src/test/resources/screenshots/afterDragAndDrop.png"));
	}

	//Podemos hacer test sobre las cookies a traves del metodo manage()
	@Test
	public void testCookies() {
		driver.get(Constantes.URL);
		Cookie cookie = new Cookie("cookie_selenium", "Cookie creada por nosostros");
		driver.manage().addCookie(cookie);
		
		//Comprobamos que la cookie creada por nososotros esta
		Cookie cookie2 = driver.manage().getCookieNamed("cookie_selenium");
		assertEquals("Cookie creada por nosostros", cookie2.getValue());
		
		//Borramos la cookie y comprobamos
		driver.manage().deleteCookieNamed("cookie_selenium");
		cookie2 = driver.manage().getCookieNamed("cookie_selenium");
		assertNull(cookie2);
	}
	
	//Eventos
	//Este SI tiene que fallar para que cree una captura de pantalla, en la carpeta de resources
	//onException.png
	@Test
	public void testEventosWebDriver() {
		
		//Con esta clase se nos permite escuchar eventos lanzados por el driver
		EventFiringWebDriver eventDriver = 
			new EventFiringWebDriver(driver);
		//Esta clase le hemos creado nosostros y será la que reciba los eventos
		_06_TraceListener listener = new _06_TraceListener();
		eventDriver.register(listener);

		//Cargamos Wikipedia
		eventDriver.get("https://es.wikipedia.org");

		//Dispara los eventos 'beforeFindBy' y 'afterFindBy'
		WebElement button = eventDriver.findElement(By.id("searchButton"));
		button.click();
		
		//Buscamos algo que no existe para que se produzca una excepción y se
		//lance el evento 'onException'
		WebElement input = eventDriver.findElement(By.id("searchInput_TROLOLO"));
		input.sendKeys("Selenium");
		
		/*
		//Por si no hay internet 
		eventDriver.get(Constantes.URL);
		eventDriver
			.findElement(
				By.id("userTrololo")).getTagName();*/
		
	}
	
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
}

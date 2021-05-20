package selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

//Para crear enventos en Selenium
public class _06_TraceListener implements WebDriverEventListener {

	//Entra el webDriver que desencadena el evento
	
	//Evento despues de aceptar un alert
	@Override
	public void afterAlertAccept(WebDriver webDriver) {
		
	}

	//Evento despues de dismiss de alert
	@Override
	public void afterAlertDismiss(WebDriver webDriver) {
		
	}

	@Override
	public void afterChangeValueOf(WebElement WebElement, WebDriver webDriver, CharSequence[] charSequence) {
		
	}

	@Override
	public void afterClickOn(WebElement WebElement, WebDriver webDriver) {
		
	}

	@Override
	public void afterFindBy(By id, 
							WebElement WebElement, 
							WebDriver webDriver) {
		
		System.out.print("Se ha buscado por el id:"+id);
		if(WebElement == null) {
			System.out.println(". NO EXISTE");
		} else {
			System.out.println(". EXISTE");
		}		
		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		
	}

	@Override
	public void afterNavigateBack(WebDriver webDriver) {
		
	}

	@Override
	public void afterNavigateForward(WebDriver webDriver) {
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver webDriver) {
		
	}

	@Override
	public void afterNavigateTo(String url, WebDriver webDriver) {

		System.out.println("Navegando a:"+url);
		
	}

	@Override
	public void afterScript(String arg0, WebDriver webDriver) {
		
	}

	@Override
	public void afterSwitchToWindow(String arg0, WebDriver webDriver) {
		
	}

	@Override
	public void beforeAlertAccept(WebDriver webDriver) {
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver webDriver) {
		
	}

	//Este método se llama antes que los métodos
	//clear() y sendKeys().
	@Override
	public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] arg2) {
		System.out.println("BeforeChangeValueOf: " + webElement);
	}

	//Este método se llama antes que el método
	//click().
	@Override
	public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
		System.out.println("BeforeClickOn: " + webElement);
	}

	//Este método se llama antes que los métodos
	//findElement() y findElements().
	@Override
	public void beforeFindBy(By by, WebElement WebElement, WebDriver arg2) {
		System.out.println("BeforeFindBy: " + by);
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		
	}

	@Override
	public void beforeNavigateBack(WebDriver webDriver) {
		
	}

	@Override
	public void beforeNavigateForward(WebDriver webDriver) {
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver webDriver) {
		
	}

	//Evento que se lanza antes de ejecutar el get()
	@Override
	public void beforeNavigateTo(String url, WebDriver webDriver) {
		System.out.println("BeforeNavigateTo: " + url);
		
	}

	@Override
	public void beforeScript(String arg0, WebDriver webDriver) {
		
	}

	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver webDriver) {
		
	}

	//Evento que se lanza cuando ocurre alguna excepcion
	@Override
	public void onException(Throwable arg0, WebDriver webDriver) {

		File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("src/test/resources/screenshots/onException.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
	
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
	
	}

}



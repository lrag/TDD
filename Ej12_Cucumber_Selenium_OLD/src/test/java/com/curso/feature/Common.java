package com.curso.feature;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.curso.util.LoginUtil;
import com.curso.util.WebDriverUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

//
//En esta clase colocamos los métodos del ciclo de vida
//

public class Common {
	
    private final WebDriver driver;

    //Inyeccion de dependencias
    //Cucumber cuenta con una rudimentaria inyección de dependencias
    public Common(WebDriverUtil webDriverUtil) {
        this.driver = webDriverUtil.getDriver();
    }
    
	@Before("@Autenticado")
	public void beforeEach() {
		LoginUtil.login(driver, "aaa", "bbb");		
	}
	
	@Before("@Tocoto")
	public void beforeEach2() {
		System.out.println("ARSA vamos que nos vamos");	
	}	
	
	@After
	public void afterEach(Scenario scenario) {
		System.out.println("AFTER EACH");
	    if (scenario.isFailed()) {
	    	System.out.println("ESCENARIO FALLIDO");
	        byte[] captura = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	        scenario.attach(captura, "image/png", "captura-fallo");
	    }
	    //driverManager.getDriver().quit();
	}  	
	
}

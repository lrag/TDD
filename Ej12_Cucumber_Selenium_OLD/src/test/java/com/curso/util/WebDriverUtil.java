package com.curso.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebDriverUtil {

    private final WebDriver driver;
    
    //
    //Cucumber examina el proyecto buscando clases que tengan el constructor
    //por defecto.
    //Cuando se vaya a ejecutar un escenario instancia automáticamente esas clases
    //
    public WebDriverUtil() {
    	System.out.println("Creando DriverManager");
        System.setProperty("webdriver.gecko.driver",
                "src/test/resources/drivers/geckodriver/geckodriver.exe");
        
        WebDriver driver = new FirefoxDriver();
		WebDriverListener oyente = new OyenteSelenium(driver);
		EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(oyente);
		this.driver = decorator.decorate(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
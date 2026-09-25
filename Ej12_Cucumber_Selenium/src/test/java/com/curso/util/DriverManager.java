package com.curso.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    private final WebDriver driver;
    
    public DriverManager() {
    	System.out.println("Creando DriverManager");
        System.setProperty("webdriver.gecko.driver",
                "src/test/resources/drivers/geckodriver/geckodriver.exe");
        this.driver = new FirefoxDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
package com.curso.feature;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.curso.util.DriverManager;
import com.curso.util.LoginUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Common {
	
    private final DriverManager driverManager;

    public Common(DriverManager driverManager) {
        this.driverManager = driverManager;
    }
	
	@Before("@Autenticado")
	public void beforeEach() {
		LoginUtil.login(driverManager.getDriver(), "aaa", "bbb");		
	}	
	
	@After
	public void afterEach() {
		//driverManager.getDriver().quit();
	}
	
}

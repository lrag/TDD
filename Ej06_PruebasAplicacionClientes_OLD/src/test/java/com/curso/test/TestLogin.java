package com.curso.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.curso.util.LoginUtil;

public class TestLogin {

	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", 
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}	
		
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}	
	
	@Test
	public void pruebaLogin() {
		
		LoginUtil.login(driver, "aaa", "bbb");
		
		WebElement h1Titulo = driver.findElement(By.className("titulo"));
		Assertions.assertEquals(h1Titulo.getText(), "Listado de clientes");	
		
	}
	
	@Test
	public void pruebaLoginCredencialesIncorrectas() {
		
		LoginUtil.login(driver, "XXX", "YYY");
		
		WebElement h1Titulo = driver.findElement(By.className("titulo"));
		Assertions.assertEquals(h1Titulo.getText(), "Login");	
		
	}
	
}

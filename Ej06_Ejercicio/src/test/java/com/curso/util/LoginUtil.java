package com.curso.util;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginUtil {

	public static void login(WebDriver driver, String username, String password) {
		
		driver.get(Constantes.URL_APLICACION+"/login.html");
	
		WebElement tfLogin = driver.findElement(By.name("login"));
		WebElement tfPassword = driver.findElement(By.name("pw"));
		
		tfLogin.sendKeys(username);
		tfPassword.sendKeys(password);
		
		//Si solo necesitamos el botón para hacerle click lo podemos dejar así
		//driver.findElement(By.id("btnEntrar")).click();
		
		WebElement btnEntrar = driver.findElement(By.id("btnEntrar"));
		btnEntrar.click();
		
	}
	
}

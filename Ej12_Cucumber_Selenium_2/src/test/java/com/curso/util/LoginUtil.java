package com.curso.util;

import org.openqa.selenium.WebDriver;

import com.curso.pom.LoginPOM;

public class LoginUtil {

	public static void login(WebDriver driver, String username, String password) {
		driver.get(Constantes.URL_APLICACION+"/login.html");
		LoginPOM login = new LoginPOM(driver);
		login.getLogin().sendKeys(username);
		login.getPw().sendKeys(password);
		login.getBtnEntrar().click();
	}
	
}

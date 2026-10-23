package com.curso.util;

import org.openqa.selenium.WebDriver;

import com.curso.pom.LoginPOM;

public class LoginUtil {

	public static void login(WebDriver driver, String login, String pw) {
		driver.get(Constantes.URL_APLICACION+"login.html");
		LoginPOM loginPOM = new LoginPOM(driver);
		loginPOM.getLogin().sendKeys(login);
		loginPOM.getPw().sendKeys(pw);
		loginPOM.getBtnEntrar().click();		
	}	
	
}

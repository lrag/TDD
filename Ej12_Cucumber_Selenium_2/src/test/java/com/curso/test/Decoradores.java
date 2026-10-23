package com.curso.test;

import org.openqa.selenium.WebDriver;

import com.curso.pom.LoginPOM;
import com.curso.util.Constantes;
import com.curso.util.LoginUtil;
import com.curso.util.WebDriverUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Decoradores {

	private WebDriver driver;
	
	public Decoradores(WebDriverUtil webDriverUtil) {
		System.out.println("Creando Common()");
		driver = webDriverUtil.getDriver();
	}	
	
	@Before("@Autenticado")
	public void beforeAutenticado() {
		LoginUtil.login(driver, Constantes.USERNAME_CORRECTO, Constantes.PASSWORD_CORRECTO);
	}
	
	@Before("@Tocoto")
	public void beforeTocoto() {
		System.out.println("Antes del escenario marcado con @Tocoto");
	}
	
	@After("@Tocoto") 
	public void afterTocoto() {
		System.out.println("Despues del escenario marcado con @Tocoto");
	}
	
}

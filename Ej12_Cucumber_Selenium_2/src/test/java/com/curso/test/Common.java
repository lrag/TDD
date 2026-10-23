package com.curso.test;

import org.openqa.selenium.WebDriver;

import com.curso.pom.LoginPOM;
import com.curso.util.Constantes;
import com.curso.util.LoginUtil;
import com.curso.util.WebDriverUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Common {

	private WebDriver driver;
	
	public Common(WebDriverUtil webDriverUtil) {
		System.out.println("Creando Common()");
		driver = webDriverUtil.getDriver();
	}	
	
	//Este método existe para aquellos escenarios que necesiten autenticación pero que no les sirva 
	//el decorador @Autenticado
	@Given("un usuario autenticado")
	public void un_usuario_autenticado() {
		LoginUtil.login(driver, Constantes.USERNAME_CORRECTO, Constantes.PASSWORD_CORRECTO);
	}
	
}

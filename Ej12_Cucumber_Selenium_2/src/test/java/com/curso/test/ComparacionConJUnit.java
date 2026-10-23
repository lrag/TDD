package com.curso.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.curso.pom.InicioPOM;
import com.curso.pom.LoginPOM;
import com.curso.pom.MenuPOM;
import com.curso.util.Constantes;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ComparacionConJUnit {

	private WebDriver driver;
	
	@BeforeEach
	public void setUp() {
		driver = new FirefoxDriver();
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}
	
	///////////
	// JUNIT //
	///////////	
	
	//@Test
	@DisplayName("Cuando el usuario proporciona unas credenciales correctas entra en la aplicación")
	public void loginExito() {
		driver.get(Constantes.URL_APLICACION+"/login.html");
		LoginPOM login = new LoginPOM(driver);
		login.getLogin().sendKeys(Constantes.USERNAME_CORRECTO);
		login.getPw().sendKeys(Constantes.PASSWORD_CORRECTO);
		login.getBtnEntrar().click();
		
		MenuPOM menu = new MenuPOM(driver);
		Assertions.assertEquals(Constantes.NOMBRE_USUARIO, menu.getNombreUsuario().getText().replace("Usuario:", "").trim());		
	}
	
	//////////////
	// CUCUMBER //
	//////////////
	
	//@Given("entro en la página de login")
	public void entroEnLaPaginaDeLogin() {
		driver.get(Constantes.URL_APLICACION+"/login.html");
		LoginPOM login = new LoginPOM(driver);
		Assertions.assertEquals("Login", login.getTitulo().getText());
	}
	
	//@When("introduzco unas credenciales correctas")
	public void introduzco_unas_credenciales_correctas() {
		LoginPOM login = new LoginPOM(driver);
		login.getLogin().sendKeys(Constantes.USERNAME_CORRECTO);
		login.getPw().sendKeys(Constantes.PASSWORD_CORRECTO);
	}
	
	//@When("pulso el botón entrar")
	public void pulso_el_botón_entrar() {
		LoginPOM login = new LoginPOM(driver);
		login.getBtnEntrar().click();
	}
	
	//@Then("estoy autenticado")
	public void estoy_autenticado() {
		MenuPOM menu = new MenuPOM(driver);
		Assertions.assertEquals(Constantes.NOMBRE_USUARIO, menu.getNombreUsuario().getText().replace("Usuario:", "").trim());		
	}
	
}

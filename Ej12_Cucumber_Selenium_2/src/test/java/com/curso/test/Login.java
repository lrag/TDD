package com.curso.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.curso.pom.InicioPOM;
import com.curso.pom.LoginPOM;
import com.curso.pom.MenuPOM;
import com.curso.util.Constantes;
import com.curso.util.WebDriverUtil;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {
	
	private WebDriver driver;
	
	public Login(WebDriverUtil webDriverUtil) {
		System.out.println("Creando Login()");
		driver = webDriverUtil.getDriver();
	}
	
	@Given("entro en la página de login")
	public void entroEnLaPaginaDeLogin() {
		driver.get(Constantes.URL_APLICACION+"/login.html");
		LoginPOM login = new LoginPOM(driver);
		Assertions.assertEquals("Login", login.getTitulo().getText());
	}
	
	@When("introduzco unas credenciales correctas")
	public void introduzco_unas_credenciales_correctas() {
		LoginPOM login = new LoginPOM(driver);
		login.getLogin().sendKeys(Constantes.USERNAME_CORRECTO);
		login.getPw().sendKeys(Constantes.PASSWORD_CORRECTO);
	}
	
	@When("introduzco unas credenciales incorrectas")
	public void introduzco_unas_credenciales_incorrectas() {
		LoginPOM login = new LoginPOM(driver);
		login.getLogin().sendKeys(Constantes.USERNAME_INCORRECTO);
		login.getPw().sendKeys(Constantes.PASSWORD_INCORRECTO);
	}	
	
	@When("pulso el botón entrar")
	public void pulso_el_botón_entrar() {
		LoginPOM login = new LoginPOM(driver);
		login.getBtnEntrar().click();
	}
	
	@Then("estoy autenticado")
	public void estoy_autenticado() {
		//Comprobar que existe la cookie para ver si el usuario está autenticado está mal:
		//-estamos abriendo la caja negra para ver cómo funciona
		//-en este caso particular hay aplicaciones que crean esta cookie antes incluso de la autenticación
		Cookie cookie = driver.manage().getCookieNamed("JSESSIONID");
		Assertions.assertNotNull(cookie, "No existe la cookie JSESSIONID");
		
		//Mejor averiaguar que estamos autenticados por otros medios
		MenuPOM menu = new MenuPOM(driver);
		Assertions.assertEquals(Constantes.NOMBRE_USUARIO, menu.getNombreUsuario().getText().replace("Usuario:", "").trim());		
	}

	@Then("estoy en la página de inicio")
	public void veo_la_página_de_inicio() {
	    InicioPOM inicio = new InicioPOM(driver);
	    Assertions.assertEquals("Inicio", inicio.getTitulo().getText());
	}
	
	@Then("estoy en la página de login")
	public void veo_la_página_de_login() {
		LoginPOM login = new LoginPOM(driver);
		Assertions.assertEquals("Login", login.getTitulo().getText());
	}
	
	@Then("veo el mensaje \"Credenciales incorrectas\"")
	public void veo_el_mensaje_credenciales_incorrectas() {
		LoginPOM login = new LoginPOM(driver);
		//El texto "Credenciales incorrectas" siempre está pero solo se hace visible cuando hay un error de autenticación
		Assertions.assertEquals("block", login.getMensaje().getCssValue("display"));
	}	


	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	@When("intento acceder directamente a {string}")
	public void intento_acceder_directamente_a_inicio(String recurso) {
		System.out.println("Intentando acceder a: "+recurso);
		driver.get(Constantes.URL_APLICACION+recurso);
	}		
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
		
	private List<String> recursos;
	
	/*
	filas = [
		{
			"recurso" : "/seguro/inicio.jsp"
		},
		{
			"recurso" : "/seguro/SVClientes"
		},
		{
			"recurso" : "/seguro/formularioClientes.jsp"
		},
		{
			"recurso" : "/seguro/listadoClientes.jsp"
		},
	]
	 */
	@Given("las siguientes páginas")
	public void las_siguientes_páginas(DataTable dataTable) {
	    List<Map<String, String>> filas = dataTable.asMaps(String.class, String.class);
	    recursos = new ArrayList<>();
	    for (Map<String, String> fila : filas) {	
	    	System.out.println(fila.get("recurso"));
	    	recursos.add(fila.get("recurso"));
	    }	
	}	
	
	@When("intento acceder directamente a cada una de ellas")
	public void intento_acceder_directamente_a_cada_una_de_ellas() {
		for(String recurso: recursos) {
			driver.get(Constantes.URL_APLICACION+recurso);
			LoginPOM login = new LoginPOM(driver);
			Assertions.assertEquals("Login", login.getTitulo().getText(), "Se ha podido acceder a "+recurso+" sin autenticación.");	
		}
	}	
	
	
	
	
	
	
	
}

package com.curso.feature;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.curso.pom.ListadoClientesPOM;
import com.curso.util.Constantes;
import com.curso.util.WebDriverUtil;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {
	
	private WebDriver driver;
	
	public Login(WebDriverUtil driverManager) {
		super();
		System.out.println("Instanciando Login");
		this.driver = driverManager.getDriver();
	}

	@Given("entro en la página de login")
	public void entro_en_la_página_de_login() {
		driver.get(Constantes.URL_APLICACION+"/login.html");
		WebElement h1Titulo = driver.findElement(By.className("titulo"));
		Assertions.assertEquals(h1Titulo.getText(), "Login");	
	}
	
	@Given("estoy en la página de login")
	public void estoy_en_la_página_de_login() {
		WebElement h1Titulo = driver.findElement(By.className("titulo"));
		Assertions.assertEquals(h1Titulo.getText(), "Login");	
	}

	@When("intento acceder directamente a {string}")
	public void intento_acceder_directamente_a_inicio(String recurso) {
		System.out.println("Intentando acceder a: "+recurso);
		driver.get(Constantes.URL_APLICACION+recurso);
	}		

	@When("introduzco mis credenciales")
	public void introduzco_mis_credenciales() {
		WebElement tfLogin = driver.findElement(By.name("login"));
		WebElement tfPassword = driver.findElement(By.name("pw"));
		
		tfLogin.sendKeys("aaa");
		tfPassword.sendKeys("bbb");	
	}

	@When("pulso el botón entrar")
	public void pulso_el_botón_entrar() {
		driver.findElement(By.id("btnEntrar")).click();
	}
	
	@When("introduzco unas credenciales incorrectas")
	public void introduzco_unas_credenciales_incorrectas() {
		WebElement tfLogin = driver.findElement(By.name("login"));
		WebElement tfPassword = driver.findElement(By.name("pw"));
		
		tfLogin.sendKeys("antúnez");
		tfPassword.sendKeys("TOCOTÓ");	
	}

	@Then("se crea mi sesión")
	public void se_crea_mi_sesión() {
		//Comprobamos que nos envían un redirect y la cookie JSESSIONID (no formaría parte de este test porque esto es conocer cómo se 
		//realiza la autenticación!) 
		//Y encima no basta con que exista la cookie para garantizar que el usuario está autenticado
		Cookie token = driver.manage().getCookieNamed("JSESSIONID");
		System.out.println("JSESSIONID:"+token.getValue());
		assertNotNull(token);	
		
		//"Se crea mi sesión" se comprobaría buscando el nombre del usuario en la página
	}

	@Then("veo la página de inicio")
	public void veo_la_página_de_inicio() {
		ListadoClientesPOM listadoClientes = new ListadoClientesPOM(driver);
		Assertions.assertEquals("NO_SE_QUE_Inicio", listadoClientes.getH1Titulo().getText());
	}
	
	
	private List<String> recursos;
	
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
			WebElement h1Titulo = driver.findElement(By.className("titulo"));
			Assertions.assertEquals("Login", h1Titulo.getText(), "Se ha poodido acceder a "+recurso+" sin autenticación.");	
		}
	}	
	

}

package com.curso.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import com.curso.pom.InicioPOM;
import com.curso.pom.LoginPOM;
import com.curso.util.Constantes;
import com.curso.util.CustomWebDriverListener;
import com.curso.util.LoginUtil;

public class PruebasLogin {

	//Un navegador para cada test de esta clase
	private WebDriver driver;
	
	@BeforeEach
	public void setUp() {
		//System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
		WebDriver driverNoDecorado = new FirefoxDriver();		
		WebDriverListener oyente = new CustomWebDriverListener(driverNoDecorado);
		EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(oyente);
		//Todos los test utilizarán el driver decorado:
		driver = decorator.decorate(driverNoDecorado);			
	}
	
	@AfterEach
	public void afterEach() {
		//driver.quit();
	}	
	
	@Test
	@DisplayName("Un usuario con credenciales correctas entra en la aplicación")
	public void loginExito() {
		
		//Navegar a la pantalla de login
		//Introducir las credenciales del usuario
		//Pulsar el botón entrar
		//Comprobar que se navega a la pantalla de Inicio
		
		LoginUtil.login(driver, "aaa", "bbb");
		
		//Ahora debe haber navegado a inicio
		InicioPOM inicioPOM = new InicioPOM(driver);
		
		Assertions.assertEquals("Inicio", inicioPOM.getTitulo().getText());
		
	}	
	
	@Test
	@DisplayName("Un usuario no autienticado no puede ver la aplicacion")
	public void usuarioNoAutenticado() {
		
		//Intentamos navegar a la pantalla de inicio sin estar autenticados
		//La aplicación reacciona devolviendo la pa<ntalla de login
		
		driver.get(Constantes.URL_APLICACION+"seguro/inicio.jsp");
		LoginPOM loginPOM = new LoginPOM(driver);
		Assertions.assertEquals("Login", loginPOM.getTitulo().getText());
	}		
	
}














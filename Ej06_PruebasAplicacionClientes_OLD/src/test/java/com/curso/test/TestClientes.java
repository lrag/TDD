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

import com.curso.pom.FormularioClientesPOM;
import com.curso.pom.ListadoClientesPOM;
import com.curso.util.Constantes;
import com.curso.util.LoginUtil;

public class TestClientes {

	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", 
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		//Hacemos login antes de cada test
		LoginUtil.login(driver, "aaa", "bbb");
	}	
		
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}	
	
	@Test
	public void insertarCliente() {
	
		//Entramos en la página
		driver.get(Constantes.URL_APLICACION+"/seguro/SVClientes?accion=verFormulario");
		//Creamos el DOM
		FormularioClientesPOM formularioClientes = new FormularioClientesPOM(driver);
		
		//Datos del cliente para la prueba
		String nombreCliente = "Antúnez";
		String direccionCliente = "C/Tocotó";
		String telefonoCliente = "555123456";
		
		//Escribimos en las cajas de texto
		
		formularioClientes.getTfNombre().sendKeys(nombreCliente);
		formularioClientes.getTfDireccion().sendKeys(direccionCliente);
		formularioClientes.getTfTelefono().sendKeys(telefonoCliente);
		
		formularioClientes.getBtnInsertar().click();
		
		ListadoClientesPOM listadoClientes = new ListadoClientesPOM(driver);
		
		//Estamos en la pantalla de listado?
		Assertions.assertEquals(listadoClientes.getH1Titulo().getText(), "Listado de clientes");			
		
		/*
		//Obteniendo la última fila
		WebElement tablaClientes = listadoClientes.getTablaClientes();
		List<WebElement> filas = tablaClientes.findElements(By.tagName("tr")); //Todos los tr que 'cuelguen' de 'tablaClientes'
		WebElement ultimaFila = filas.get(filas.size()-1);
		
		//Obteniendo los td de la ultima fila
		List<WebElement> columnas = ultimaFila.findElements(By.tagName("td")); //Los td del último tr
		*/
		
		List<WebElement> columnas = listadoClientes.getTdsUltimaFila();
		
		String tdNombre    = columnas.get(0).getText();
		String tdDireccion = columnas.get(1).getText();
		String tdTelefono  = columnas.get(2).getText();	
		
		//Comprobamos que los valores coinciden con los insertados:
		Assertions.assertAll(
					() -> assertEquals(nombreCliente, tdNombre),
					() -> assertEquals(direccionCliente, tdDireccion),
					() -> assertEquals(telefonoCliente, tdTelefono)
				);			
	}
	
	@Test
	public void borrarCliente() {

		driver.get(Constantes.URL_APLICACION+"/seguro/SVClientes");
		
		ListadoClientesPOM listadoClientes = new ListadoClientesPOM(driver);
		
		/*
		WebElement tablaClientes = listadoClientes.getTablaClientes();
		List<WebElement> filas = tablaClientes.findElements(By.tagName("tr")); //Todos los tr que 'cuelguen' de 'tablaClientes'
		WebElement primeraFila = filas.get(0);
		
		List<WebElement> columnas = primeraFila.findElements(By.tagName("td")); //Los td del último tr
		WebElement primerTd = columnas.get(0);
		*/
		
		WebElement primerTd = listadoClientes.getPrimerTdPrimeraFila();			
		WebElement enlace = primerTd.findElement(By.tagName("a"));
		enlace.click();
		
		//Falta terminar la parte de pulsar borrar y el aserto
		
	}	
	
}

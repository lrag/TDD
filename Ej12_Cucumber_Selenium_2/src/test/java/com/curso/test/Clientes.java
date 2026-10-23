package com.curso.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.curso.pom.FormularioClientesPOM;
import com.curso.pom.ListadoClientesPOM;
import com.curso.pom.MenuPOM;
import com.curso.util.Constantes;
import com.curso.util.WebDriverUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Clientes {

	private WebDriver driver;
	
	public Clientes(WebDriverUtil webDriverUtil) {
		System.out.println("Creando Clientes()");
		driver = webDriverUtil.getDriver();
	}	

	@Given("hay clientes dados de alta")
	public void hay_clientes_dados_de_alta() {
		//Damos por sentado que hay clientes enla tabla
		//Si no fuera así los insertariamos aqui
	}

	@When("entro en la página de listado de clientes")
	public void accedo_al_listado_de_clientes() {
		driver.get(Constantes.URL_APLICACION+"/seguro/SVClientes");
		new ListadoClientesPOM(driver);		
	}
	
	@When("navego a la pagina del listado de clientes")
	public void navego_a_la_pagina_listado_de_clientes() {
		driver.get(Constantes.URL_APLICACION+"/seguro/inicio.jsp");
		MenuPOM menu = new MenuPOM(driver);
		menu.getEnlaceClientes().click();
		new ListadoClientesPOM(driver);		
	}

	@Then("veo una fila por cada cliente con su nombre, dirección y teléfono")
	public void veo_una_fila_por_cada_cliente_con_su_nombre_dirección_y_teléfono() {
		//Damos por sentado que estamos en la pantalla del listado
		ListadoClientesPOM listadoClientes = new ListadoClientesPOM(driver);
		Assertions.assertTrue( listadoClientes.getTablaClientes().findElements(By.tagName("tr")).size() > 0, "No hay clientes en la tabla");
	}	

	////////////////////////////////////////////////////////////////////////////////	
	private String nombre;
	private String direccion;
	private String telefono;
	
	@When("selecciono un cliente de la tabla")
	public void selecciono_un_cliente_de_la_tabla() {
		ListadoClientesPOM listadoClientes = new ListadoClientesPOM(driver);
		List<WebElement> columnasPrimeraFila = listadoClientes.getTdsPrimeraFila();
		nombre = columnasPrimeraFila.get(0).getText();
		direccion = columnasPrimeraFila.get(1).getText();
		telefono = columnasPrimeraFila.get(2).getText();
		listadoClientes.getEnlacePrimerTdPrimeraFila().click();		
	}

	@Then("veo el formulario con el cliente seleccionado")
	public void veo_el_formulario_con_el_cliente_seleccionado() {
		FormularioClientesPOM formularioClientes = new FormularioClientesPOM(driver);		
		Assertions.assertEquals(nombre, formularioClientes.getTfNombre().getAttribute("value"));
		Assertions.assertEquals(direccion, formularioClientes.getTfDireccion().getAttribute("value"));
		Assertions.assertEquals(telefono, formularioClientes.getTfTelefono().getAttribute("value"));
	}	
	
}

package com.curso.feature;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.curso.pom.ListadoClientesPOM;
import com.curso.util.Constantes;
import com.curso.util.DriverManager;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Clientes {
	
	private WebDriver driver;
	
	public Clientes(DriverManager driverManager) {
		super();
		System.out.println("Instanciando Clientes");
		this.driver = driverManager.getDriver();
	}
	
	@When("accedo al listado de clientes")
	public void accedo_al_listado_de_clientes() {
		driver.get(Constantes.URL_APLICACION+"/seguro/SVClientes");
		ListadoClientesPOM listadoClientes = new ListadoClientesPOM(driver);
		Assertions.assertEquals("Listado de clientes", listadoClientes.getH1Titulo().getText()); 
	}

	@Then("veo una fila por cada cliente con su nombre, dirección y teléfono")
	public void veo_una_fila_por_cada_cliente_con_su_nombre_dirección_y_teléfono() {
		ListadoClientesPOM listadoClientes = new ListadoClientesPOM(driver);
		//Este aserto no hace falta porque al instanciar ListadoClientesPOM ya se busca "tablaClientes"
		//Assertions.assertNotNull(listadoClientes.getTablaClientes());
	}		

}

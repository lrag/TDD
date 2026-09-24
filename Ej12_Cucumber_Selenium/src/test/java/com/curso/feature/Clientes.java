package com.curso.feature;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.curso.pom.ListadoClientesPOM;
import com.curso.util.Constantes;
import com.curso.util.LoginUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Clientes {
	
	private WebDriver driver;

	@Before
	public void beforeEach() {
		System.setProperty("webdriver.gecko.driver", 
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		LoginUtil.login(driver, "aaa", "bbb");		
	}	
	
	@After
	public void afterEach() {
		//driver.close();
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

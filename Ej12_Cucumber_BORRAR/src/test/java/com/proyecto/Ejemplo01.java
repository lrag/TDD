package com.proyecto;

import org.junit.jupiter.api.Assertions;

import com.proyecto.modelo.entidad.Cesta;
import com.proyecto.modelo.entidad.Producto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Ejemplo01 {

	//CTRL + SHIFT + O : Organize imports
	
	private Producto teclado;
	private Producto raton;
	private Cesta cesta = new Cesta();
	
	public Ejemplo01() {
		super();
		System.out.println("Instanciando Ejemplo01");
	}
	
	@Given("el precio de un teclado es {double}")
	public void el_precio_de_un_teclado_es(Double precio) {
		teclado = new Producto();
		teclado.setId(1);
		teclado.setPrecio(precio);
	}
	
	@Given("el precio de un raton es {double}")
	public void el_precio_de_un_raton_es(Double precio) {
		raton = new Producto();
		raton.setId(2);
		raton.setPrecio(precio);
	}

	@When("Compro un teclado")
	public void compro_teclado() {	
		cesta.addProducto(teclado, 1);
	}
	
	@When("Compro {int} teclados")
	public void compro_teclados(Integer cantidad) {
		cesta.addProducto(teclado, cantidad);
	}
	
	@When("Compro {int} raton")
	public void compro_raton(Integer cantidad) {
		cesta.addProducto(raton, cantidad);
	}    

	@Then("el total de la cesta es {double}")
	public void el_total_De_la_centa_es(Double total) {
		Assertions.assertEquals(total, cesta.getTotal());
	}  	
	
	@Then("hay {int} productos en la cesta")
	public void hay_productos_en_la_cesta(Integer numDetalles) {
		Assertions.assertEquals(numDetalles, cesta.getDetalles().size());
	}	
}

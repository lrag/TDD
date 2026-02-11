package com.proyecto;

import org.junit.jupiter.api.Assertions;

import com.proyecto.modelo.entidad.Cesta;
import com.proyecto.modelo.entidad.Producto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Ejemplo01 {
	
	//Cesta cesta = new Cesta();
	private Double precioTeclado;
	private Double precioRaton;
	
	public Ejemplo01() {
		super();
		System.out.println("Instanciando Ejemplo01");
	}

	@Given("el precio de un teclado es {double}")
	public void el_precio_de_un_teclado_es(Double precio) {
	    this.precioTeclado = precio;
	}
	
	@When("Compro {int} teclados")
	public void compro_teclados(Integer cantidad) {
		Producto producto = new Producto(1, "Teclado", precioTeclado);
		EstadoCesta.getCesta().addProducto(producto, cantidad);
	}
	
	@Given("el precio de un raton es {double}")
	public void el_precio_de_un_raton_es(Double precio) {
		this.precioRaton = precio;
	}

	@When("Compro {int} raton")
	public void compro_raton(Integer cantidad) {
		Producto producto = new Producto(2, "Raton", precioRaton);
		EstadoCesta.getCesta().addProducto(producto, cantidad);
	}	
	
	@Then("el total es {double}")
	public void el_total_es(Double total) {
		Assertions.assertEquals(total, EstadoCesta.getCesta().getTotal());
		EstadoCesta.eliminarCesta();
	} 
	
	@Then("el numero de detalles de la cesta es {int}")
	public void el_numero_de_detalles_de_la_cesta_es(Integer numeroDetalles) {
		Assertions.assertEquals(numeroDetalles, EstadoCesta.getCesta().getDetalles().size());
		EstadoCesta.eliminarCesta();
	}	
	
	
}

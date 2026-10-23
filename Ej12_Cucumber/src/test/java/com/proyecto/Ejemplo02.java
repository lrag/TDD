package com.proyecto;

import org.junit.jupiter.api.Assertions;

import com.proyecto.modelo.entidad.Cesta;
import com.proyecto.modelo.entidad.Producto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Ejemplo02 {

	@When("Quiero imprimir por consola {string} y {string}")
	public void quiero_imprimir_por_consola_y(String string, String string2) {
	    System.out.println("Dato 1:"+string+", dato2:"+string2);
	}

	@Then("Todo fue estupendamente con el texto {string}")
	public void todo_fue_estupendamente(String texto) {
	    System.out.println(texto);
	}	
		
	private Cesta cesta = new Cesta();
	private Producto producto;
	
	@Given("el precio de {string} es {double}")
	public void el_precio_de_es(String nombre, Double precio) {
	    producto = new Producto();
	    producto.setId(1);
	    producto.setNombre(nombre);
	    producto.setPrecio(precio);
	}

	@When("Compro {int} producto")
	public void compro_producto(Integer cantidad) {
	    cesta.addProducto(producto, cantidad);
	}

	//Atentos al punto
	@Then("el total de la cesta es {double}.")
	public void el_total_de_la_cesta_es(Double total) {
	    Assertions.assertEquals(total, cesta.getTotal());
	}	
	
}

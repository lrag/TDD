package com.proyecto;

import org.junit.jupiter.api.Assertions;

import com.proyecto.modelo.entidad.Producto;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AbusoCesta {

	public AbusoCesta() {
		super();
		System.out.println("Instanciando AbusoCesta");
	}

	@Then("la operación es rechazada")
	public void la_operacion_es_rechazada() {
		Assertions.assertTrue(EstadoCesta.getCesta().getDetalles().isEmpty());
	}

	@When("intento añadir un producto con el mismo id y precio {double}")
	public void intento_anadir_un_producto_con_el_mismo_id_y_precio(Double precio) {
		Producto producto = new Producto(1, "Teclado", precio);
		EstadoCesta.getCesta().addProducto(producto, 1);
	}

	@Then("el total sigue siendo {double}")
	public void el_total_sigue_siendo(Double total) {
		Assertions.assertEquals(total, EstadoCesta.getCesta().getTotal());
	}

}

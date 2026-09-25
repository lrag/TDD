package com.proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import com.proyecto.modelo.entidad.Cesta;
import com.proyecto.modelo.entidad.Producto;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Ejemplo02 {
	
	List<Producto> productos = new ArrayList<Producto>();
	List<Integer> cantidades = new ArrayList<Integer>();
	//Cesta cesta = new Cesta();
	
	public Ejemplo02() {
		super();
		System.out.println("Instanciando Ejemplo02");
	}

	@Given("los siguientes productos")
	public void los_siguientes_productos(DataTable dataTable) {
		
	
		// Obtenemos una lista de mapas: cada mapa es una fila
	    List<Map<String, String>> filas = dataTable.asMaps(String.class, String.class);

	    for (Map<String, String> fila : filas) {
	        String nombre = fila.get("nombre producto");
	        String precio = fila.get("precio");
	        String cantidad = fila.get("cantidad");
	        
	        Producto producto = new Producto((int) Math.round(Math.random()*1_000_000), nombre, Double.valueOf(precio));
	        productos.add(producto);
	        cantidades.add(Integer.valueOf(cantidad));
	        
	        System.out.println("Cargando: "+nombre+", "+precio+", "+cantidad);
	    }
		
	}

	@When("Compro la cesta")
	public void compro_la_cesta() {
		for(int transit=0; transit<productos.size(); transit++) {
			Producto producto = productos.get(transit);
			Integer cantidad = cantidades.get(transit);
			EstadoCesta.getCesta().addProducto(producto, cantidad);
		}
	}

	@Then("el total es {double} y el numero de detalles es {int}")
	public void el_total_y_el_num_detalles_es(Double total, Integer numDetalles) {
		Assertions.assertEquals(total, EstadoCesta.getCesta().getTotal());
		EstadoCesta.eliminarCesta();
	} 
	

	@When("Quiero imprimir por consola {string} y {string}")
	public void quiero_imprimir_por_consola(String numero, String mensaje) {
	    System.out.println("Número :"+numero+", "+mensaje);
	}
	
	@Then("Todo fue estupendamente")
	public void todo_fue_estupendamente() {
		System.out.println("Todo es fabuloso.");
	}
	
	
	
}

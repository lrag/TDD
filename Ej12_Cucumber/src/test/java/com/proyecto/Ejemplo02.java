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

	    for (Map<String, String> columna : filas) {
	        String nombre = columna.get("nombre producto");
	        String precio = columna.get("precio");
	        String cantidad = columna.get("cantidad");
	        
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

	/*
	@Then("el total de la cesta es {double}")
	public void el_total_de_la_cesta_es(Double double1) {
		Assertions.assertEquals(double1, EstadoCesta.getCesta().getTotal());
	}
	*/
	
}

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

public class Ejemplo01 {

	private Double precioTeclado;
	private Double precioRaton;
	
	private List<Producto> productos = new ArrayList<>();
	private List<Integer> cantidades = new ArrayList<>();
	
	private Cesta cesta = new Cesta();
	
	public Ejemplo01() {
		System.out.println("Creando una instancia de Ejemplo01");
	}

	@Given("el precio de un teclado es {double}")
	public void el_precio_de_un_teclado_es(Double precio) {
		precioTeclado = precio;
	}
	
	@Given("el precio de un raton es {double}")
	public void el_precio_de_un_raton_es(Double precio) {
		precioRaton = precio;
	}
	
	@When("Compro un teclado")
	public void compro_un_teclado() {
		Producto teclado = new Producto();
		teclado.setNombre("Teclado");
		teclado.setPrecio(precioTeclado);
		
		//cesta = new Cesta();
		cesta.addProducto(teclado, 1);	
	}
	
	@When("Compro {int} teclados")
	public void compro_teclados(Integer cantidad) {
		Producto teclado = new Producto();
		teclado.setId(1);
		teclado.setNombre("Teclado");
		teclado.setPrecio(precioTeclado);
		
		cesta.addProducto(teclado, cantidad);	
	}
	
	@When("Compro {int} ratones")
	public void compro_ratones(Integer cantidad) {
		Producto raton = new Producto();
		raton.setId(2);
		raton.setNombre("Ratón");
		raton.setPrecio(precioRaton);
		
		cesta.addProducto(raton, cantidad);	
	}	
	
	@Given("los siguientes productos")
	public void dados_los_siguientes_productos(DataTable dataTable) {
	
		// Obtenemos una lista de mapas: cada mapa es una fila
		
		/*
		[
			{
				"nombre producto" : "Chintáfono",
				"precio"          : 20,
				"cantidad"        : 1
			},
			{
				"nombre producto" : "Fleje",
				"precio"          : 30,
				"cantidad"        : 2
			},		
			{
				"nombre producto" : "Studebaker",
				"precio"          : 40,
				"cantidad"        : 3
			}
		]		
		*/
	    List<Map<String, String>> filas = dataTable.asMaps(String.class, String.class);

	    System.out.println("================================================");
	    for (Map<String, String> fila : filas) {
	        String nombre = fila.get("nombre producto");
	        String precio = fila.get("precio");
	        String cantidad = fila.get("cantidad");
	        
	        System.out.println("Cargando: "+nombre+", "+precio+", "+cantidad);
	        Producto producto = new Producto((int) Math.round(Math.random()*1_000_000), nombre, Double.valueOf(precio));
	        productos.add(producto);
	        cantidades.add(Integer.valueOf(cantidad));
	    }
	}
	
	@When("Compro la cesta")
	public void compro_la_cesta() {
		for(int a=0; a<productos.size(); a++) {
			cesta.addProducto(productos.get(a), cantidades.get(a));
		}
	}
	
	@Then("el total de la cesta es {double}")
	public void el_total_de_la_cesta_es(Double total) {
	    Assertions.assertEquals(total, cesta.getTotal());
	}
	
	@Then("hay {int} productos en la cesta")
	public void hay_X_productos_en_la_cesta(Integer productos) {
	    Assertions.assertEquals(productos, cesta.getDetalles().size());
	}	
	
}

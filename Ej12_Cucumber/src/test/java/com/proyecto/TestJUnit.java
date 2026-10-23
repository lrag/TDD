package com.proyecto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.proyecto.modelo.entidad.Cesta;
import com.proyecto.modelo.entidad.Producto;

public class TestJUnit {

	@Test
	public void addProductoCesta() {

		//Dada esta cesta
		Cesta cesta = new Cesta();
		//Y este producto
		Producto teclado = new Producto(1,"Teclado",20d);
		//Cuando añado el producto a la cesta
		cesta.addProducto(teclado, 1);
		//Entonces la cesta tiene un total de 20 euros
		Assertions.assertEquals(20d, cesta.getTotal());
		
	}
	
}

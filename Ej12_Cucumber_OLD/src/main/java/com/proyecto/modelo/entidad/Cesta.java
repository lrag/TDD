package com.proyecto.modelo.entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cesta {

	private List<DetalleCesta> detalles = new ArrayList<>();
	private Double total;
	
	public void addProducto(Producto producto, int cantidad) {		
		for(DetalleCesta aux: detalles) {
			if(aux.getProducto().getId().equals(producto.getId())) {
				aux.aumentarCantidad(cantidad);
				calcularTotal();
				return;
			}
		}
		
		detalles.add(new DetalleCesta(cantidad, producto));
		calcularTotal();
	}
	
	private void calcularTotal() {
		total = detalles.stream().collect(Collectors.summingDouble( dp -> dp.getProducto().getPrecio()*dp.getCantidad()));		
	}
	
	public Double getTotal() {
		return this.total;
	}

	public List<DetalleCesta> getDetalles() {
		return detalles;
	}
	
}

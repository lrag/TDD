package com.proyecto.modelo.entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cesta {

	private static final int CANTIDAD_MAXIMA_POR_PRODUCTO = 100;

	private List<DetalleCesta> detalles = new ArrayList<>();
	private Double total;

	public void addProducto(Producto producto, int cantidad) {
		if(producto.getPrecio() == null || producto.getPrecio() <= 0) {
			return;
		}
		if(cantidad <= 0 || cantidad > CANTIDAD_MAXIMA_POR_PRODUCTO) {
			return;
		}

		Optional<DetalleCesta> detalleExistente = detalles.stream()
				.filter(aux -> aux.getProducto().getId().equals(producto.getId()))
				.findFirst();

		if(detalleExistente.isPresent()) {
			if(!detalleExistente.get().getProducto().getPrecio().equals(producto.getPrecio())) {
				return;
			}
			detalleExistente.get().aumentarCantidad(cantidad);
		} else {
			detalles.add(new DetalleCesta(cantidad, producto));
		}

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

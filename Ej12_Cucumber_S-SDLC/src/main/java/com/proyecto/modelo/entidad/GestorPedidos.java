package com.proyecto.modelo.entidad;

import java.util.ArrayList;
import java.util.List;

public class GestorPedidos {

	//Esto simula la base de datos
	private List<Pedido> pedidos = new ArrayList<>();

	public boolean confirmarPedido(Pedido pedido) {
		boolean yaExiste = pedidos.stream()
				.anyMatch(p -> p.getIdentificadorEnvio().equals(pedido.getIdentificadorEnvio()));
		if (yaExiste) {
			return false;
		}
		pedidos.add(pedido);
		return true;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

}

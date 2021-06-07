package com.curso.modelo.negocio;

import com.curso.modelo.entidad.Cliente;

public class GestorClientes {

	public Cliente insertarCliente(Cliente cliente) {
		//LN para insertar el cliente
		cliente.setId(System.currentTimeMillis());
		return cliente;
	}
	
}

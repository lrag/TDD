package com.curso.modelo.negocio;

import java.util.List;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Comercial;
import com.curso.modelo.entidad.Sucursal;
import com.curso.modelo.persistencia.ClienteDao;

public class GestorClientes {

	private ClienteDao clienteDao;
	private GestorSucursales gestorSucursales;
	private GestorComerciales gestorComerciales;
	private GestorDirecciones gestorDirecciones;

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	public void setGestorSucursales(GestorSucursales gestorSucursales) {
		this.gestorSucursales = gestorSucursales;
	}

	public void setGestorComerciales(GestorComerciales gestorComerciales) {
		this.gestorComerciales = gestorComerciales;
	}

	public void setGestorDirecciones(GestorDirecciones gestorDirecciones) {
		this.gestorDirecciones = gestorDirecciones;
	}

	// Recibe el cliente por referencia, lo modifica y lo devuelve
	public Cliente altaCliente(Cliente cliente) throws Exception {

		// ...

		gestorDirecciones.comprobarDireccion(cliente.getDireccion());

		// ...

		Sucursal sucursal = gestorSucursales.encontrarSucursalCercana(cliente.getDireccion());
		if (sucursal == null) {
			sucursal = new Sucursal(1, "Sucursal virtual", "www.sucursal.es");
		}

		// ...

		cliente.setSucursal(sucursal);

		// ...

		List<Comercial> comerciales = gestorComerciales.encontrarComerciales();
		cliente.setComerciales(comerciales);
		
		// ...
		
		cliente = clienteDao.insertar(cliente);

		// ...

		return cliente;
	}

	// Recibimos una lista de clientes e intentamos insertarlos
	// Si hay algún cliente que no se puede insertar el proceso NO se detiene
	public void altaClientes(List<Cliente> clientes) {
		for (Cliente c : clientes) {
			try {
				altaCliente(c);
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	// En los test doubles solo aparecen los métodos PUBLICOS
	private void metodo() {
		// código
	}

}


//UN DUMMIE DE LA CLASE GestorCLientes:
/*
public class GestorClientes_Dummie extends GestorClientes {

	public void setClienteDao(ClienteDao clienteDao) { }

	public void setGestorSucursales(GestorSucursales gestorSucursales) { }

	public void setGestorComerciales(GestorComerciales gestorComerciales) { }

	public void setGestorDirecciones(GestorDirecciones gestorDirecciones) { }

	public Cliente altaCliente(Cliente cliente) throws Exception { return null; }

	public void altaClientes(List<Cliente> clientes){ }

}
*/



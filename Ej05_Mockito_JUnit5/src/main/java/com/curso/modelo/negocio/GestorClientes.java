package com.curso.modelo.negocio;

import java.util.List;
import java.util.Set;

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

	//Recibe el cliente por referencia, lo modifica y lo devuelve
	public Cliente altaCliente(Cliente cliente) throws Exception {
		
		//..
		gestorDirecciones.comprobarDireccion(cliente.getDireccion());
		
		Sucursal sucursal = gestorSucursales.encontrarSucursalCercana(cliente.getDireccion());
		//...
		cliente.setSucursal(sucursal);

		cliente = clienteDao.insertar(cliente);
		
		List<Comercial> comerciales = gestorComerciales.encontrarComerciales();
		
		//...
		cliente.setComerciales(comerciales);

						
		//...
		
		return cliente;
	}
	
	//
	public void altaClientes(List<Cliente> clientes){
	
		for(Cliente c: clientes) {
			try {
				altaCliente(c);
			} catch (Exception e) {
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
	}

}










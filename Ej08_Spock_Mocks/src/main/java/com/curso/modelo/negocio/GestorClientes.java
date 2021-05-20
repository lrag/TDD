package com.curso.modelo.negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.persistencia.ClienteDao;

public class GestorClientes {

	private ClienteDao clienteDao;
	private GestorSucursales gestorSucursales;
	private GestorComerciales gestorComerciales;

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	public void setGestorSucursales(GestorSucursales gestorSucursales) {
		this.gestorSucursales = gestorSucursales;
	}

	public void setGestorComerciales(GestorComerciales gestorComerciales) {
		this.gestorComerciales = gestorComerciales;
	}

	//Recibe el cliente por referencia, lo modifica y lo devuelve
	public Cliente altaCliente(Cliente cliente) throws Exception {
		
		System.out.println("====================================");
		System.out.println(cliente.getDireccion());
		
		
		
		cliente.setSucursal(gestorSucursales.encontrarSucursalCercana(cliente.getDireccion()));
		cliente.setComerciales(gestorComerciales.encontrarComerciales());
		clienteDao.insertar(cliente);
		//...
		
		return cliente;
	}


}

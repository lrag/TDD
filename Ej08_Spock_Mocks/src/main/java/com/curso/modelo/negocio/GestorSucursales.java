package com.curso.modelo.negocio;

import com.curso.modelo.entidad.Sucursal;
import com.curso.modelo.negocio.excepcion.DireccionException;
import com.curso.modelo.negocio.excepcion.SucursalException;
import com.curso.modelo.persistencia.SucursalDao;

public class GestorSucursales {

	private SucursalDao sucursalDao;
	private GestorDirecciones gestorDirecciones;

	public void setSucursalDao(SucursalDao sucursalDao) {
		this.sucursalDao = sucursalDao;
	}
	
	public void insertar(Sucursal sucursal) {
		sucursalDao.insertar(sucursal);
	}
	
	public Sucursal encontrarSucursalCercana(String direccion) throws DireccionException{
		
		gestorDirecciones.comprobarDireccion(direccion);
		//
		//Lógica de negocio...
		//
		return new Sucursal();
	}
		
}

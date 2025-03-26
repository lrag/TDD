package com.curso.modelo.negocio;

import com.curso.modelo.entidad.Sucursal;
import com.curso.modelo.negocio.excepcion.SucursalException;
import com.curso.modelo.persistencia.SucursalDao;

public class GestorSucursales {

	private SucursalDao sucursalDao;

	public void setSucursalDao(SucursalDao sucursalDao) {
		this.sucursalDao = sucursalDao;
	}

	public void insertar(Sucursal sucursal) {
		sucursalDao.insertar(sucursal);
	}

	public Sucursal encontrarSucursalCercana(String direccion) throws SucursalException {
		//
		// L�gica de negocio para encontrar la sucursal m�s cercana. Si no se encuentra devuelve null (por ejemplo)
		//
		return new Sucursal();
	}

}


/*

public class GestorSucursales_MOCK extends GestorSucursales {

	public void setSucursalDao(SucursalDao sucursalDao) {
	}

	public void insertar(Sucursal sucursal) {
	}

	public Sucursal encontrarSucursalCercana(String direccion) throws SucursalException {
		return null;
	}

}

*/




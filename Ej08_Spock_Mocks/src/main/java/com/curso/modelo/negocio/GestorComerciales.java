package com.curso.modelo.negocio;

import java.util.List;

import com.curso.modelo.entidad.Comercial;
import com.curso.modelo.persistencia.ComercialDao;

public class GestorComerciales {

	private ComercialDao comercialDao;

	public void setComercialDao(ComercialDao comercialDao) {
		this.comercialDao = comercialDao;
	}
	
	public List<Comercial> encontrarComerciales(){
		//
		//Lógica de negocio para encontrar comerciales
		//
		return null;
	}
	
}

package com.curso.modelo.negocio;

import com.curso.modelo.negocio.excepcion.DireccionException;

public class GestorDirecciones {

	public GestorDirecciones() {
	}
	
	public String saludar() {
		return "OLA KE TAL";
	}
	
	public boolean comprobar() {
		return true;
	}
	
	public int sumar(int s1,int s2) {
		return s1+s2;
	}
	
	public void comprobarDireccion(String direccion) throws DireccionException {
		
		if(direccion==null) {
			throw new DireccionException("Direcci?n nula");
		}
		
		//Simulamos una conexi?n a una base de datos de direcciones 
		if(direccion.toUpperCase().contains("FALSA")) {
			throw new DireccionException("Esta direccion es falsa");
		}
		
		//Y si llegamos hasta aqui la direcci?n es correcta.
	}

}

/*
public class GestorDirecciones_MOCK {

	@Override
	public GestorDirecciones() {
	}
	
	@Override
	public void comprobarDireccion(String direccion) throws DireccionException {
	}

}
*/
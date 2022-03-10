package com.curso.modelo.negocio;

import com.curso.modelo.negocio.excepcion.DireccionException;

public class GestorDirecciones {

	public GestorDirecciones() {
	}
	
	public void comprobarDireccion(String direccion) throws DireccionException {
		
		if(direccion==null) {
			throw new DireccionException("Dirección nula");
		}
		
		//Simulamos una conexión a una base de datos de direcciones 
		if(direccion.toUpperCase().contains("FALSA")) {
			throw new DireccionException("Esta direccion es falsa");
		}
		
		//Y si llegamos hasta aqui la dirección es correcta.
	}

}

/*
package com.curso.modelo.negocio;

import com.curso.modelo.negocio.excepcion.DireccionException;

public class GestorDirecciones_Dummie extends GestorDirecciones {
	
	public void comprobarDireccion(String direccion) throws DireccionException {
	}

}
*/

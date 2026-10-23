package com.proyecto;

import com.proyecto.modelo.entidad.Cesta;

public class EstadoCesta {

	private static Cesta cesta;
	
	public static Cesta getCesta() {
		if(cesta == null) {
			cesta = new Cesta();
		}
		return cesta;
	}
	
	public static void eliminarCesta() {
		cesta = null;
	}
	
}

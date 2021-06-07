package com.curso.modelo.negocio;

import com.curso.modelo.entidad.Persona;

public class GestorPersonas {

	public GestorPersonas() {
	}

	public void insertar(Persona persona) throws Exception {
		System.out.println("Insertando:"+persona);
		if(persona.getNombre() == null) {
			throw new Exception("Nombre obligatorio");
		}
	}
	
}

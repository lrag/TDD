package com.proyecto;

import io.cucumber.java.Before;

public class Hooks {

	@Before
	public void limpiarCesta() {
		EstadoCesta.eliminarCesta();
	}

}

package com.proyecto.modelo.entidad;

import java.time.LocalDate;

public class Pedido {

	private String identificadorEnvio;
	private LocalDate fecha;
	private String direccionEntrega;
	private Cesta cesta;

	public Pedido(String identificadorEnvio, LocalDate fecha, String direccionEntrega, Cesta cesta) {
		super();
		this.identificadorEnvio = identificadorEnvio;
		this.fecha = fecha;
		this.direccionEntrega = direccionEntrega;
		this.cesta = cesta;
	}

	public String getIdentificadorEnvio() {
		return identificadorEnvio;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public Cesta getCesta() {
		return cesta;
	}

}

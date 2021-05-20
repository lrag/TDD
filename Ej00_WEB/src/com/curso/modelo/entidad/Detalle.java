package com.curso.modelo.entidad;

public class Detalle {

	private String producto;
	private Double cantidad;

	public Detalle() {
		super();
	}

	public Detalle(String producto, Double cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Detalle [producto=" + producto + ", cantidad=" + cantidad + "]";
	}
	
}

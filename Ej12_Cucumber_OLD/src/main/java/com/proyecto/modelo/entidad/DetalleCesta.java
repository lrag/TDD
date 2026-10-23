package com.proyecto.modelo.entidad;

public class DetalleCesta {

	private int cantidad;
	private Producto producto;

	public DetalleCesta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetalleCesta(int cantidad, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void aumentarCantidad(int cantidad) {
		this.cantidad += cantidad;
	}

}

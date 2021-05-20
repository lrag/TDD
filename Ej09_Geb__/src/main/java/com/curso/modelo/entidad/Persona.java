package com.curso.modelo.entidad;

public class Persona {

	private String nombre;
	private String direccion;
	private String telefono;

	public Persona() {
	}

	public Persona(String nombre, String direccion, String telefono) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}	

}








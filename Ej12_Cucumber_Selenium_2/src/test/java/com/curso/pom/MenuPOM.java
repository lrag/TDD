package com.curso.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPOM {

	/*
	 * <nav class="navbar navbar-inverse"> <div class="container-fluid"> <ul
	 * class="nav navbar-nav"> <li><a href="inicio.jsp">Inicio</a></li> <li><a
	 * href="SVClientes">Clientes</a></li> <li><a href="#">Productos</a></li> <li><a
	 * href="#">Empleados</a></li> <li><a href="#" id="linkSalir">Salir</a></li>
	 * </ul> <p id="nombreUsuario" class="navbar-text navbar-right">Usuario: Fistro
	 * Pecador&nbsp;&nbsp;&nbsp;</p> </div> </nav>
	 */

	@FindBy(id = "nombreUsuario")	private WebElement nombreUsuario;
	@FindBy(linkText = "Inicio")    private WebElement enlaceInicio;
	@FindBy(linkText = "Clientes")  private WebElement enlaceClientes;
	@FindBy(linkText = "Productos") private WebElement enlaceProductos;
	@FindBy(linkText = "Empleados") private WebElement enlaceEmpleados;
	@FindBy(id = "linkSalir")  	    private WebElement enlaceSalir;

	public MenuPOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getNombreUsuario() {
		return nombreUsuario;
	}

	public WebElement getEnlaceInicio() {
		return enlaceInicio;
	}

	public WebElement getEnlaceClientes() {
		return enlaceClientes;
	}

	public WebElement getEnlaceProductos() {
		return enlaceProductos;
	}

	public WebElement getEnlaceEmpleados() {
		return enlaceEmpleados;
	}

	public WebElement getEnlaceSalir() {
		return enlaceSalir;
	}

}
package com.curso.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormularioClientesPOM {
	// Referencia al driver con el que se buscarán los elementos
	private WebDriver driver;

	@FindBy(id = "btnInsertar")	 private WebElement btnInsertar; // Se declara a nulo, Selenium inyectará el valor
	@FindBy(id = "btnModificar") private WebElement btnModificar;
	@FindBy(id = "btnBorrar") 	 private WebElement btnBorrar;
	@FindBy(xpath = "//input[@type='submit' and @value='Cancelar']") private WebElement btnCancelar;

	@FindBy(id = "nombre")    private WebElement tfNombre;
	@FindBy(id = "direccion") private WebElement tfDireccion;
	@FindBy(id = "telefono")  private WebElement tfTelefono;

	public FormularioClientesPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebElement getBtnInsertar() {
		return btnInsertar;
	}

	public WebElement getBtnModificar() {
		return btnModificar;
	}

	public WebElement getBtnBorrar() {
		return btnBorrar;
	}

	public WebElement getBtnCancelar() {
		return btnCancelar;
	}

	public WebElement getTfNombre() {
		return tfNombre;
	}

	public WebElement getTfDireccion() {
		return tfDireccion;
	}

	public WebElement getTfTelefono() {
		return tfTelefono;
	}
	
}

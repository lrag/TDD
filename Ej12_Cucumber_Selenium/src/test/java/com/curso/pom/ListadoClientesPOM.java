package com.curso.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ListadoClientesPOM {
	private WebDriver driver;

	@FindBy(className = "titulo") private WebElement h1Titulo; // Se declara a nulo, Selenium inyectará el valor
	@FindBy(id = "btnNuevo")      private WebElement btnNuevo;
	@FindBy(id = "tablaClientes") private WebElement tablaClientes; //Es el tbody
	
	@FindBy(xpath = "//tbody[@id='tablaClientes']/tr[last()]")	  private WebElement ultimaFila;
	@FindBy(xpath = "//tbody[@id='tablaClientes']/tr[1]")         private WebElement primeraFila;
	@FindBy(xpath = "//tbody[@id='tablaClientes']/tr[last()]/td") private List<WebElement> tdsUltimaFila;
	@FindBy(xpath = "//tbody[@id='tablaClientes']/tr[1]/td[1]")   private WebElement primerTdPrimeraFila;
	
	public ListadoClientesPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebElement getH1Titulo() {
		return h1Titulo;
	}

	public WebElement getBtnNuevo() {
		return btnNuevo;
	}

	public WebElement getTablaClientes() {
		return tablaClientes;
	}

	public WebElement getUltimaFila() {
		return ultimaFila;
	}

	public WebElement getPrimeraFila() {
		return primeraFila;
	}

	public List<WebElement> getTdsUltimaFila() {
		return tdsUltimaFila;
	}

	public WebElement getPrimerTdPrimeraFila() {
		return primerTdPrimeraFila;
	}
	
}

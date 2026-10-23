package com.curso.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InicioPOM {

	@FindBy(className = "titulo")    private WebElement titulo; 
	
	public InicioPOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getTitulo() {
		return titulo;
	}

}
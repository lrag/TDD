package com.curso.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOM {

	@FindBy(className = "titulo")    private WebElement titulo; 
	@FindBy(name      = "login")     private WebElement login;
	@FindBy(name      = "pw")        private WebElement pw;
	@FindBy(id        = "btnEntrar") private WebElement btnEntrar;
	
	public LoginPOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getTitulo() {
		return titulo;
	}

	public WebElement getLogin() {
		return login;
	}

	public WebElement getPw() {
		return pw;
	}

	public WebElement getBtnEntrar() {
		return btnEntrar;
	}	

}

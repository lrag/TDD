package pruebas_

import geb.Browser
import geb.Page
import pruebas.Formulario3
import pruebas.LoginPage3

import org.openqa.selenium.Keys

//Esto está en 'src/test/resources/GebConfig.groovy'
//System.setProperty("webdriver.gecko.driver",
//	"src/test/resources/drivers/geckodriver/geckodriver.exe")	

class LoginPage3 extends Page {
	static url = "/Ej09_Geb/login.html"
	
	static at = {
		title == "Login"
	}
	
	static content = {
		login     { $("[name=login]") }
		pw        { $("[name=pw]") }
		btnEntrar { $("#btnEntrar") }
	}	
}

class Formulario3 extends Page {
	static url = "/Ej09_Geb/formulario.html"
	
	static at = {
		title == "Formulario"
	}
	
	static content = {
		id           { $("[name=id]") }
		titulo       { $("[name=titulo]") }
		director     { $("[name=director]") }
		genero       { $("[name=genero]") }
		fechaEstreno { $("[name=fechaEstreno]") }
		btnInsertar  { $("#btnInsertar") }
		btnModificar { $("#btnModificar") }
	    btnBorrar    { $("#btnBorrar") }		
	}	
}


Browser.drive {
	
	setBaseUrl("http://localhost:8080")
	
	
	//LoginPage3 page = to(LoginPage3)
	//def page = to(LoginPage3)
	//def page = to LoginPage3
	to LoginPage3

	Thread.sleep(1000)
	page.login.value("aaa")
	Thread.sleep(750)
	page.pw.value("bbb")

	Thread.sleep(2000)
	
	to Formulario3
	Thread.sleep(1000)
	page.titulo << "Alien"
	Thread.sleep(750)
	page.director << "Ridley Scott"
	Thread.sleep(750)
	page.genero << "Ci-Fi"
	Thread.sleep(750)
	page.fechaEstreno << "1979"
	
	Thread.sleep(1500)	
	page.btnInsertar.click()
		

}



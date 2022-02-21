package pruebas

import geb.Browser
import geb.Page
import pruebas_.FormularioPage
import pruebas_.LoginPage

import org.openqa.selenium.Keys

//Esto está en 'src/test/resources/GebConfig.groovy'
//System.setProperty("webdriver.gecko.driver",
//	"src/test/resources/drivers/geckodriver/geckodriver.exe")	

class LoginPage extends Page {
	static url = "/Ej09_Geb/login.html"
}

class FormularioPage extends Page {
	static url = "/Ej09_Geb/formulario.html"
}

Browser.drive {
	
	setBaseUrl("http://localhost:8080")
	
	to(LoginPage)
	Thread.sleep(2000)
	//En groovy nos podemos ahorrar los parentesis
	to FormularioPage

}



package pruebas

import geb.Browser
import geb.Page
import org.openqa.selenium.Keys

//Esto est� en 'src/test/resources/GebConfig.groovy'
//System.setProperty("webdriver.gecko.driver",
//	"src/test/resources/drivers/geckodriver/geckodriver.exe")	

class LoginPage2 extends Page {
	static url = "/Ej09_Geb/login.html"
	
	//At guarda un aserto que se eval�a nada m�s cargarse la p�gina
	static at = {
		title == "Login"
	}
}

class FormularioPage2 extends Page {
	static url = "/Ej09_Geb/formulario.html"
	
    static at = {
        title == "Formulario"
    }
}

Browser.drive {
	
	setBaseUrl("http://localhost:8080")
	
	to(LoginPage2)
	Thread.sleep(2000)
	to(FormularioPage2)

}



package pruebas

import geb.Browser
import geb.Page
import org.openqa.selenium.Keys

class LoginPage extends Page {
	
}


Browser.drive {
	
	//Esto está en 'src/test/resources/GebConfig.groovy'
	//System.setProperty("webdriver.gecko.driver",
	//	"src/test/resources/drivers/geckodriver/geckodriver.exe")	
	
	go "http://localhost:8080"
	$("[name=q]") << "Spring security"+Keys.ENTER

}



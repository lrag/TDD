package pruebas

import geb.Browser
import org.openqa.selenium.Keys

//Esto está en 'src/test/resources/GebConfig.groovy'
//System.setProperty("webdriver.gecko.driver",
//	"src/test/resources/drivers/geckodriver/geckodriver.exe")	


Browser.drive({	
	
	go "http://www.google.es"
	$("[name=q]") << "Spring security"+Keys.ENTER

})//.quit()



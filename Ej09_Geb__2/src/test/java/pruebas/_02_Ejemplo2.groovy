package pruebas

import geb.Browser
import org.openqa.selenium.Keys

Browser.drive {
	
	//Esto está en 'src/test/resources/GebConfig.groovy'
	//System.setProperty("webdriver.gecko.driver",
	//	"src/test/resources/drivers/geckodriver/geckodriver.exe")	
	
	go "http://www.google.es"
	$("[name=q]") << "Spring security"+Keys.ENTER

}//.quit()



package pruebas

import geb.Browser
import org.openqa.selenium.Keys

//Esto está en 'src/test/resources/GebConfig.groovy'
//System.setProperty("webdriver.gecko.driver",
//	"src/test/resources/drivers/geckodriver/geckodriver.exe")	

Browser.drive {	
	
	setBaseUrl("http://localhost:8080") 
	
	go "/Ej09_Geb/index.html"	
	Thread.sleep(2000)
	go "/Ej09_Geb/formulario.html"	
	Thread.sleep(2000)
	go "/Ej09_Geb/login.html"	
	Thread.sleep(2000)
	go "/Ej09_Geb/DoubleClickDemo.html"	
	Thread.sleep(2000)
	go "/Ej09_Geb/DragAndDropDemo.html"	
	Thread.sleep(2000)

}



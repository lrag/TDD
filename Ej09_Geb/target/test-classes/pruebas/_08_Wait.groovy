package pruebas

import geb.Browser
import geb.Page
import pruebas_.Index

//Esto está en 'src/test/resources/GebConfig.groovy'
//System.setProperty("webdriver.gecko.driver",
//	"src/test/resources/drivers/geckodriver/geckodriver.exe")	


class Index extends Page {
	
	static url = "/Ej09_Geb/index.html"
	
	static content = {
		boton (wait: true) { $("#btn-1") }
	}
}

Browser.drive {	
	
	setBaseUrl("http://localhost:8080") 
	
	//Sin Page
	//
	//go "/Ej09_Geb/index.html"	
	//
	//Esto falla porque el botón no aparece hasta cuantro segundos despues
	//de terminar de cargarse la página
	//def boton = $("#btn-1")
	//boton.click()
	//	
	//def boton = waitFor { $("#btn-1") } 
	//boton.click()
	
	//Thread.sleep(3000)

	to Index
	page.boton.click()
			
}



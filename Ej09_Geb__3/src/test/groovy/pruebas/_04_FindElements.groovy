package pruebas

import geb.Browser
import geb.navigator.Navigator
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

//Esto est� en 'src/test/resources/GebConfig.groovy'
//System.setProperty("webdriver.gecko.driver",
//	"src/test/resources/drivers/geckodriver/geckodriver.exe")	

Browser.drive {	
	
	setBaseUrl("http://localhost:8080") 
	
	go "/Ej09_Geb/index.html"	
	
	//Para localizar elmentos se utiliza el m�todo $
	//Est� basado en jQuery
	
	//Se puede usar cualquier selector existente en Selenium WebDriver
	$(By.id("user"))
	$(By.name("username"))
	$(By.className("in-user"))
	$(By.xpath("/html/body/form/input"))
	
	//Pero lo com�n es utilizar selectores css, como en jQuery
	$("#user")
	$("[name=username]")
	
	//El metodo $ devuelve geb.navigator.Navigator, que funciona
	//igual que el objeto jQuery
	Navigator elem = $(".in-user")
	println elem.value()
	
	//println $("a").text() //ZAS!
	println $("a")*.text() //Devuelve una coleci�n 
	println $("a").getAt(0).text()
	println $("a").getAt(1).text()
	
	//Cuando el selector devuelve m�s de un elemento podemos iterarlo
	$("a").collect { 
		//Cada elemento es 'it'
		println it.text()
	}
	
}



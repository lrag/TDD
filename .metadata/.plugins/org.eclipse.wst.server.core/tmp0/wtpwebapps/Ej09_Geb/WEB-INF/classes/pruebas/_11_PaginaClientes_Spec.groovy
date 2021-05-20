package pruebas

import geb.Page
import geb.spock.GebSpec
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

class LoginPage4 extends Page {
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

class FormularioClientes extends Page {
	static url = "/Ej09_Geb/seguro/SVClientes?accion=verFormulario"
		
	static at = {
		title == "Formulario"
	}
	
	static content = {
		id           { $("[name=idCliente]") }
		nombre       { $("[name=nombre]") }
		direccion    { $("[name=direccion]") }
		telefono     { $("[name=telefono]") }
		btnInsertar  { $("#btnInsertar") }
		btnModificar { $("#btnModificar") }
		btnBorrar    { $("#btnBorrar") }			
	}
}

class _11_PaginaClientes_Spec extends GebSpec {
		
	def setup(){
		//System.setProperty(
		//		"webdriver.gecko.driver",
		//		"src/test/resources/drivers/geckodriver/geckodriver.exe");
		
		setBaseUrl("http://localhost:8080")
		
		to LoginPage4
		page.login << "aaa"
		page.pw << "bbb"
		page.btnEntrar.click()
		
	}	
	
	def "insertarCliente"() {

		given:
			def nombre = "John McClane"
			def direccion = "NY"
			def telefono = "987 654 321"
							 
		when:
			to FormularioClientes
			page.nombre << nombre
			page.direccion << direccion
			page.telefono << telefono
				
				
			page.btnInsertar.click()	

			Thread.sleep(3000)
			
			def fila = $("#tablaClientes").find("tr").last()
			def columnas = fila.find("td")
			println columnas.getAt(0).text()
			
		then:	
			columnas.getAt(0).text() == nombre
			columnas.getAt(1).text() == direccion
			columnas.getAt(2).text() == telefono
				
	}
	
}

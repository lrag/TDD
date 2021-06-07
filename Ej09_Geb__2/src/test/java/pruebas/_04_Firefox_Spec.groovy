package pruebas

import spock.lang.Specification
import org.openqa.selenium.Keys
import geb.Browser

class _04_Firefox_Spec extends Specification {
		
	def setup(){
		System.setProperty(
				"webdriver.gecko.driver",
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
	}	
	
	def "pantalla de login"() {
				
		given:
			def browser = new Browser()
			 
		when:
			browser.go "http://www.google.es"		
			browser.$("[name=q]") << "Spring security"+Keys.ENTER			
			 
			def q = browser.$("[name=q]")
			
		then:			
			q.value() == "Spring security"
	}
	
}

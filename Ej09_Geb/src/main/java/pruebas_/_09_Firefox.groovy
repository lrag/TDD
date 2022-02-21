package pruebas_

import spock.lang.Specification
import org.openqa.selenium.Keys
import geb.Browser

class _09_Firefox extends Specification {
		
	def setup(){
		System.setProperty(
				"webdriver.gecko.driver",
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
	}	
	
	def "google"() {
				
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

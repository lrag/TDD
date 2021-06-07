package pruebas

import geb.spock.GebSpec
import org.openqa.selenium.Keys

class _05_GebSpec_Spec extends GebSpec {
		
	def setup(){
		System.setProperty(
				"webdriver.gecko.driver",
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
	}	
	
	def "pantalla de login"() {
					 
		when:
			go "http://www.google.es"		
			$("[name=q]") << "Spring security"+Keys.ENTER	
			
			def q = $("[name=q]")		
			 
		then:			
			q.value() == "Spring security"		
	}
	
}

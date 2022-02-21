package pruebas

import geb.spock.GebSpec
import org.openqa.selenium.Keys

class _10_GebSpec extends GebSpec {
		
	def setup(){
		System.setProperty(
				"webdriver.gecko.driver",
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
	}	
	
	def "pantalla de login"() {
					 
		when:
			go "http://www.google.es"		
			$("[name=q]") << "Spring security"+Keys.ENTER	
			
			Thread.sleep(2000)
						
			def q = $("[name=q]").getAt(0)		
			 
		then:			
			q.value() == "Spring security"		
	
	}
	
}

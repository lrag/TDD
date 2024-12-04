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
			
			$("#L2AGLb").click()
					
			$("[name=q]") << "Spring security 4"+Keys.ENTER	
			Thread.sleep(2000)
			def q = $("[name=q]").getAt(0)		
			Thread.sleep(2000)
			
		then:			
			q.value() == "Spring security 4"		
	
	}
	
}

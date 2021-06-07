package pruebas

import geb.Browser
import org.openqa.selenium.Keys

//Esto está en 'src/test/resources/GebConfig.groovy'
//System.setProperty("webdriver.gecko.driver",
//	"src/test/resources/drivers/geckodriver/geckodriver.exe")

def browser = new Browser()
browser.go "http://www.google.es"
browser.$("[name=q]") << "tdd"+Keys.ENTER
//browser.quit()

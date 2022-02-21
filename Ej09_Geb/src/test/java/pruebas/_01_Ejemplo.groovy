package pruebas

import geb.Browser
import org.openqa.selenium.Keys

//Esto está en 'src/test/resources/GebConfig.groovy'
//System.setProperty("webdriver.gecko.driver",
//	"src/test/resources/drivers/geckodriver/geckodriver.exe")

//Browser es equivalente a Driver en Selenium
def browser = new Browser()

browser.go "http://www.google.es"

//JQuery
browser.$("[name=q]") << "tdd"+Keys.ENTER

//browser.quit()





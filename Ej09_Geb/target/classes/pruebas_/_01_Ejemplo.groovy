package pruebas_

import org.openqa.selenium.Keys

import geb.Browser

//Esto está en 'src/test/resources/GebConfig.groovy'
//System.setProperty("webdriver.gecko.driver",
//	"src/main/resources/drivers/geckodriver/geckodriver.exe")

System.setProperty("webdriver.chrome.driver",
	"src/main/resources/drivers/chromedriver/chromedriver.exe");

//Browser es equivalente a Driver en Selenium
def browser = new Browser()

browser.go "http://www.google.es"

//JQuery
browser.$("[name=q]") << "tdd"+Keys.ENTER

//browser.quit()





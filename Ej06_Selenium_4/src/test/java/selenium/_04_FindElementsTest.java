package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@Execution(ExecutionMode.SAME_THREAD)
public class _04_FindElementsTest {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.gecko.driver", 
				"src/test/resources/drivers/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void byId() {
		//Cargamos la página
		driver.get(Constantes.URL);
		//Buscamos elementos por id
		WebElement username = driver.findElement(By.id("user"));
		WebElement password = driver.findElement(By.id("pass"));
		
		//si no existiera, findElement lanza una excepcion de tipo NoSuchElementException asi que
		//no son necesarios los siguientes asertos:
		//assertNotNull(username);
		//assertNotNull(password);
	}
	
	@Test
	public void byName() {
		driver.get(Constantes.URL);
		//Buscamos elemento por nombre
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
	}
	
	@Test
	public void byClassName() {
		driver.get(Constantes.URL);
		//En una página pueden existir muchos elementos qon el mismo class name
		List<WebElement> elementos =driver.findElements(By.className("in-user"));
		
		//Si sabemos que solo hay uno o solo nos interesa el primero podemos utilizar findElement
		WebElement username = driver.findElement(By.className("in-user"));
		WebElement password = driver.findElement(By.className("in-pass"));
		
	}
	
	
	/*
	
	<html>
		<body>
			<div id="conyuge1">
				<input type="text" id="nombre"/>
			</div>
			<div id="conyuge2">
				<input type="text" id="nombre"/>
			</div>		
		</body>
	</tml>
	*/
	
	//podemos concretar más la busqueda, las className que estan dentro
	//del loginForm. La clase WebDriver y WebElement comparten la misma interfaz
	@Test
	public void concretarLaBusqueda() {
		driver.get(Constantes.URL);
		//el primer findElement nos devuelve un WebElement
		
		WebElement formulario = driver.findElement(By.id("loginForm"));
		
		//Esto busca en toda la página
		//WebElement username = driver.findElement(By.className("in-user"));
		//Y esta es un búsqueda a partir del elemento 'formulario'		
		WebElement username = formulario.findElement(By.className("in-user"));

		//Rebuscado, pero sirve de ejemplo (mejor con una consulta xpath)
		WebElement password = driver
			.findElement(By.id("loginForm"))
			.findElement(By.className("in-pass"));
	}
	
	/*
	<html>
		<body>
		
			<h1 class="titulo">Listado de clientes</h1>
			
			<div>
				Nombre:	<input type="text" id="nombre">
				<br/>
				Dirección:<input type="text" id="direccion">
			</div>
		
		</body>	
	</html>
	*/
	@Test
	public void byTagName() {
		driver.get(Constantes.URL);
		//busqueda por tagName, pueden ser varios, notese el findElements (plural)
		List<WebElement> labels = driver.findElements(By.tagName("label"));
		System.out.println("=================================");
		for (WebElement label : labels) {
			System.out.println(label.getText());			
		}
		assertEquals(labels.size(), 8);
	}
	
	@Test
	public void byLinkText() {
		driver.get(Constantes.URL);
		//Obtenermos un elemento <a> a traves de su texto
		//f12 con chrome y podemos inspeccionar los elementos
		WebElement enlace = driver.findElement(By.linkText("Página"));
	}
	
	@Test
	public void buscarAtributos() {
		driver.get(Constantes.URL);
		System.out.println("=================================");
		WebElement enlace = driver.findElement(By.linkText("Página"));
		//Podemos acceder a los atributos de un webElement con getDomAttribute
		String href = enlace.getDomAttribute("href");
		System.out.println("HREF:"+href);
		assertEquals("index.html", href);
		
		String clase = enlace.getDomAttribute("class");
		System.out.println("Class:"+clase);
	}
	
	@Test
	public void byPartialLinkText() {
		System.out.println("=================================");
		driver.get(Constantes.URL);
		//Si no sabemos el texto completo de un link pero sabemos por qué empieza
		List<WebElement> enlaces = driver.findElements(By.partialLinkText("Página"));
		for(WebElement enlace: enlaces) {
			System.out.println(enlace.getDomAttribute("href"));
		}
	}
	
	// Consultas XPath
	/*
	Expression	Description
	nodename	Selects all nodes with the name "nodename", hijos directos desde donde estemos
	/			Selects from the root node
	//			Selects nodes in the document from the current node that match the selection no matter where they are
	.			Selects the current node
	..			Selects the parent of the current node
	@			Selects attributes of a node


	<bookstores>
		<bookstore>
			<book></book>
		</bookstore>
		<bookstore>
			<book></book>
		</bookstore>
		<bookstore>
			<book></book>
		</bookstore>
		
		<movida>
			<bookstore>
				<book></book>
			</bookstore>		
		</movida>
		
	</bookstores>


	Path Expression	Result
	bookstore		Selects all nodes with the name "bookstore", hijos directos desde donde estemos
	/bookstores		Selects the root element bookstore
					Note: If the path starts with a slash ( / ) 
					it always represents an absolute path to an element!

	bookstore/book	Selects all book elements that are children of bookstore
	//book			Selects all book elements no matter where they are in the document
	bookstore//book	Selects all book elements that are descendant of the bookstore 
					element, no matter where they are under the bookstore element
	//@lang			Selects all attributes that are named lang


	Path Expression						Result
	/bookstore/book[1]					Selects the first book element that is the child of the bookstore element.
										Note: In IE 5,6,7,8,9 first node is[0], but according to W3C, it is [1]. To solve this 
										problem in IE, set the SelectionLanguage to XPath:
										In JavaScript: xml.setProperty("SelectionLanguage","XPath");
	/bookstore/book[last()]				Selects the last book element that is the child of the bookstore element
	/bookstore/book[last()-1]			Selects the last but one book element that is the child of the bookstore element
	/bookstore/book[position() < 3]		Selects the first two book elements that are children of the bookstore element
	//title[@lang]						Selects all the title elements that have an attribute named lang
	//title[@lang='en']					Selects all the title elements that have an attribute named lang with a value of 'en'
	/bookstore/book[price>35.00]		Selects all the book elements of the bookstore element that have a price element with 
	 									a value greater than 35.00
	/bookstore/book[price>35.00]/title	Selects all the title elements of the book elements of the bookstore element that have 
	 									a price element with a value greater than 35.00
				
	Wildcard	Description
	*			Matches any element node
	@*			Matches any attribute node
	node()		Matches any node of any kind

	Path Expression	Result
	/bookstore/*	Selects all the child nodes of the bookstore element
	//*				Selects all elements in the document
	//title[@*]		Selects all title elements which have any attribute

	Path Expression	Result
	//book/title | //book/price		Selects all the title AND price elements of all book elements
	//title | //price				Selects all the title AND price elements in the document
	/bookstore/book/title | //price	Selects all the title elements of the book element of the bookstore element AND all the price elements in the document

	*/	
	
	@Test
	public void byXPath1() {
		driver.get(Constantes.URL);
		//ruta absoluta, primer html, primer body, primer form, primer input (hijos directos)
		WebElement boton = driver.findElement(By.xpath("/html/body/form/input"));
		//System.out.println("=================================");
		//System.out.println(boton.getAttribute("value"));
		assertEquals(boton.getDomAttribute("value"), "Login");
	}
	
	@Test
	public void byXPath2() {
		driver.get(Constantes.URL);
		//en cualquier parte un input que su atributo type sea submit
		WebElement boton = driver.findElement(By.xpath("//input[@type='submit']"));
		assertEquals(boton.getDomAttribute("value"), "Login");
	}
	
	@Test
	public void byXPath3() {
		driver.get(Constantes.URL);
		//primer html, primer body/primer form/segundo div/primer input con atributo name = password
		WebElement input = driver.findElement(By.xpath("/html/body/form/div[2]/input[@name='password']"));
		assertEquals(input.getDomAttribute("placeholder"), "Un ejemplo...");
	}
	
	@Test
	public void byXPath4() {
		driver.get(Constantes.URL);
		//cualquier input cuyo atributo type sea submit Y su atributo value sea login
		WebElement button1 = driver.findElement(By.xpath("//input[@type='submit' and @value='Login']"));
		assertEquals(button1.getDomAttribute("value"), "Login");

		//cualquier input cuyo atributo type sea submit O su atributo value sea login
		WebElement button2 = driver.findElement(By.xpath("//input[@type='submit' or @value='Logout']"));
		assertEquals(button2.getDomAttribute("value"), "Login");

		//encuentrame todas las img que no tengan el atributo alt
		List<WebElement> imagesWithAlt = driver.findElements(By.xpath("//img[not(@alt)]"));
		assertEquals(imagesWithAlt.size(), 2);
	}
	
	@Test
	public void byXPath5() {
		driver.get(Constantes.URL);
		//encuentra cualquier input con cualquier atributo que tenga el valor "password"
		WebElement input = driver.findElement(By.xpath("//input[@*='password']"));
		assertEquals(input.getDomAttribute("placeholder"), "Un ejemplo...");
	}
	
	//https://www.w3schools.com/cssref/css_selectors.asp
	//Ejemplo de div > p
	//https://www.w3schools.com/cssref/tryit.asp?filename=trycss_sel_element_gt
	@Test
	public void byCssSelector1() {
		driver.get(Constantes.URL);
		//todos los label dentro de div dentro de form dentro de body y dentro de html
		WebElement username1 = driver.findElement(By.cssSelector("html body form div label"));
		assertEquals(username1.getText(), "Username:");

		//todos los label que su padre sea div que su padre sea form que su padre sea body que su padre sea html
		WebElement username2 = driver.findElement(By.cssSelector("html > body > form > div > label"));
		assertEquals(username2.getText(), "Username:");
	}
	
	@Test
	public void byCssSelector2() {
		driver.get(Constantes.URL);
		//Seleccionamos teoricamente todas las label, pero como es findElement solo devuelve el primero
		//que encuentra
		WebElement username = driver.findElement(By.cssSelector("label"));
		
		assertEquals(username.getText(), "Username:");
		//ahora si buscamos todos los label
		List<WebElement> labels = driver.findElements(By.cssSelector("label"));
		assertEquals(labels.size(), 8);
	}
	
	@Test
	public void byCssSelector3() {
		driver.get(Constantes.URL);
		//seleccionamos un input cuya clase sea in-user
		WebElement username = driver.findElement(By.cssSelector("input.in-user"));
		assertNotNull(username);
		
		//elemento input con id password
		WebElement pass1 = driver.findElement(By.cssSelector("input#pass"));
		assertEquals(pass1.getDomAttribute("placeholder"), "Un ejemplo...");
		
		//Para ajustar menos la busqueda (cualquier elemento con id password)
		//el primero qu ese encuentre
		WebElement pass2 = driver.findElement(By.cssSelector("#pass"));
		assertEquals(pass2.getDomAttribute("placeholder"), "Un ejemplo...");
	}
	
	@Test
	public void byCssSelector4() {
		driver.get(Constantes.URL);
		//un input que tenga un atributo name que sea password
		WebElement password = driver.findElement(By.cssSelector("input[name='password']"));
		assertEquals(password.getDomAttribute("placeholder"), "Un ejemplo...");
		
		//una imagen que tenga un atributo alt que sea Imagen1
		WebElement img = driver.findElement(By.cssSelector("img[alt='Imagen1']"));
		assertEquals(img.getDomAttribute("name"), "img1");
	}
	
	//Búsqueda de hijos
	@Test
	public void byCssSelector5() {
		driver.get(Constantes.URL);
		//un div con id lista-items con un hijo directo ul con otro hijo directo li
		//, de ahi accedemos al primer hijo (ya que hay 3 hijos directos con etiqueta <li>)
		//y le pide el input como hijo directo
		WebElement input1 = driver.findElement(By.cssSelector("div#lista-items > ul > li:first-child > input"));
		assertEquals(input1.getDomAttribute("value"), "Item 1");

		//lo mismo pero con el segundo hijo
		WebElement input2 = driver.findElement(By.cssSelector("div#lista-items > ul > li:nth-child(2) > input"));
		assertEquals(input2.getDomAttribute("value"), "Item 2");

		//lo mismo pero con el ultimo hijo
		WebElement input3 = driver.findElement(By.cssSelector("div#lista-items > ul > li:last-child > input"));
		assertEquals(input3.getDomAttribute("value"), "Item 3");
	}
	
	@Test
	public void byCssSelector6() {
		driver.get(Constantes.URL);
		//Div con id 'lista-items'
		//Primer ul
		//Primer li precedido por un li
		//->Segundo li
		WebElement segundoItem = driver.findElement(By.cssSelector("div#lista-items > ul > li + li"));
		assertEquals("Dos", segundoItem.getText());
	}
	
	@Test
	public void byCssSelector7() {
		driver.get(Constantes.URL);
		//el input que tenga el foco
		WebElement inputAutofocus = driver.findElement(By.cssSelector("input:focus"));
		assertEquals(inputAutofocus.getDomAttribute("value"), "Item 1");
		
		WebElement inputDisabled = driver.findElement(By.cssSelector("input:disabled"));
		assertEquals(inputDisabled.getDomAttribute("value"), "Item 2");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void byJQuery() {
		driver.get(Constantes.URL);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Elemento con id listaInteres que tengan un hijo directo que tenga un atributo checked
		List<WebElement> elementosChecked = (List<WebElement>) 
				js.executeScript("return jQuery.find('#listaIntereses > :checked')");
		assertEquals(elementosChecked.size(), 1);
		//Elemento con id listaIntereses que tenga un hijo directo input de tipo checkbox que
		//no este checked
		List<WebElement> elementosNotChecked = (List<WebElement>) 
				js.executeScript("return jQuery.find('#listaIntereses > input:checkbox:not(:checked)')");
		assertEquals(elementosNotChecked.size(), 2);
	}
	
	@AfterAll
	public static void tearDown() {
		//driver.quit();
	}
}

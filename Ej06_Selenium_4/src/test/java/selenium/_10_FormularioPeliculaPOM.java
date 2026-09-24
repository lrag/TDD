package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Page Object Model
/*
Selenium nos permite agrupar todos los elementos de una página web en una misma clase
PageFactory. De esta manera podemos reutilizar el código sin preocuparnos de localizar los
elementos dentro de la web.

Es un POJO
*/
public class _10_FormularioPeliculaPOM {

	//Referencia al driver con el que se buscarán los elementos
	//private WebDriver driver;

	@FindBy(id = "btnInsertar")    private WebElement btnInsertar; //Se declara a nulo, Selenium inyectará el valor
	@FindBy(id = "btnModificar")   private WebElement btnModificar;
	@FindBy(id = "btnBorrar")	   private WebElement btnBorrar;
	@FindBy(name = "idPelicula")   private WebElement tfIdPelicula;
	@FindBy(name = "titulo")	   private WebElement tfTitulo;
	@FindBy(name = "director")     private WebElement tfDirector;
	@FindBy(name = "genero")	   private WebElement tfGenero;
	@FindBy(name = "fechaEstreno") private WebElement tfFechaEstreno;

	//Inicializamos los atributos
	public _10_FormularioPeliculaPOM(WebDriver driver) {
		//this.driver = driver;
		//PageFactory.initElements(this.driver, this);
		
		PageFactory.initElements(driver, this);
	}

	public WebElement getBtnInsertar() {
		return btnInsertar;
	}

	public WebElement getBtnModificar() {
		return btnModificar;
	}

	public WebElement getBtnBorrar() {
		return btnBorrar;
	}

	public WebElement getTfIdPelicula() {
		return tfIdPelicula;
	}

	public WebElement getTfTitulo() {
		return tfTitulo;
	}

	public WebElement getTfDirector() {
		return tfDirector;
	}

	public WebElement getTfGenero() {
		return tfGenero;
	}

	public WebElement getTfFechaEstreno() {
		return tfFechaEstreno;
	}

}

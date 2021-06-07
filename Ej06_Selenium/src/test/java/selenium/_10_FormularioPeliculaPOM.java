package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Page Object Model
/*
 * Selenium nos permite agrupar todos los elementos de una página web en una misma clase
PageFactory. De esta manera podemos reutilizar el código sin preocuparnos de localizar los
elementos dentro de la web.
 */
public class _10_FormularioPeliculaPOM {

	private WebDriver driver;

	@FindBy(id = "btnInsertar")    private WebElement btnInsertar;
	@FindBy(id = "btnModificar")   private WebElement btnModificar;
	@FindBy(id = "btnBorrar")	   private WebElement btnBorrar;
	@FindBy(name = "idPelicula")   private WebElement idPelicula;
	@FindBy(name = "titulo")	   private WebElement titulo;
	@FindBy(name = "director")     private WebElement director;
	@FindBy(name = "genero")	   private WebElement genero;
	@FindBy(name = "fechaEstreno") private WebElement fechaEstreno;

	// Inicializamos los atributos
	public _10_FormularioPeliculaPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
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

	public WebElement getIdPelicula() {
		return idPelicula;
	}

	public WebElement getTitulo() {
		return titulo;
	}

	public WebElement getDirector() {
		return director;
	}

	public WebElement getGenero() {
		return genero;
	}

	public WebElement getFechaEstreno() {
		return fechaEstreno;
	}

}

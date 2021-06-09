package ejemplos;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.curso.modelo.negocio.Calculadora;

public class _04_Ciclo_de_vida_2 {

	private Calculadora calculadora;

	//CalculadoraTest se instanciará una única vez y se ejecutarán todos sun métodos marcados con @Test
	public _04_Ciclo_de_vida_2() {
		super();
		System.out.println("Instanciando 04_Ciclo_de_vida_2");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println(Thread.currentThread().getName()+"-Before test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println(Thread.currentThread().getName()+"-After test");
	}	
	
	@BeforeClass //Equivalente a @BeforeAll de JUnit
	public void beforeClass() {
		System.out.println(Thread.currentThread().getName()+"-Before class");
		//Si creamos aqui la calculadora se reutilizará en todos los test de esta clase (peligro!)
		//calculadora = new Calculadora();
	}

	@AfterClass //Equivalente a @AfterAll de JUnit
	public void afterClass() {
		System.out.println(Thread.currentThread().getName()+"-After class");
	}

	@BeforeMethod //Equivalente a @BeforeEach de JUnit
	public void beforeMethod() {
		System.out.println(Thread.currentThread().getName()+"-Before method");
		//Si creamos aqui la calculadora se cada test de esta clase utilizará una instancia distinta (correcto)
		calculadora = new Calculadora();
	}
	
	@AfterMethod //Equivalente a @AfterEach de JUnit
	public void afterMethod() {
		System.out.println(Thread.currentThread().getName()+"-After method");
	}

	@Test
	public void laSumaDebeSerCorrecta() {	
		
		System.out.println(Thread.currentThread().getName()+"-laSumaDebeSerCorrecta");
		
		//DADO
		double s1 = 2d;
		double s2 = 2d;
		//CUANDO
		Double res = calculadora.sumar(s1, s2);
		//ENTONCES		
		assertEquals(Double.valueOf(4), res);
	}
	
	@Test
	public void dividirFuncionaCorrectamente() throws Exception {
		System.out.println(Thread.currentThread().getName()+"-dividirFuncionaCorrectamente");
		assertEquals(Double.valueOf(5d), calculadora.dividir(10d, 2d));
	}

	@Test
	public void cuadradoFuncionaCorrectamente() throws Exception {
		System.out.println(Thread.currentThread().getName()+"-cuadradoFuncionaCorrectamente");
		assertEquals(Double.valueOf(625d), calculadora.cuadrado(25d));
	}
	
}


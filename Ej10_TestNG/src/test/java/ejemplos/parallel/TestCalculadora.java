package ejemplos.parallel;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.curso.modelo.negocio.Calculadora;

public class TestCalculadora {

	private static Calculadora calculadora;

	//CalculadoraTest se instanciará una única vez y se ejecutarán todos sun métodos marcados con @Test
	public TestCalculadora() {
		super();
		System.out.println("Instanciando TestCalculadora");
	}


	@BeforeMethod
	public void beforeMethod() {
		calculadora = new Calculadora();
	}
	
	@Test
	public void laSumaDebeSerCorrecta() {	
		
		System.out.println(Thread.currentThread().getName()+"-Test SUMA");
		
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
		System.out.println(Thread.currentThread().getName()+"-Test DIVIDIR");
		assertEquals(Double.valueOf(5d), calculadora.dividir(10d, 2d));
	}

	@Test
	public void cuadradoFuncionaCorrectamente() throws Exception {
		System.out.println(Thread.currentThread().getName()+"-Test CUADRADO");
		assertEquals(Double.valueOf(625d), calculadora.cuadrado(25d));
	}
	
}


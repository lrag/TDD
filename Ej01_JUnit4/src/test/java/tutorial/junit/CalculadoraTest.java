package tutorial.junit;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculadoraTest {
	
	
	public CalculadoraTest() {
		System.out.println("Creando CalculadoraTest");
	}

	@Test
	public void testMultiplicar() {
		Calculadora tester = new Calculadora();
		assertEquals("Result", 50, tester.multiplicar(10, 5));
	}
	
	@Test
	public void testSumar() {
		Calculadora tester = new Calculadora();
		assertEquals("Result", 15, tester.sumar(10, 5));
	}	

}

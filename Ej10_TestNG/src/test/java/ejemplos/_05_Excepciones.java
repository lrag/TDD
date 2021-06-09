package ejemplos;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

import org.testng.Assert;
import org.testng.Assert.ThrowingRunnable;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.curso.modelo.negocio.Calculadora;
import com.curso.modelo.negocio.CalculadoraException;

public class _05_Excepciones {

	private Calculadora calculadora;

	public _05_Excepciones() {
		super();
		System.out.println("Instanciando 05_Excepciones");
	}

	@BeforeMethod
	public void beforeMethod() {
		calculadora = new Calculadora();
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("After class");
	}

	@Test
	public void dividirFuncionaCorrectamente() throws Exception {
		double dividendo = 50;
		double divisor = 2;
		
		double rs = calculadora.dividir(dividendo, divisor);
		assertEquals(25, rs);
	}	
	
	//Excepciones
	@Test
	public void divisionPorCeroDebeLanzarExcepcion() {
		
		double dividendo = 100;
		double divisor   = 0;
		
		//Podríamos controlarlo nosotros:
		/*
		try {
			calculadora.dividir(dividendo, divisor);
			//MAL
			fail("Pues no ha lanzado la excepción!");
		} catch (CalculadoraException e) {
			//OK
		} catch(Exception e) {
			fail("Pues ha lanzado OTRA excepción!");			
		}
		*/
		
		/*
		Assert.assertThrows(CalculadoraException.class, new ThrowingRunnable() {
			public void run() throws Exception {
				calculadora.dividir(dividendo, divisor);				
			}
		});
		*/		 
		
		//AsserThrows es void. No nos entregan la excepcion
		assertThrows(CalculadoraException.class,  
			         () -> calculadora.dividir(dividendo, divisor));
	}
	
}






















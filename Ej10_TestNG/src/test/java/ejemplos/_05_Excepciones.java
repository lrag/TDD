package ejemplos;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

import org.testng.Assert;
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
	public static void afterClass() {
		System.out.println("After class");
	}

	@Test
	public void dividirFuncionaCorrectamente() {
		double dividendo = 50;
		double divisor = 2;
		
		try {
			double rs = calculadora.dividir(dividendo, divisor);
			assertEquals(25, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		Assertions.assertThrows(CalculadoraException.class, new Executable() {
			public void execute() throws Throwable {
				calculadora.dividir(dividendo, divisor);				
			}
		});
		 */
		
		assertThrows(CalculadoraException.class,  
			                    () -> calculadora.dividir(dividendo, divisor));
	
	}
	
}















		/*
		try {
			calculadora.dividir(10d, 10d);
			fail("ZASCA");
		} catch (Exception e) {
			//Me pongo un visto
		}
		*/
		
		//Con clase interna anónima
		/*
		assertThrows(Exception.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				calculadora.dividir(10d, 0d);
			}
		});
		*/
		
		//Con expresiones lambda
		//assertThrows(Exception.class, () -> calculadora.dividir(10d, 0d));	
	
	/*
	}

}
*/



















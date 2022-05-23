package ejemplos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.curso.modelo.negocio.Calculadora;
import com.curso.modelo.negocio.CalculadoraException;

public class _05_Excepciones {

	private Calculadora calculadora;

	public _05_Excepciones() {
		super();
		System.out.println("Instanciando 05_Excepciones");
	}

	@BeforeEach
	public void beforeEach() {
		calculadora = new Calculadora();
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
		
		//Podr�amos controlarlo nosotros:
		/*
		try {
			calculadora.dividir(dividendo, divisor);
			//MAL
			Assertions.fail("Pues no ha lanzado la excepci�n!");
		} catch (CalculadoraException e) {
			//OK, Esto es lo que esperabamos!
			System.out.println("OK");
		} catch(Exception e) {
			Assertions.fail("Pues ha lanzado OTRA excepci�n!");			
		}
		*/
		
		/*
		//Con clase interna an�nima
		Executable exe = new Executable() {
			@Override
			public void execute() throws Throwable {
				calculadora.dividir(dividendo, divisor);
			}
		};	
		Assertions.assertThrows(CalculadoraException.class, exe);
		*/
		
		/*
		//�dem, sin declarar primero la variable 'exe'
		Assertions.assertThrows(CalculadoraException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				calculadora.dividir(dividendo, divisor);
			}
		});
		*/

		/*
		Assertions.assertThrows(
				CalculadoraException.class,  
				() -> calculadora.dividir(dividendo, divisor)
			);	
		*/	
		
		//Nos entregan la excepci�n que se ha lanzado por si nos hace falta
		CalculadoraException e = 
			Assertions.assertThrows(
				CalculadoraException.class,  
                () -> calculadora.dividir(dividendo, divisor)
            );	
		assertEquals("Divisi�n por cero", e.getMessage());
		
	}	
	
}


















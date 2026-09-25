package ejemplos_assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

		//assertEquals(25, rs);
		assertThat(rs).isEqualTo(25);
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
			fail("Pues no ha lanzado la excepción!");
		} catch (CalculadoraException e) {
			//OK, Esto es lo que esperabamos!
			System.out.println("OK");
			return;
		} catch(Exception e) {
			fail("Pues ha lanzado OTRA excepción!");
		}
		*/

		/*
		//Ni hablar de esto (Executable es de JUnit, no existe en AssertJ)
		ComprobadorExcepcionDivisionPorCero c = new ComprobadorExcepcionDivisionPorCero();
		c.calculadora = calculadora;
		c.dividendo = dividendo;
		c.divisor = divisor;

		Assertions.assertThrows(CalculadoraException.class , c);
		*/


		//Con clase interna anónima
		/*
		Executable exe = new Executable() {
			public void execute() throws Throwable {
				calculadora.dividir(dividendo, divisor);
			}
		};
		Assertions.assertThrows(CalculadoraException.class, exe);
		*/


		//Ídem, sin declarar primero la variable 'exe'
		/*
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

		//Con AssertJ, assertThatThrownBy encadena "qué excepción se lanza" y "qué mensaje tiene" en una sola comprobación
		//CalculadoraException e = Assertions.assertThrows(
		//		CalculadoraException.class,
		//        () -> calculadora.dividir(dividendo, divisor)
		//    );
		//assertEquals("División por cero", e.getMessage());
		assertThatThrownBy(() -> calculadora.dividir(dividendo, divisor))
				.isInstanceOf(CalculadoraException.class)
				.hasMessage("División por cero");

	}

}


class ComprobadorExcepcionDivisionPorCero implements Executable {

	public Calculadora calculadora;
	public double dividendo;
	public double divisor;

	@Override
	public void execute() throws Throwable {
		// TODO Auto-generated method stub
		calculadora.dividir(dividendo, divisor);
	}

}

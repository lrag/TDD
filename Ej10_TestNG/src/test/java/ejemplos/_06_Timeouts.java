package ejemplos;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.curso.modelo.negocio.Calculadora;

public class _06_Timeouts {

	private Calculadora calculadora;

	public _06_Timeouts() {
		super();
		System.out.println("Instanciando 06_Timeouts");
	}

	@BeforeMethod
	public void beforeMethod() {
		calculadora = new Calculadora();
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("After class");
	}

	@Test(timeOut = 4000) //No espera a que el test acabe. Lanza la excepción cuando se cumple el tiempo estipulado
	public void calculoExtremadamenteComplejoDebeRealizarseRapido() throws InterruptedException {
		
		/*
		//Si no existiera el assertTimeout
		long inicio = System.currentTimeMillis();
		calculadora.calculoExtremadamenteComplejo();
		long fin = System.currentTimeMillis();
		assertTrue(fin-inicio < 2000, "El metodo no se ha ejecutado en el tiempo estipulado");
		*/
		
		calculadora.calculoExtremadamenteComplejo();
	}

}



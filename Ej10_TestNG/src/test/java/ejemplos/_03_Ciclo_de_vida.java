package ejemplos;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import com.curso.modelo.negocio.Calculadora;


public class _03_Ciclo_de_vida {

	//Se creará una única instancia de la case para todos los @Test
	//Los atributos de la clase se compartirán por todos ellos
	private Calculadora calculadora = new Calculadora();

	public _03_Ciclo_de_vida() {
		super();
		//calculadora = new Calculadora();
		System.out.println("Creando CicloDeVidaTest");
	}

	@Test
	public void primerTest() {		
		
		System.out.println(this);
		
		//Dados
		double n1 = 5;
		double n2 = 10;
		
		//Cuando
		double n3 = calculadora.sumar(n1,n2);
		
		//Entonces
		assertEquals(15, n3);		
	}

	@Test
	public void segundoTest() {

		System.out.println(this);
		
		double n1 = 5;
		double n2 = calculadora.cuadrado(n1);
		assertEquals(25, n2);
	}

	@Test
	public void tercerTest() throws Exception {

		System.out.println(this);
		
		double n1 = 30;
		double n2 = 2;
		double n3 = calculadora.dividir(n1, n2);
		assertEquals(15, n3);
	}
		
}

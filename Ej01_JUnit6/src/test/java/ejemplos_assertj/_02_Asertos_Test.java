package ejemplos_assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.curso.modelo.negocio.Calculadora;

public class _02_Asertos_Test {

	//@Test
	public void test1() {

		System.out.println("Test 1");

		//dados
		int n1 = 100;
		int n2 = 200;

		//cuando
		int n3 = n1 + n2;

		//entonces

		//Si no existieran los asertos:
		//if(n3 != 301) {
		//	System.out.println("Vamos listos porque el + de java no funciona");
		//	throw new RuntimeException("La suma no es correcta");
		//}

		//Utiliza equals, no el '=='

		//Con import estático
		//assertThat(n3).isEqualTo(300);

		//Lo mismo, pero sin import estático:
		//Assertions.assertEquals(301, n3);
		Assertions.assertThat(n3).isEqualTo(301);

	}

	//@Test
	public void test2() {
		System.out.println("Test 2");
		int n1 = 100;
		int n2 = 200;

		//Utiliza equals, no el '=='
		//assertThat(n1).isNotEqualTo(n2);
		//assertNotEquals(n1, n2, "Son iguales y no tendrían que serlo!");
		assertThat(n1).withFailMessage("Son iguales y no tendrían que serlo!").isNotEqualTo(n2);
	}

	//@Test
	public void test3() {
		System.out.println("Test 3");
		int n1 = 100;
		int n2 = 200;
		//Recibe una expresión que se resuelve en un boolean
		//Con assertTrue/isTrue podríamos hacer cualquier comprobación
		//assertTrue(n2 > n1);
		assertThat(n2 >  n1).isTrue();
	}

	//@Test
	public void test4() {
		System.out.println("Test 4");
		int n1 = 100;
		int n2 = 200;
		//assertTrue(n2 < n1);
		assertThat(n2 < n1).isTrue();
	}

	//@Test
	public void test5() throws Exception {
		System.out.println("Test 5");
		String txt = null;

		//assertNull(txt);
		assertThat(txt).isNull();
	}

	//@Test
	public void test6() {
		System.out.println("Test 6");
		String txt = new String("TXT");

		//assertNotNull(txt);
		assertThat(txt).isNotNull();
	}

	//@Test
	public void test7() {
		System.out.println("Test 7");

		//Cuidado con el autoboxing
		Integer i1 = 127;
		Integer i2 = 127;

		Integer i3 = i1 + i2;

		Calculadora c1 = new Calculadora();
		Calculadora c2 = c1;

		//isEqualTo compara utilizando el método 'equals'
		//isSameAs compara utilizando '==', compara LAS REFERENCIAS
		//assertSame(i1, i2);
		assertThat(i1).isSameAs(i2);
		//assertSame(c1, c2);
		assertThat(c1).isSameAs(c2);
	}

	//@Test
	public void test8() {
		System.out.println("Test 8");
		Integer i1 = 128;
		Integer i2 = 128;

		Calculadora c1 = new Calculadora();
		Calculadora c2 = new Calculadora();

		//assertNotSame(i1, i2);
		assertThat(i1).isNotSameAs(i2);
		//assertNotSame(c1, c2);
		assertThat(c1).isNotSameAs(c2);
	}

	//@Test
	public void test9() {
		System.out.println("Test 9");
		String[] palabras1 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		String[] palabras2 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};

		/*
		for(int a=0; a<palabras1.length; a++) {
			if(!palabras1[a].equals(palabras2[a])) {
				throw new RuntimeException("No son iguales!!!!");
			}
		}
		*/

		//assertArrayEquals(palabras2, palabras1);
		assertThat(palabras1).containsExactly(palabras2);
	}

	//@Test
	public void test10() {
		System.out.println("Test 10");
		String[] palabras1 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		String[] palabras2 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};

		List<String> lista1 = Arrays.asList(palabras1);
		List<String> lista2 = Arrays.asList(palabras2);

		//Para comparar Iterables.
		//assertIterableEquals(lista1, lista2);
		assertThat(lista1).containsExactlyElementsOf(lista2);
	}

	@Test
	public void test11() {

		System.out.println("Test 11");

		boolean condicionDificilDeExpresarConUnAserto = false;

		if(!condicionDificilDeExpresarConUnAserto) {
			//throw new RuntimeException("La liamos parda"); //Ñapa
			//Assertions.fail("Test fallido");
			fail("Test fallido");
		}

	}

	//@Test
	@DisplayName("Prueba de assertAll (test12)")
	void test12() {

		System.out.println("Test 12");

		//Dados
		Calculadora calculadora = new Calculadora();
		double s1 = 5d;
		double s2 = 4d;

		//Cuando
		final Double sumResult = calculadora.sumar(s1, s2);


		//Podríamos hacer esto, pero al primer aserto que falle se sale del método por la excepción que se lanza
		System.out.println("I");
		//assertNotNull(sumResult, "El resultado no es nulo");
		assertThat(sumResult).withFailMessage("El resultado no es nulo").isNotNull();
		System.out.println("II");
		//assertTrue(sumResult > 10, "El resultado es mayor que 10"); //A partir de aqui no se ejecuta (se lanza una excepción)
		assertThat(sumResult > 10).withFailMessage("El resultado es mayor que 10").isTrue();
		System.out.println("III");
		//assertTrue(sumResult < 10, "El resultado es menor que 10");
		assertThat(sumResult < 10).withFailMessage("El resultado es menor que 10").isTrue();

		//Esto exige un trabajo inasumible:
		//Comprobador1 c1 = new Comprobador1();
		//c1.sumResult = sumResult;
		//Comprobador2 c2 = new Comprobador2();
		//c2.sumResult = sumResult;
		//Comprobador3 c3 = new Comprobador3();
		//c3.sumResult = sumResult;
		//SoftAssertions.assertSoftly(softly -> { c1.comprobar(softly); c2.comprobar(softly); c3.comprobar(softly); });


		//Con clases internas anónimas
		//assertAll(Executable...) no existe en AssertJ: el equivalente es SoftAssertions,
		//que acumula los fallos de varias comprobaciones y los reporta todos juntos al final.

		SoftAssertions.assertSoftly(softly -> {
			new Comprobacion() {
				public void comprobar(SoftAssertions softly) {
					System.out.println("I");
					//assertNotNull(sumResult);
					softly.assertThat(sumResult).isNotNull();
				}
			}.comprobar(softly);
			new Comprobacion() {
				public void comprobar(SoftAssertions softly) {
					System.out.println("II");
					//assertTrue(sumResult > 10, "No es mayor que 10");
					softly.assertThat(sumResult > 10).withFailMessage("No es mayor que 10").isTrue();
				}
			}.comprobar(softly);
			new Comprobacion() {
				public void comprobar(SoftAssertions softly) {
					System.out.println("III");
					//assertTrue(sumResult < 10, "No es menor que 10");
					softly.assertThat(sumResult < 10).withFailMessage("No es menor que 10").isTrue();
				}
			}.comprobar(softly);
		});

		//Con expresiones lambda
		SoftAssertions.assertSoftly(softly -> {
			//assertTrue(sumResult != null, "La suma es nula!");
			softly.assertThat(sumResult != null).withFailMessage("La suma es nula!").isTrue();
			//assertTrue(sumResult < 10, "La suma es mayor que 10!");
			softly.assertThat(sumResult < 10).withFailMessage("La suma es mayor que 10!").isTrue();
			//assertTrue(sumResult > 10, "La suma es menor que 10!");
			softly.assertThat(sumResult > 10).withFailMessage("La suma es menor que 10!").isTrue();
		});

		/*
		SoftAssertions.assertSoftly(softly -> {
			System.out.println("-I"); softly.assertThat(sumResult == null).withFailMessage("La suma es nula!").isTrue();
			System.out.println("-II"); softly.assertThat(sumResult < 10).withFailMessage("La suma es mayor que 10!").isTrue();
			System.out.println("-III"); softly.assertThat(sumResult > 10).withFailMessage("La suma es menor que 10!").isTrue();
		});
		*/
	}


}


//Executable ya no aplica (es de JUnit); usamos una interfaz mínima equivalente para SoftAssertions
interface Comprobacion {
	void comprobar(SoftAssertions softly);
}

class Comprobador1 implements Comprobacion {
	public Double sumResult;

	@Override
	public void comprobar(SoftAssertions softly) {
		System.out.println("I");
		//Assertions.assertNotNull(sumResult);
		softly.assertThat(sumResult).isNotNull();
	}
}

class Comprobador2 implements Comprobacion {
	public Double sumResult;

	@Override
	public void comprobar(SoftAssertions softly) {
		System.out.println("II");
		//Assertions.assertTrue(sumResult > 10);
		softly.assertThat(sumResult > 10).isTrue();
	}
}

class Comprobador3 implements Comprobacion {
	public Double sumResult;

	@Override
	public void comprobar(SoftAssertions softly) {
		System.out.println("III");
		//Assertions.assertTrue(sumResult < 10);
		softly.assertThat(sumResult < 10).isTrue();
	}
}

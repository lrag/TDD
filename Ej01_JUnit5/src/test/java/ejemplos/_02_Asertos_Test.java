package ejemplos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.curso.modelo.negocio.Calculadora;

public class _02_Asertos_Test {

	@Test
	public void test1() {			

		System.out.println("Test 1");
		
		//dados
		int n1 = 100;
		int n2 = 200;
		
		//cuando
		int n3 = n1 + n2;

		//entonces		
		assertEquals(300, n3);
	
		//Lo mismo, pero sin import est�tico:
		//Assertions.assertEquals(300, n3);
	}
	
	@Test
	public void test2() {	
		System.out.println("Test 2");
		int n1 = 100;
		int n2 = 200;
		assertNotEquals(n1, n2, "No son iguales");	
	}
	
	@Test
	public void test3() {
		System.out.println("Test 3");		
		int n1 = 100;
		int n2 = 200;	
		//Recibe una expresi�n que se resuelve en un boolean
		//Con assertTrue podr�amos hacer cualquier comprobacion
		assertTrue(n2 > n1);		
	}
		
	@Test
	public void test4() {
		System.out.println("Test 4");		
		int n1 = 100;
		int n2 = 200;		
		assertFalse(n2 < n1);		
	}
	
	@Test
	public void test5() throws Exception {
		System.out.println("Test 5");	
		String txt = null;
		
		assertNull(txt);	
	}

	@Test
	public void test6() {
		System.out.println("Test 6");	
		String txt = new String("TXT");
		
		assertNotNull(txt);	
	}
	
	@Test
	public void test7() {
		System.out.println("Test 7");	

		Integer i1 = 127; 
		Integer i2 = 127;
		
		Calculadora c1 = new Calculadora();
		Calculadora c2 = c1;
			
		//AssertEquals compara utilizando el  m�todo 'equals'		
		//AssertSame compara utilizando '==', compara LAS REFERENCIAS
		assertSame(i1, i2);	
		assertSame(c1, c2);	
	}
	
	@Test
	public void test8() {
		System.out.println("Test 8");	
		Integer i1 = 128;
		Integer i2 = 128;
		
		assertNotSame(i1, i2);	
	}
	
	@Test
	public void test9() throws Exception {
		System.out.println("Test 9");
		String[] palabras1 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		String[] palabras2 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};

		/*
		for(int a=0; a<palabras1.length; a++) {
			if(!palabras1[a].equals(palabras2[a])) {
				throw new Exception("No son iguales!!!!");
			}
		}	
		*/
		
		assertArrayEquals(palabras2, palabras1);	
	}

	@Test
	public void test10() {
		System.out.println("Test 10");
		String[] palabras1 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		String[] palabras2 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		
		List<String> lista1 = Arrays.asList(palabras1);
		List<String> lista2 = Arrays.asList(palabras2);
		
		//Para comparar Iterables. Es decir: colecciones
		assertIterableEquals(lista1, lista2);		
	}
	
	@Test
	public void test11() {		

		System.out.println("Test 11");
		
		boolean condicionDificilDeExpresarConUnAserto = true;
		if(!condicionDificilDeExpresarConUnAserto) {
			//throw new RuntimeException("La liamos parda");
			fail("Test incompleto");
		}
	}

	@Test
	@DisplayName("Prueba de assertAll")
	void test12() {
		
		System.out.println("Test 12");		
		
		//Dados
		Calculadora calculadora = new Calculadora();
		double s1 = 5d;
		double s2 = 4d;
			
		//Cuando
		Double sumResult = calculadora.sumar(s1, s2);
		
		//Podr�amos hacer esto, pero al primer aserto que falle se sale del m�todo por la excepci�n que se lanza
		/*
		System.out.println("I");
		assertNotNull(sumResult);
		System.out.println("II");
		assertTrue(sumResult > 10); //A partir de aqui no se ejecuta (se lanza una excepci�n)
		System.out.println("III");
		assertTrue(sumResult < 10);
		*/
		
		//As� lo hac�a un mandril	
		/*
		assertAll(new Executable() {
			public void execute() throws Throwable{
				System.out.println("I");
				assertTrue(sumResult != null);
				System.out.println("II");
				assertTrue(sumResult > 10); //A partir de aqui no se ejecuta
				System.out.println("III");
				assertTrue(sumResult < 10);
			}
		});
		*/		

		/*
		//Con clases internas an�nimas
		assertAll( 
				new Executable() {
					public void execute() throws Throwable {
						assertTrue(sumResult != null);
					}
				},
				new Executable() {
					public void execute() throws Throwable {
						assertTrue(sumResult < 10);
					}
				},
				new Executable() {
					public void execute() throws Throwable {
						assertTrue(sumResult > 10);
					}
				});
		*/
			
		//Con expresiones lambda
		assertAll(() -> assertTrue(sumResult != null),
				  () -> assertTrue(sumResult < 10),
				  () -> assertTrue(sumResult > 10));						

		/*
		assertAll(
			() -> { System.out.println("Uno"); assertTrue(sumResult != null); },
			() -> { System.out.println("Dos"); assertTrue(sumResult < 10); },
			() -> { System.out.println("Tres"); assertTrue(sumResult > 10); }
		);	
		*/	
		
	}		
	
	@Test
	@Disabled 
	//Si queremos deshabilitar todos los test de una clase podemos anotarla con @Disabled
	@DisplayName("Esto solo tiene sentido en linux")
	public void test13() {
		
		System.out.println("Test 13");
		
		//Precondici�n: Si no se cumple no falla el test
		//assumeFalse(System.getProperty("os.name").contains("Linux"));
		
		assumeTrue(System.getProperty("os.name").contains("Linux"));
		
		//Si no se cumple lo que hemos asumido el test deja de ejecutarse al 
		//lanzarse una excepci�n (TestAbortedException)
		//El tes no queda marcado como fallido, sino como 'skipped'
		assertNotNull(null);

		// Id�ntico al anterior
		assumingThat(System.getProperty("os.name").contains("Linux"), () -> assertNotNull(null));
	}	

}














package ejemplos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;


public class _08_Precondiciones {

	@Test
	//@Disabled 
	//Si queremos deshabilitar todos los test de una clase podemos anotarla con @Disabled
	public void estoSoloTieneSentidoEnLinux() {
		
		//Si no se cumple una asuncion esta lanza TestAbortedException y el test no se da por fallido sino como 'skipped'
		
		//Assumptions.assumeFalse(System.getProperty("os.name").contains("Linux"));

		//Precondición
		System.out.println("Antes");
		Assumptions.assumeTrue(System.getProperty("os.name").contains("Linux"));
		System.out.println("Despues");
		assertNotNull(null);

		// Idéntico al anterior
		Assumptions.assumingThat(System.getProperty("os.name").contains("Linux"), () -> assertNotNull(null));
	}

}


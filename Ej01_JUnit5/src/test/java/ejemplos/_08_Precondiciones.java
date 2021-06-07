package ejemplos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;


public class _08_Precondiciones {


	@Test
	public void estoSoloTieneSentidoEnLinux() {
		
		//Las asunciones si no se cumplen lanzan TestAbortedException y el test no se da por fallido
		
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



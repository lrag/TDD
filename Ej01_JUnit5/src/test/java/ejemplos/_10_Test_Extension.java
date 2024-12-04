package ejemplos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(_10_CustomExtension.class)
public class _10_Test_Extension {

	@Test
	public void test1() { 
		System.out.println("Ejecutando un test que no hace nada");
	}
	
	/*
	@Test
	public void test2() { 
		System.out.println("ZASCA");
		Assertions.fail("MUERTE Y DESTRUCCIÓN");
	}
	*/
	
}


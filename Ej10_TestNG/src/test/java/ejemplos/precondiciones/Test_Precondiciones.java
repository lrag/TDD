package ejemplos.precondiciones;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(value = OyenteMetodosPrecondiciones.class )
public class Test_Precondiciones {

	@Test
	@WindowsOnly
	public void estoSoloTieneSentidoEnWindows() {
		//
		Assert.assertTrue(System.getProperty("os.name").contains("Windows"));
	}

	@Test
	@LinuxOnly
	public void estoSoloTieneSentidoEnLinux() {
		//
		Assert.assertTrue(System.getProperty("os.name").contains("Linux"));
	}

}



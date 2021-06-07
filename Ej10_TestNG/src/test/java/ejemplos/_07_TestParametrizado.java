package ejemplos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.Calculadora;
import com.curso.modelo.negocio.CalculadoraImpuestos;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.InvalidIngresoException;

public class _07_TestParametrizado {

	private CalculadoraImpuestos calculadoraImpuestos;
	private Calculadora calculadora;
	private GestorClientes gestorClientes;
	
	@BeforeMethod
	public void inicializar() {
		calculadoraImpuestos = new CalculadoraImpuestos();
		calculadora = new Calculadora();
		gestorClientes = new GestorClientes();
	}

	public _07_TestParametrizado() {
		super();
		System.out.println("Instanciando TestParametrizado");
	}
	
	//Los data providers hn de ser métodos estáticos
	@DataProvider(name = "ejemplo")
	public static Object[] primeNumbers() {
		return new Object[] { 5d, 25d, 50d, 75d };
	}	
	
	@Test(dataProvider = "ejemplo")
	public void ejemplo(Double numero) {
		//Dado este número
		System.out.println("Parámetro:"+numero);
		//Cuando 
		double resultado = calculadora.cuadrado(2d);
		//Entonces
		Assert.assertTrue(resultado == 2d*2d);	
		//Lo mismo, con equals
		Assert.assertEquals(resultado, 2d*2d);	
	}
	
	@DataProvider(name = "clientes")
	public static Iterator<Cliente> datosParaProbarInsertarCliente() {
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente(null,"N1","D1","T1"));
		clientes.add(new Cliente(null,"N2","D2","T2"));
		clientes.add(new Cliente(null,"N3","D3","T3"));
		clientes.add(new Cliente(null,"N4","D4","T4"));
		clientes.add(new Cliente(null,"N5","D5","T5"));
		return clientes.iterator();
	}		
	
	@Test(dataProvider = "clientes")
	void pruebaInsertarClientes(Cliente cliente) {
		//Cuando
		Cliente cAux = gestorClientes.insertarCliente(cliente);
		//Entonces
		Assert.assertNotNull(cAux.getId());		
	}
	
	@DataProvider(name = "datosParaProbarCalculadoraImpuestos")
	static Object[][] datosParaProbarCalculadoraImpuestos() {
		return new Object[][] {
	        { 5000d,0d },
	        { 10000d,800d },
	        { 17000d,1700d },
	        { 22000d,3300d },
	        { 35000d,6825d}
		};	 
	}	

	@Test(dataProvider = "datosParaProbarCalculadoraImpuestos")
	void pruebaCalculadoraImpuestos(Double ingreso, Double impuesto) throws InvalidIngresoException {
		//Dado
		System.out.println(ingreso+","+impuesto);
		//Cuando
		double resultado = calculadoraImpuestos.calcularImpuestosSobreIngreso(ingreso);
	    //Entonces
		Assert.assertEquals((Double) impuesto, (Double) resultado);
	}
		
}

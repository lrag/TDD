package com.curso.modelo.negocio;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Comercial;
import com.curso.modelo.entidad.Sucursal;
import com.curso.modelo.negocio.excepcion.DireccionException;
import com.curso.modelo.negocio.excepcion.SucursalException;
import com.curso.modelo.persistencia.ClienteDao;
import com.curso.modelo.persistencia.ClienteDaoImpl;

//Podemos crear los mocks invocando Mockito.mock()
//o usando anotaciones. 
@ExtendWith(MockitoExtension.class)
public class GestorClientesTest {
	
	
	
	//ESTO NO ES UNA PRUEBA UNITARIA
	//@Test
	public void pruebaAltaCliente() throws Exception {
		
		//Dados
		Cliente c = new Cliente(null,"Bender Bending Rodriguez","NNY","555666");
		GestorClientes gc = new GestorClientes();
		//Si usamos objetos de verdad para resolver las dependencias de GestorClientes
		//estamos a�adiendo a la prueba el c�digo de otros m�todos y ya no es unitaria
		GestorDirecciones gd = new GestorDirecciones();
		gc.setGestorDirecciones(gd);
		GestorComerciales gcom = new GestorComerciales();
		gc.setGestorComerciales(gcom);
		GestorSucursales gs = new GestorSucursales();
		gc.setGestorSucursales(gs);
		ClienteDao cDao = new ClienteDaoImpl();
		gc.setClienteDao(cDao);
		
		//Cuando
		Cliente cAux = gc.altaCliente(c);
		
		//Entonces
		Assertions.assertAll(
			() -> Assertions.assertNotNull(cAux.getId()),
			() -> Assertions.assertNotNull(cAux.getSucursal()),
			() -> Assertions.assertTrue(cAux.getComerciales().size()>0)
		);
		
	}
	
	//Test doubles:
	//
	//dummies: un objeto que para los metodos void no hace nada
	//         y para los que devuelven algo devuelve:
	//      	  -cero si es n�mero
	//        	  -false si es boolean
	//        	  -null si es referencia
	//stubs: un objeto que cuenta con una serie de respuestas enlatadas para determinados m�todos
	//fakes: un objeto programado por nosostros y que reproduce el comportamiento del objeto real
	//       un fake se programa de verdad!
	//mocks: un objeto que recuerda las llamadas que ha recibido, el orden de las mismas y el n�mero de veces	
	
	
	@Test
	@DisplayName("GestorClientes.altaCliente: Un cliente con datos correctos se insertar� correctamente")
	public void altaClienteDatosCorrectos() throws Exception {
		
		//DADOS:
		Cliente cliente = new Cliente(null,"Nombre","Direccion","Telefono");
		
		GestorClientes gestorClientes = new GestorClientes(); //<-- ESTE ES DE VERDAD
		
		//Dummie:
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class);
		System.out.println(gestorDirecciones);
		
		//Stub:
		GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class);
		Mockito
			.when(gestorSucursales.encontrarSucursalCercana(any(String.class)))
			.thenReturn(new Sucursal(1,"Sucursal 1","C/Tocot�"));
		
		//Stub:
		GestorComerciales gestorComerciales = mock(GestorComerciales.class);
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial(1,"EMP-1","Comercial1"));
		comerciales.add(new Comercial(2,"EMP-2","Comercial2"));	
		Mockito
			.when(gestorComerciales.encontrarComerciales())
			.thenReturn(comerciales);
		
		//Stub:
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class);

		//No nos sirve porque tiene que devolver exactamente el mismo cliente
		//que recibi�:
		//Mockito
		//	.when(clienteDao.insertar(any(Cliente.class)))
		//	.thenReturn( new Cliente(1,"N","D","T") );		
		
		
		//Tampoco nos sirve: aunque devuelva el mismo objeto que recibi� no le coloca
		//un valor en el id
		//Mockito
		//	.when(clienteDao.insertar(any(Cliente.class)))
		//	.thenReturn( (Cliente) AdditionalAnswers.returnsFirstArg() );	
		
		//thenReturn, doReturn: cuando sabemos qu� vamos a devolver
		//thenAnswer, doAnswer: cuando necesitamos cierto c�digo para crear el valor a devolver
		//thenThrow,  doThrow: cuando queremos que se lance una excepci�n
		
		//Para esta situacion debemos proporcionar nuestro c�digo		
		//Mockito
		//	.when(clienteDao.insertar(any(Cliente.class)))
		//	.thenAnswer( new Answer<Cliente>() {
		//		public Cliente answer(InvocationOnMock invocation) throws Throwable {
		//			Cliente c = (Cliente) invocation.getArgument(0);
		//			c.setId(1);
		//			return c;
		//		}
		//	});
		
		Mockito
			.when(clienteDao.insertar(any(Cliente.class)))
			.thenAnswer(   
				  invocation -> {
					  Cliente c = (Cliente) invocation.getArgument(0);
					  c.setId(1);
					  return c;
				  }
			);
		
		//Le proporcionamos los test doubles a gestorClientes
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		gestorClientes.setGestorSucursales(gestorSucursales);		
		gestorClientes.setGestorComerciales(gestorComerciales);
		gestorClientes.setClienteDao(clienteDao);
		
		//CUANDO:
		Cliente clienteInsertado = gestorClientes.altaCliente(cliente);		
		
		//ENTONCES:
		System.out.println(clienteInsertado);
		Assertions.assertAll( () -> Assertions.assertEquals(2, clienteInsertado.getComerciales().size()),
				              () -> assertNotNull(clienteInsertado.getSucursal()),
				              () -> assertNotNull(clienteInsertado.getId()));	
		
	}
	
	//@Test
	@DisplayName("GestorClientes.altaCliente: con un cliente cuya direccion nula se lanzar� una DireccionException")
	public void altaClienteDireccionNula() {
		
		//Dados:
		Cliente cliente = new Cliente(null, "John McClane", null, "123456");
		
		//Este es el objeto real que queremos probar
		GestorClientes gestorClientes = new GestorClientes(); 
		
		//Test doubles:
		
		//Stub:
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class);
		
		try {
			Mockito
				.doThrow(new DireccionException("Direcci�n nula"))
				.when(gestorDirecciones)
				.comprobarDireccion(null);
		} catch (DireccionException e) {
			e.printStackTrace();
		}
		
		//Para esta prueba no son necesarios estos test doubles:
		//GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class);
		//GestorComerciales gestorComerciales = mock(GestorComerciales.class);
		//ClienteDao clienteDao = Mockito.mock(ClienteDao.class);
		
		//Le proporcionamos los test doubles a gestorClientes
		gestorClientes.setGestorDirecciones(gestorDirecciones);

		//Entonces:		
		Exception e = Assertions.assertThrows(DireccionException.class, 
				                              () -> gestorClientes.altaCliente(cliente) );
		Assertions.assertEquals("Direcci�n nula", e.getMessage());
		
	}
	
	//@Test
	@DisplayName("GestorClientes.altaCliente: un cliente con la direccion falsa lanzar� una DireccionException")
	public void altaClienteTestDireccionFalsa() {
		
		Cliente cliente = new Cliente(null,"Paul","C/Falsa, 123","Telefono");

		//Dados
		//Cliente cliente = new Cliente(null,"Paul","C/Falsa, 123","Telefono");
		
		//Este es el objeto real que queremos probar
		GestorClientes gestorClientes = new GestorClientes();
		
		//Stub
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class);
		try {
			Mockito
				.lenient()
				.doThrow(new DireccionException("Esta direccion es falsa"))
				.when(gestorDirecciones)
				.comprobarDireccion("C/Falsa, 123");
		} catch (DireccionException e) {
			e.printStackTrace();
		}
		
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		
		//Entonces:		
		DireccionException ex = Assertions.assertThrows(DireccionException.class, 
				                                        () -> gestorClientes.altaCliente(cliente) );	
		Assertions.assertEquals("Esta direccion es falsa", ex.getMessage());				

		
		
	}	
	
	//@Test
	@DisplayName("Comprobamos que gestorClientes.altaCliente realiza las llamadas correctas a sus dependecias")
	public void pruebaMOCKS() {
		
		
		//Dados
		/*
		Cliente c1 = new Cliente(null,"Antunez","d1","t1");
		Cliente c2 = new Cliente(null,"Harpo","d2","t2");
		
		GestorClientes gc = new GestorClientes();
		
		ClienteDao cDao = Mockito.mock(ClienteDao.class);
		gc.setClienteDao(cDao);
		
		Mockito.lenient().when(cDao.insertar(c1)).thenAnswer(invocation -> {
						System.out.println("llamada a clienteDao.insertar");
						Cliente cli = invocation.getArgument(0, Cliente.class);
						cli.setId(2);
						return cli;
					});
		
		Mockito.lenient().when(cDao.insertar(c2)).thenAnswer(invocation -> {
						System.out.println("llamada a clienteDao.insertar");
						Cliente cli = invocation.getArgument(0, Cliente.class);
						cli.setId(22);
						return cli;
					});

		try {
			gc.altaCliente(c1);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		*/
						
		//Dados:
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente(null,"N1","D1","T1"));
		clientes.add(new Cliente(null,"N2","D2","T2"));
		clientes.add(new Cliente(null,"N3",null,"T3"));
		clientes.add(new Cliente(null,"N4","D4","T4"));
		clientes.add(new Cliente(null,"N5","C/Falsa, 123","T5"));
				
		GestorClientes gestorClientes = new GestorClientes();
		
		//TestDoubles;
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class);
		
		try {
			Mockito
				.lenient()
				.doThrow(new DireccionException("Direcci�n nula"))
				.when(gestorDirecciones)
				.comprobarDireccion(null);
			Mockito
				.lenient()
				.doThrow(new DireccionException("La direcci�n es falsa"))
				.when(gestorDirecciones)
				.comprobarDireccion("C/Falsa, 123");
		} catch (DireccionException e1) {
			e1.printStackTrace();
		}
		
		GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class);	
		try {
			Mockito
				.when(gestorSucursales.encontrarSucursalCercana(any(String.class)))
				.thenReturn(new Sucursal(1,"Sucursal 1","C/Tocot�"));
		} catch (SucursalException e) {
			e.printStackTrace();
		}
		
		GestorComerciales gestorComerciales = mock(GestorComerciales.class);
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial(1,"EMP-1","Comercial1"));
		comerciales.add(new Comercial(2,"EMP-2","Comercial2"));	
		Mockito
			.when(gestorComerciales.encontrarComerciales())
			.thenReturn(comerciales);
		
		ClienteDao clienteDao = mock(ClienteDao.class);
		Mockito
			.when(clienteDao.insertar(any(Cliente.class)))
			.thenAnswer( 
			  invocation -> {
				System.out.println("llamada a clienteDao.insertar");
				Cliente cli = invocation.getArgument(0, Cliente.class);
				cli.setId(42);
				return cli;
			});
		
		gestorClientes.setClienteDao(clienteDao);
		gestorClientes.setGestorComerciales(gestorComerciales);
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		gestorClientes.setGestorSucursales(gestorSucursales);
		
		//Cuando:
		gestorClientes.altaClientes(clientes);

		
		//Entonces:
		
		//Los mocks de Mockito guardan como estado las llamadas que han 
		//recibido, el n�mero y el orden de las mismas
		//
		//Si usamos esta caracter�stica de mockito estamos dando por sentado que el test conoce 
		//el funcionamiento interno del metodo 'altaCliente'. La prueba deja de ser de tipo 'caja negra'
		//y pasa a ser algo m�s parecido a un test de integraci�n que a uno unitario.
		//
		//Conclusi�n: mucho cuidado con esto
		//		
		
		try {
			//Verificamos que gestorClientes ha llamado a los m�todos adecuados
			//Esto es posible porque los mocks recuerdan las llamadas recibidas
			//Mockito.verify(clienteDao).insertar(any(Cliente.class)); //Verifica que se ha llamado UNA vez
			//Mockito.verify(gestorComerciales).encontrarComerciales();
			//Mockito.verify(gestorSucursales).encontrarSucursalCercana(any(String.class));
			//Mockito.verify(gestorSucursales).encontrarSucursalCercana("D4");
		
			//Tambien recuerda cuantas veces y podemos comprobar si se han realizado las esperadas
			
			Mockito.verify(clienteDao, Mockito.times(3)).insertar(any(Cliente.class));
			Mockito.verify(gestorDirecciones, Mockito.times(5)).comprobarDireccion(Mockito.any());			
			Mockito.verify(gestorComerciales, Mockito.times(3)).encontrarComerciales();
			Mockito.verify(gestorSucursales, Mockito.times(1)).encontrarSucursalCercana("D4");
			
			//Podemos verificar el orden de las llamadas
			/*
			 *No funciona si en la lista de clientes tenemos m�s de uno
		    InOrder ordered = Mockito.inOrder(gestorDirecciones, gestorSucursales, gestorComerciales, clienteDao);	
		    ordered.verify(gestorDirecciones).comprobarDireccion(any(String.class));
		    ordered.verify(gestorSucursales).encontrarSucursalCercana(any(String.class));
		    ordered.verify(gestorComerciales).encontrarComerciales();
		    ordered.verify(clienteDao).insertar(any(Cliente.class));
		    */
					
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
	}
	
}









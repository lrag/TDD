package com.curso.modelo.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Comercial;
import com.curso.modelo.entidad.Sucursal;
import com.curso.modelo.negocio.excepcion.DireccionException;
import com.curso.modelo.persistencia.ClienteDao;
import com.curso.modelo.persistencia.ClienteDaoImpl;


public class GestorClientesTest {
	
	//ESTO NO ES UNA PRUEBA UNITARIA PORQUE UTILIZA LAS DEPENDENCIAS REALES DE LA CLASE GESTOR_CLIENTES
	//Es un test de integraci�n
	//@Test
	public void pruebaAltaCliente() throws Exception {
		
		//Dados
		Cliente c = new Cliente(null,"Bender Bending Rodriguez","NNY","555666");
		GestorClientes gc = new GestorClientes();		
		
		GestorDirecciones gd = new GestorDirecciones();
		gc.setGestorDirecciones(gd);
		GestorComerciales gcom = new GestorComerciales();
		gc.setGestorComerciales(gcom);
		GestorSucursales gs = new GestorSucursales();
		gc.setGestorSucursales(gs);
		//Aqui todav�a nos faltar�a todo lo relacionado con las conexiones a la bb.dd!
		ClienteDao cDao = new ClienteDaoImpl();
		gc.setClienteDao(cDao);			
				
		//Cuando
		Cliente cAux = gc.altaCliente(c);
		
		//Entonces
		assertAll(
				() -> assertNotNull(cAux.getId(),"El cliente no tiene id"),
				() -> assertNotNull(cAux.getSucursal(),"El cliente no tiene sucursal"),
				() -> assertTrue(cAux.getComerciales().size() == 2,"El cliente no tiene comerciales")
			);
	}

	//Test doubles:
	//
	//dummies: un objeto que en los metodos void no hace nada
	//         y para los que devuelven algo devuelve:
	//      	  -cero si es n�mero
	//        	  -false si es boolean
	//        	  -null si es referencia
	//         En los m�todos que lanzen excepci�n nunca la lanzar�
	//stubs: un objeto que cuenta con una serie de respuestas enlatadas para determinados m�todos
	//fakes: un objeto programado por nosostros y que reproduce el comportamiento del objeto real
	//       un fake se programa de verdad!
	//mocks: un objeto que recuerda las llamadas que ha recibido, el orden de las mismas y el n�mero de veces	
	//       es un test double contra el que se har�n asertos
	
	//@Test
	@DisplayName("GestorClientes.altaCliente: Un cliente con datos correctos se insertar� correctamente")
	public void altaClienteDatosCorrectos() throws Exception {
	
		//Dados
		Cliente cliente = new Cliente(null,"Bender Bending Rodriguez","NNY","555666"); //Premisa: estos datos SON CORRECTOS
		GestorClientes gestorClientes = new GestorClientes(); //Este es el objeto real que vamos a probar		
		
		//Y estos test doubles
		
		//Dummie:
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class); 
		
		//Stub:
		GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class); //Inicialmente es un dummie
		Mockito
			.when(gestorSucursales.encontrarSucursalCercana(Mockito.any(String.class)))
			.thenReturn(new Sucursal(1,"Sucursal 1","C/Tocot�"));
		
		//Stub:
		GestorComerciales gestorComerciales = Mockito.mock(GestorComerciales.class);
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial(1,"EMP-1","Comercial1"));
		comerciales.add(new Comercial(2,"EMP-2","Comercial2"));	
		Mockito
			//.lenient()
			.when(gestorComerciales.encontrarComerciales())
			.thenReturn(comerciales);	
		
		//Stub:		
		//ClienteDao clienteDao = Mockito.mock(ClienteDaoImpl.class); 
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class); //Mockito tambien hace test doubles a partir de interfaces

		//No nos basta con unn dummy porque devolver�a null en la llamada a insertar
		
		//No nos sirve porque tiene que devolver exactamente el mismo cliente que recibi�:
		//Mockito
		//	.when(clienteDao.insertar(Mockito.any(Cliente.class)))
		//	.thenReturn( new Cliente(1,"N","D","T") );		
		
		//Tampoco nos sirve: aunque devuelva el mismo objeto que recibi� no le coloca
		//un valor en el id :(
		//Mockito
		//	.when(clienteDao.insertar(Mockito.any(Cliente.class)))
		//	.thenReturn( (Cliente) AdditionalAnswers.returnsFirstArg() );
		
		//Los 'when' solo se pueden utilizar en m�todos que no son void
		//Los 'do' se pueden utilizar en cualquier m�todo
		//thenReturn, doReturn: cuando sabemos qu� vamos a devolver
		//thenAnswer, doAnswer: cuando necesitamos cierto c�digo para crear el valor a devolver
		//thenThrow,  doThrow: cuando queremos que se lance una excepci�n		
		
		//Derochando recursos...
		//Mockito
		//.when(clienteDao.insertar(Mockito.any(Cliente.class)))
		//.thenAnswer(new ClienteDaoInsertarAnswer());		
		
		/*
		//Con clase interna an�nima
		Mockito
			.when(clienteDao.insertar(Mockito.any(Cliente.class)))
			.thenAnswer(new Answer<Cliente>() {
				@Override
				public Cliente answer(InvocationOnMock invocation) throws Throwable {
					Cliente c = (Cliente) invocation.getArgument(0);
					c.setId(1);
					return c;
				}
			});
		*/		

		//Con expresion lambda:
		Mockito
			.when(clienteDao.insertar(Mockito.any(Cliente.class)))
			.thenAnswer(invocation -> {
					Cliente cAux = (Cliente) invocation.getArgument(0);
					cAux.setId(1);
					return cAux;
				});
		
		//Le proporcionamos los test doubles a gestorClientes
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		gestorClientes.setGestorSucursales(gestorSucursales);		
		gestorClientes.setGestorComerciales(gestorComerciales);
		gestorClientes.setClienteDao(clienteDao);
		
		//CUANDO:
		Cliente clienteInsertado = gestorClientes.altaCliente(cliente);		
		
		//ENTONCES:
		System.out.println(clienteInsertado);
		assertAll( () -> assertEquals(2, clienteInsertado.getComerciales().size(),"El cliente no tiene comerciales!"),
				   () -> assertNotNull(clienteInsertado.getSucursal(),"El cliente no tiene sucursal!"),
				   () -> assertNotEquals(clienteInsertado.getSucursal().getNombre() ,"Sucursal virtual", "Al cliente no se le debe asignar la sucursal virtual"),
				   () -> assertNotNull(clienteInsertado.getId(),"El cliente no tiene id!"));	
	}	

	//@Test
	@DisplayName("GestorClientes.altaCliente: al cliente cuya direccion est� alejada de cualquier sucursal se le asignar� la sucursal virtual")
	public void altaClienteConDireccionAlejadaDeCualquierSucursal() throws Exception {
		
		//Dados
		Cliente cliente = new Cliente(null,"Bender Bending Rodriguez","El quinto pino","555666"); //Premisa: esta direcci�n no est� cerca de ninguna sucursal
		GestorClientes gestorClientes = new GestorClientes(); //Este es el objeto real que vamos a probar		
		
		//Y estos test doubles
		
		//Dummie:
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class); 
		
		//Dummie:
		GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class); //Inicialmente es un dummie

		//Stub:
		GestorComerciales gestorComerciales = Mockito.mock(GestorComerciales.class);
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial(1,"EMP-1","Comercial1"));
		comerciales.add(new Comercial(2,"EMP-2","Comercial2"));	
		Mockito
			//.lenient()
			.when(gestorComerciales.encontrarComerciales())
			.thenReturn(comerciales);		
		
		//Stub:		
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class); //Mockito tambien hace test doubles a partir de interfaces
		Mockito
			.when(clienteDao.insertar(Mockito.any(Cliente.class)))
			.thenAnswer(invocation -> {
					Cliente cAux = (Cliente) invocation.getArgument(0);
					cAux.setId(1);
					return cAux;
				});
		
		//Le proporcionamos los test doubles a gestorClientes
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		gestorClientes.setGestorSucursales(gestorSucursales);		
		gestorClientes.setGestorComerciales(gestorComerciales);
		gestorClientes.setClienteDao(clienteDao);		
		
		//CUANDO:
		Cliente clienteInsertado = gestorClientes.altaCliente(cliente);		
		
		//ENTONCES:
		System.out.println(clienteInsertado);
		assertAll( 
			() -> assertEquals(2, clienteInsertado.getComerciales().size(),"El cliente no tiene comerciales!"),
			() -> assertNotNull(clienteInsertado.getSucursal(),"El cliente no tiene sucursal!"),
			() -> assertEquals(clienteInsertado.getSucursal().getNombre(),"Sucursal virtual", "Al cliente no se le ha asignado la sucursal virtual"),
			() -> assertNotNull(clienteInsertado.getId(),"El cliente no tiene id!")
		);				
	}
	
	//@Test
	@DisplayName("GestorClientes.altaCliente: un cliente con la direccion falsa lanzar� una DireccionException")
	public void altaClienteTestDireccionFalsa() throws Exception {
		
		//Dados
		Cliente cliente = new Cliente(null,"Bender Bending Rodriguez","C/Falsa, 123","555666"); //Premisa: esta direccion es falsa
		GestorClientes gestorClientes = new GestorClientes(); //Este es el objeto real que vamos a probar		
		
		//Y estos test doubles
		
		//Los 'when' solo se pueden utilizar en m�todos que no son void
		//Los 'do' se pueden utilizar en cualquier m�todo
		//thenReturn, doReturn: cuando sabemos qu� vamos a devolver
		//thenAnswer, doAnswer: cuando necesitamos cierto c�digo para crear el valor a devolver
		//thenThrow,  doThrow: cuando queremos que se lance una excepci�n		

		//Stub:
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class); 
		Mockito
			.doThrow(new DireccionException("Esta direccion es falsa"))
			.when(gestorDirecciones)
			.comprobarDireccion(Mockito.any(String.class));
			
		//Dummie:
		GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class); 

		//Dummie:
		GestorComerciales gestorComerciales = Mockito.mock(GestorComerciales.class);
		
		//Dummie:
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class); 
		
		//Le proporcionamos los test doubles a gestorClientes
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		gestorClientes.setGestorSucursales(gestorSucursales);		
		gestorClientes.setGestorComerciales(gestorComerciales);
		gestorClientes.setClienteDao(clienteDao);		
		
		//CUANDO - ENTONCES:
		Exception e = assertThrows(DireccionException.class, () -> gestorClientes.altaCliente(cliente));
		assertEquals(e.getMessage(), "Esta direccion es falsa");	
	}
	
	
	@Test
	@DisplayName("Comprobamos que gestorClientes.altaCliente realiza las llamadas correctas a sus dependecias")
	public void pruebaMOCKS() throws Exception {
						
		//Dados:
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente(null,"N1","D1","T1")); //Este es correcto
		//clientes.add(new Cliente(null,"N2",null,"T2")); //Este no
		//clientes.add(new Cliente(null,"N3","D3","T3")); //Este si
		//clientes.add(new Cliente(null,"N4","C/Falsa, 123","T4")); //Y este no
		//clientes.add(new Cliente(null,"N5","D5","T5")); //Y este si
						
		//Este es el objeto real que vamos a probar
		GestorClientes gestorClientes = new GestorClientes();
		
		//TestDoubles;
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class);		
		Mockito
			//.lenient()
			.doThrow(new DireccionException("Direcci�n nula"))
			.when(gestorDirecciones)
			.comprobarDireccion(null);
		Mockito
			//.lenient()
			.doThrow(new DireccionException("La direcci�n es falsa"))
			.when(gestorDirecciones)
			.comprobarDireccion("C/Falsa, 123");
		
		GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class);	
		Mockito
			.when(gestorSucursales.encontrarSucursalCercana(Mockito.any(String.class)))
			.thenReturn(new Sucursal(1,"Sucursal 1","C/Tocot�"));
		
		GestorComerciales gestorComerciales = mock(GestorComerciales.class);
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial(1,"EMP-1","Comercial1"));
		comerciales.add(new Comercial(2,"EMP-2","Comercial2"));	
		Mockito
			.when(gestorComerciales.encontrarComerciales())
			.thenReturn(comerciales);
		
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class);
		Mockito
			.when(clienteDao.insertar(Mockito.any(Cliente.class)))
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
		
		//Los test doubles de Mockito guardan como estado las llamadas que han 
		//recibido, el n�mero y el orden de las mismas
		//
		//Si usamos esta caracter�stica de mockito en un test este deje de ser un test unitario como tal
		//y se convierte en una especia de test de integraci�n
		//
		//
		//En un test de este tipo no tienen cabida asertos como los de los test unitarios
		//			
		
		//Verificamos que gestorClientes ha llamado a los m�todos adecuados de sus dependecias
		//Esto es posible porque los mocks recuerdan las llamadas recibidas
				
		/*
		Mockito.verify(clienteDao, Mockito.times(3)).insertar(Mockito.any(Cliente.class));
		Mockito.verify(gestorDirecciones, Mockito.times(5)).comprobarDireccion(Mockito.any());			
		Mockito.verify(gestorComerciales, Mockito.times(3)).encontrarComerciales();
		Mockito.verify(gestorSucursales, Mockito.times(1)).encontrarSucursalCercana("D5"); 
		//Cuando queremos verificar que se ha llamado una �nica vez podemos escribirlo as�
		Mockito.verify(gestorSucursales).encontrarSucursalCercana("D5"); //Verifica que se ha llamado UNA vez
		*/
		
		
		//Podemos verificar el orden de las llamadas
		//Este ejemplo no funciona si en la lista de clientes tenemos m�s de uno
		InOrder ordered = Mockito.inOrder(gestorDirecciones, gestorSucursales, clienteDao, gestorComerciales); 
		
		ordered.verify(gestorDirecciones).comprobarDireccion(Mockito.any(String.class));
	    ordered.verify(gestorSucursales).encontrarSucursalCercana(Mockito.any(String.class));
	    ordered.verify(gestorComerciales).encontrarComerciales();
	    ordered.verify(clienteDao).insertar(Mockito.any(Cliente.class));
		
		
	}	
	
}


class ClienteDaoInsertarAnswer implements Answer {

	@Override
	public Object answer(InvocationOnMock invocation) throws Throwable {
		System.out.println("llamada a clienteDao.insertar");
		Cliente cli = invocation.getArgument(0, Cliente.class);
		cli.setId(42);
		return cli;
	}	
	
}






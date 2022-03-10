package com.curso.modelo.negocio;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.Comercial;
import com.curso.modelo.entidad.Sucursal;
import com.curso.modelo.negocio.excepcion.DireccionException;
import com.curso.modelo.negocio.excepcion.SucursalException;
import com.curso.modelo.persistencia.ClienteDao;


public class GestorClientesTest_Final {
	
	@Test
	@DisplayName("GestorClientes.altaCliente: Un cliente con datos correctos se insertará correctamente")
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
			.thenReturn(new Sucursal(1,"Sucursal 1","C/Tocotó"));

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
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class); 
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
				   () -> assertNotNull(clienteInsertado.getId(),"El cliente no tiene id!"));	
	}	
	
	@Test
	@DisplayName("GestorClientes.altaCliente: a cliente cuya direccion esté alejada de cualquier sucursal se le asignará la sucursal virtual")
	public void altaClienteConDireccionAlejadaDeCualquierSucursal() throws Exception {
		
		//Dados
		Cliente cliente = new Cliente(null,"Bender Bending Rodriguez","El quinto pino","555666"); //Premisa: esta dirección no está cerca de ninguna sucursal
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
	
	@Test
	@DisplayName("GestorClientes.altaCliente: un cliente con la direccion falsa lanzará una DireccionException")
	public void altaClienteTestDireccionFalsa() throws Exception {
		
		//Dados
		Cliente cliente = new Cliente(null,"Bender Bending Rodriguez","C/Falsa, 123","555666"); //Premisa: esta direccion es falsa
		GestorClientes gestorClientes = new GestorClientes(); //Este es el objeto real que vamos a probar		

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
		//clientes.add(new Cliente(null,"N4","D4","T4")); //Este tambien
		//clientes.add(new Cliente(null,"N5","C/Falsa, 123","T5")); //Y este no
						
		//Este es el objeto real que vamos a probar
		GestorClientes gestorClientes = new GestorClientes();
		
		//TestDoubles;
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class);		
		Mockito
			//.lenient()
			.doThrow(new DireccionException("Dirección nula"))
			.when(gestorDirecciones)
			.comprobarDireccion(null);
		Mockito
			//.lenient()
			.doThrow(new DireccionException("La dirección es falsa"))
			.when(gestorDirecciones)
			.comprobarDireccion("C/Falsa, 123");
		
		GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class);	
		try {
			Mockito
				.when(gestorSucursales.encontrarSucursalCercana(Mockito.any(String.class)))
				.thenReturn(new Sucursal(1,"Sucursal 1","C/Tocotó"));
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
		//recibido, el número y el orden de las mismas
		//
		//Si usamos esta característica de mockito estamos dando por sentado que el test conoce 
		//el funcionamiento interno del metodo 'altaCliente'. La prueba deja de ser de tipo 'caja negra'
		//y pasa a ser algo más parecido a un test de integración que a uno unitario (en realidad ya no es un test unitario)
		//
		//Conclusión: mucho cuidado con esto
		//			

		/*
		//Verificamos que gestorClientes ha llamado a los métodos adecuados de sus dependecias
		//Esto es posible porque los mocks recuerdan las llamadas recibidas
		Mockito.verify(clienteDao, Mockito.times(3)).insertar(Mockito.any(Cliente.class));
		Mockito.verify(gestorDirecciones, Mockito.times(5)).comprobarDireccion(Mockito.any());			
		Mockito.verify(gestorComerciales, Mockito.times(3)).encontrarComerciales();
		//Cuando queremos verificar que se ha llamado una única vez podemos escribirlo así
		Mockito.verify(gestorSucursales, Mockito.times(1)).encontrarSucursalCercana("D4"); 
		Mockito.verify(gestorSucursales).encontrarSucursalCercana("D4"); //Verifica que se ha llamado UNA vez
		*/
		
		//Podemos verificar el orden de las llamadas
		//No funciona si en la lista de clientes tenemos más de uno
	    
		InOrder ordered = Mockito.inOrder(gestorDirecciones, gestorSucursales, clienteDao, gestorComerciales);	
	    ordered.verify(gestorDirecciones).comprobarDireccion(Mockito.any(String.class));
	    ordered.verify(gestorSucursales).encontrarSucursalCercana(Mockito.any(String.class));
	    ordered.verify(gestorComerciales).encontrarComerciales();
	    ordered.verify(clienteDao).insertar(Mockito.any(Cliente.class));
		
	}
	
	
}










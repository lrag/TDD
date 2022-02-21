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
import org.mockito.InOrder;
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
public class GestorClientesTest_Final {
	
	//Test doubles:
	//
	//dummies: un objeto que para los metodos void no hace nada
	//         y para los que devuelven algo devuelve:
	//      	  -cero si es número
	//        	  -false si es boolean
	//        	  -null si es referencia
	//stubs: un objeto que cuenta con una serie de respuestas enlatadas para determinados métodos
	//fakes: un objeto programado por nosostros y que reproduce el comportamiento del objeto real
	//       un fake se programa de verdad!
	//mocks: un objeto que recuerda las llamadas que ha recibido, el orden de las mismas y el número de veces	
		
	@Test
	@DisplayName("GestorClientes.altaCliente: Un cliente con datos correctos se insertará correctamente")
	public void altaClienteDatosCorrectos() throws Exception {
		
		//DADOS:
		//Este cliente
		Cliente cliente = new Cliente(null,"Nombre","Direccion","Telefono");
		
		//Este gestorClientes
		GestorClientes gestorClientes = new GestorClientes(); //<-- ESTE ES DE VERDAD
		
		//Y estos test doubles
		
		//Dummie:
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class); 
		System.out.println(gestorDirecciones);
		
		//Stub:
		GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class); //Nos entrega un dummie
		Mockito
			.when(gestorSucursales.encontrarSucursalCercana(any(String.class)))
			.thenReturn(new Sucursal(1,"Sucursal 1","C/Tocotó"));
		
		//Stub:
		GestorComerciales gestorComerciales = mock(GestorComerciales.class); //Comenzamos con un dummie
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial(1,"EMP-1","Comercial1"));
		comerciales.add(new Comercial(2,"EMP-2","Comercial2"));	
		Mockito
			.lenient()
			.when(gestorComerciales.encontrarComerciales())
			.thenReturn(comerciales);
		
		//Stub:
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class);
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
		Assertions.assertAll( () -> Assertions.assertEquals(2, clienteInsertado.getComerciales().size(),"El cliente no tiene comerciales!"),
				              () -> assertNotNull(clienteInsertado.getSucursal(),"El cliente no tiene sucursal!"),
				              () -> assertNotNull(clienteInsertado.getId(),"El cliente no tiene id!"));	
		
	}
	
	@Test
	@DisplayName("GestorClientes.altaCliente: a cliente cuya direccion esté alejada de cualquier sucursal se le asignará la sucursal virtual")
	public void altaClienteConDireccionAlejadaDeCualquierSucursal() throws Exception {
		
		//Dados:
		Cliente cliente = new Cliente(null, "John McClane", "C/Lejísimos", "123456");
		
		//Este es el objeto real que queremos probar
		GestorClientes gestorClientes = new GestorClientes(); 
		
		//Test doubles:		
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class);
		
		//Stub:
		GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class); //Nos entrega un dummie
		Mockito
			.when(gestorSucursales.encontrarSucursalCercana(any(String.class)))
			.thenReturn(null);
		
		//Stub:
		GestorComerciales gestorComerciales = mock(GestorComerciales.class); //Comenzamos con un dummie
		List<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial(1,"EMP-1","Comercial1"));
		comerciales.add(new Comercial(2,"EMP-2","Comercial2"));	
		Mockito
			.when(gestorComerciales.encontrarComerciales())
			.thenReturn(comerciales);
		
		//Stub:
		ClienteDao clienteDao = Mockito.mock(ClienteDao.class);
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
		Assertions.assertAll( () -> Assertions.assertEquals(2, clienteInsertado.getComerciales().size(),"El cliente no tiene comerciales!"),
				              () -> Assertions.assertEquals("Sucursal virtual", clienteInsertado.getSucursal().getNombre(),"El cliente no tiene la sucursal virtual!"),
				              () -> assertNotNull(clienteInsertado.getId(),"El cliente no tiene id!"));	
		
	}
	
	@Test
	@DisplayName("GestorClientes.altaCliente: un cliente con la direccion falsa lanzará una DireccionException")
	public void altaClienteTestDireccionFalsa() {
		
		//Dados
		String direccionFalsa = "C/Falsa, 123";
		Cliente cliente = new Cliente(null,"Paul",direccionFalsa,"Telefono");
		
		//Este es el objeto real que queremos probar
		GestorClientes gestorClientes = new GestorClientes();
		
		//Stub
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class); //Dummie
		try {
			Mockito
				.doThrow(new DireccionException("Esta direccion es falsa"))
				.when(gestorDirecciones)
				.comprobarDireccion(direccionFalsa);
		} catch (DireccionException e) {
			e.printStackTrace();
		}
		
		//Durante esta prueba sabemos que solo se utilizará gestorDirecciones y que no haría falta proporcionar
		//el resto de los test doubles
		gestorClientes.setGestorDirecciones(gestorDirecciones);
		
		//Entonces:		
		DireccionException ex = Assertions.assertThrows(DireccionException.class, 
				                                        () -> gestorClientes.altaCliente(cliente) );	
		Assertions.assertEquals("Esta direccion es falsa", ex.getMessage());			
	}	
	
	@Test
	@DisplayName("Comprobamos que gestorClientes.altaCliente realiza las llamadas correctas a sus dependecias")
	public void pruebaMOCKS() throws Exception {
						
		//Dados:
		Cliente cliente = new Cliente(null,"N1","D1","T1");
						
		GestorClientes gestorClientes = new GestorClientes();
		
		//TestDoubles;
		GestorDirecciones gestorDirecciones = Mockito.mock(GestorDirecciones.class);		
		
		GestorSucursales gestorSucursales = Mockito.mock(GestorSucursales.class);	
		try {
			Mockito
				.when(gestorSucursales.encontrarSucursalCercana(any(String.class)))
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
		gestorClientes.altaCliente(cliente);
		
		//Entonces:
		Mockito.verify(clienteDao).insertar(any(Cliente.class)); //Verifica que se ha llamado UNA vez
		Mockito.verify(gestorComerciales).encontrarComerciales();
		Mockito.verify(gestorDirecciones).comprobarDireccion(any(String.class));
		Mockito.verify(gestorSucursales).encontrarSucursalCercana(any(String.class));
	}
	
}









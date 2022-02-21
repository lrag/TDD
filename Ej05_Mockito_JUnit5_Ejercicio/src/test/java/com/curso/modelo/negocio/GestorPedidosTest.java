package com.curso.modelo.negocio;

import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.entidad.DetallePedido;
import com.curso.modelo.entidad.Pedido;
import com.curso.modelo.entidad.Producto;
import com.curso.modelo.negocio.excepcion.DatosBancariosEx;
import com.curso.modelo.negocio.excepcion.ExistenciasEx;
import com.curso.modelo.persistencia.PedidoDao;

//PARA QUE FUNCIONE LA ANOTACIÓN @Mock:
@ExtendWith(MockitoExtension.class)
public class GestorPedidosTest {
	
	/*
	Que hace JUnit para una clase que solo tenga un @Test: 
	 
	crear una instancia de GestorPedidosTest
	invocar el método que esté marcado con @BeforeAll
	incovar el método que esté marcado con @BeforeEach
	invocar el método marcado que @Test 
	incovar el método que esté marcado con @AfterEach
	invocar el método que esté marcado con @AfterAll
	
	Con el @ExtendsWith(MockitoExtension.class):

	crear una instancia de PeliculaDaoTest
	invocar el método que esté marcado con @BeforeAll
	incovar el método que esté marcado con @BeforeEach
	-->Inicializar las propiedades que estén marcadas con @Mock
	invocar el método marcado que @Test 
	incovar el método que esté marcado con @AfterEach
	invocar el método que esté marcado con @AfterAll	
	*/	
	
	//Este es el objeto REAL que se va a probar
	private GestorPedidos gestorPedidos;

	/*
	//Sin la anotación @Mock: 
	private PedidoDao pedidoDao = Mockito.mock(PedidoDao.class);
	private GestorBancos gestorBancos = Mockito.mock(GestorBancos.class);
	private GestorAlmacen gestorAlmacen = Mockito.mock(GestorAlmacen.class);
	private GestorTransportes gestorTransportes = Mockito.mock(GestorTransportes.class);
	private GestorOfertas gestorOfertas = Mockito.mock(GestorOfertas.class);
	*/ 
	
	//MOCKS. Ya tenemos dummies aqui:
	@Mock private PedidoDao pedidoDao; //Null
	@Mock private GestorBancos gestorBancos; 
	@Mock private GestorAlmacen gestorAlmacen; 
	@Mock private GestorTransportes gestorTransportes; 
	@Mock private GestorOfertas gestorOfertas; 

	//Estos pedidos se usarán para las diferentes pruebas:
	private Pedido pedidoOk;
	private Pedido pedidoTCMal;
	private Pedido pedidoExistenciasMal;
	private Producto p5; 
	
	//Método que inicializa los pedidos
	private void crearPedidos() {
		
		Producto p1 = new Producto(null, "P1", "F1", 25d, 1000);
		Producto p2 = new Producto(null, "P2", "F2", 50d, 1000);
		Producto p3 = new Producto(null, "P3", "F3", 75d, 1000);
		Producto p4 = new Producto(null, "P4", "F4", 100d, 1000);
		p5 = new Producto(null, "P5", "F5", 125d, 1000);
		
		Cliente c1 = new Cliente(1, "Harpo Marx", "Su casa", "123", 1);
		Cliente c2 = new Cliente(2, "Mongomery Burns", "Su mansión", "123", 9999);
		
		pedidoOk = new Pedido(1, "PED-0", LocalDate.now(), "PENDIENTE", c2, null);
		List<DetallePedido> detalles2 = new ArrayList<>();
		detalles2.add(new DetallePedido(3, pedidoOk, p1, 25d, 25));
		detalles2.add(new DetallePedido(4, pedidoOk, p3, 75d, 25));
		detalles2.add(new DetallePedido(5, pedidoOk, p5, 125d, 25));
		pedidoOk.setDetalles(detalles2);		

		pedidoTCMal = new Pedido(2, "PED-1", LocalDate.now(), "PENDIENTE", c1, null);
		List<DetallePedido> detalles1 = new ArrayList<>();
		detalles1.add(new DetallePedido(1, pedidoTCMal, p2, 25d, 25));
		detalles1.add(new DetallePedido(2, pedidoTCMal, p4, 25d, 25));
		pedidoTCMal.setDetalles(detalles1);
		
		pedidoExistenciasMal = new Pedido(3, "PED-2", LocalDate.now(), "PENDIENTE", c2, null);
		List<DetallePedido> detalles3 = new ArrayList<>();
		detalles3.add(new DetallePedido(3, pedidoExistenciasMal, p1, 25d, 25));
		detalles3.add(new DetallePedido(4, pedidoExistenciasMal, p3, 75d, 25));
		detalles3.add(new DetallePedido(5, pedidoExistenciasMal, p5, 125d, 2500));
		pedidoExistenciasMal.setDetalles(detalles3);
	}	
	
	@BeforeEach
	public void inicializar() {
		crearPedidos();
		gestorPedidos = new GestorPedidos(); //Este es el objeto real que vamos a probar	
		
		//Le asignamos los dummies
		gestorPedidos.setPedidoDao(pedidoDao);
		gestorPedidos.setGestorBancos(gestorBancos);
		gestorPedidos.setGestorOfertas(gestorOfertas);
		gestorPedidos.setGestorTransportes(gestorTransportes);
		gestorPedidos.setGestorAlmacen(gestorAlmacen);
		
		//Si usaramos estos objetos REALES para la prueba
		//entonces no sería un test unitario, sino uno de integración
		//No es que esté mal el querer un test de integración/funcional, pero aqui queremos un test unitario y usaremos TEST DOUBLES
		/*
		PedidoDao pedidoDao     = new PedidoDaoJPAImplementation();
		ProductoDao productoDao = new ProductoDaoJPAImplementation();
		
		GestorBancos      gestorBancos = new GestorBancos();
		GestorAlmacen     gestorAlmacen = new GestorAlmacen();
		GestorTransportes gestorTransportes = new GestorTransportes();
		GestorOfertas     gestorOfertas = new GestorOfertas();
		
		gestorAlmacen.setProductoDao(productoDao);
		
		gestorPedidos.setPedidoDao(pedidoDao);
		gestorPedidos.setGestorAlmacen(gestorAlmacen);
		gestorPedidos.setGestorBancos(gestorBancos);
		gestorPedidos.setGestorOfertas(gestorOfertas);
		gestorPedidos.setGestorTransportes(gestorTransportes);
		*/		
	}

	@Test
	@DisplayName("GestorPedidos.aceptar funciona cuando el pedido es correcto")
	public void aceptarPedido() throws Exception {
		
		//DADOS
		Integer idPedido = 1;
		
		//PedidoDao: Stub
		Mockito
			.when(pedidoDao.buscar(idPedido))
			.thenReturn(pedidoOk);
		
		//GestorBancos: Dummie		
		//GestorAlmacen: Dummie
		
		//GestorTransportes: Stub
		Mockito
			.when(gestorTransportes.obtenerCamion(true))
			.thenReturn("MOC MOOOOOOOC");

		//GestorOfertas: Stub
		Mockito
			.when(gestorOfertas.obtenerPerritoPiloto(true))
			.thenReturn("REGALO");
	
		//CUANDO:
		Pedido pedidoAceptado = gestorPedidos.aceptar(idPedido);
		
		//ENTONCES:
		Assertions.assertAll(
			() -> Assertions.assertNotNull(pedidoAceptado.getCamion(),"El pedido no tiene camión"),
			() -> Assertions.assertNotNull(pedidoAceptado.getRegalo(),"El pedido no tiene regalo"),
			//() -> Assertions.assertNotNull(pedidoAceptado.getFactura(),"El pedido no tiene factura"),
			() -> Assertions.assertEquals("ACEPTADO", pedidoAceptado.getEstado(),"El pedido no tiene estado 'ACEPTADO'")
		);
	
	}
		
	@Test
	@DisplayName("GestorPedidos.aceptar lanza datosBancarios exception cuando hay un problema con los datos bancarios del cliente")
	public void aceptarPedidoDatosBancariosMal() throws Exception {

		//DADOS:
		Integer idPedido = 2;
		
		//Test doubles:
		
		//PedidoDao: stub
		Mockito
			.when(pedidoDao.buscar(idPedido))
			.thenReturn(pedidoTCMal);

		//GestorBancos: stub
		Mockito
			.doThrow(new DatosBancariosEx("Datos bancarios incorrectos"))
			.when(gestorBancos).comprobarTC(Mockito.any(Integer.class));
		
		//GestorAlmacen: dummie
		//GestorTransportes: dummie
		//GestorOfertas: dummie	
		
		//CUANDO + ENTONCES:		
		Exception e = Assertions.assertThrows(DatosBancariosEx.class, 
				                              () -> gestorPedidos.aceptar(idPedido), 
				                              "No se la lanzado DatosBancariosEx");
		Assertions.assertEquals("Datos bancarios incorrectos", e.getMessage());
	}	
	
	@Test
	@DisplayName("GestorPedidos.aceptar lanza ExistenciasException cuando no hay existencias de un producto...")
	public void aceptarPedidoExistenciasInsuficientes() throws ExistenciasEx {
		
		//DADOS
		Integer idPedido = 3;
		
		//Test doubles
		
		//PedidoDao: stub
		Mockito
			.when(pedidoDao.buscar(idPedido))
			.thenReturn(pedidoExistenciasMal);
		
		//GestorBancos: dummie

		//GestorAlmacen: stub
		Mockito
			.doThrow(new ExistenciasEx())
			.when(gestorAlmacen)
			//.comprobarExistencias(p5, new Integer(2500) );
			.comprobarExistencias(Mockito.any(Producto.class), Mockito.any(Integer.class));
		
		//GestorTransportes: dummie
		//GestorOfertas: dummie
		
		//ENTONCES
		Assertions.assertThrows(ExistenciasEx.class, 
				                ()->gestorPedidos.aceptar(idPedido),
				                "No ha lanzado ExistenciasEx");
		
	}
	
	@Test
	@DisplayName("GestorPedidos.aceptar invoca correctamente a sus dependencias")
	public void pruebaMocks() throws Exception {
		
		//Dados
		Integer idPedido = 1;
		
		//PedidoDao: stub
		Mockito
			.when(pedidoDao.buscar(idPedido))
			.thenReturn(pedidoOk);
		
		//GestorBancos: dummie
		//GestorAlmacen: dummie
		
		//GestorTransportes: stub
		Mockito
			.when(gestorTransportes.obtenerCamion(true))
			.thenReturn("MOOOOC MOOOOOOOOC");
		
		//GestorOfertras: stub
		Mockito
			.when(gestorOfertas.obtenerPerritoPiloto(true))
			.thenReturn("Perrito Piloto");		
		
		//Cuando
		Pedido pedidoAceptado = gestorPedidos.aceptar(idPedido);
		
		//Entonces
		
		//Esto aqui es opcional:		
		Assertions.assertAll(
				() -> Assertions.assertNotNull(pedidoAceptado.getCamion(),"El pedido no tiene camión"),
				() -> Assertions.assertNotNull(pedidoAceptado.getRegalo(),"El pedido no tiene regalo"),
				() -> Assertions.assertEquals("ACEPTADO", pedidoAceptado.getEstado(),"El pedido no tiene estado 'ACEPTADO'")
			);	
		
		//Si comprobamos el numero de llamadas o el orden de las mismas DEJA DE SER UNA PRUEBA UNITARIA
		Mockito.verify(gestorBancos).comprobarTC(Mockito.any()); //Exactamente una vez
		Mockito.verify(gestorAlmacen, Mockito.times(3)).comprobarExistencias(any(Producto.class), any(Integer.class));
		Mockito.verify(gestorAlmacen, Mockito.times(3)).reducirExistencias(any(Producto.class), any(Integer.class));
		Mockito.verify(gestorTransportes).obtenerCamion(true); //Una y solo una vez
		Mockito.verify(gestorOfertas, Mockito.times(1)).obtenerPerritoPiloto(true); //Así escribimos más
		
	}
		
}














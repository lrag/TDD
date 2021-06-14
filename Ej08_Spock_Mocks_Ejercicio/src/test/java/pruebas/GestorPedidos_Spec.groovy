package pruebas

import java.time.LocalDate

import com.curso.modelo.entidad.Cliente
import com.curso.modelo.entidad.DetallePedido
import com.curso.modelo.entidad.Pedido
import com.curso.modelo.entidad.Producto
import com.curso.modelo.negocio.GestorAlmacen
import com.curso.modelo.negocio.GestorBancos
import com.curso.modelo.negocio.GestorOfertas
import com.curso.modelo.negocio.GestorPedidos
import com.curso.modelo.negocio.GestorTransportes
import com.curso.modelo.negocio.excepcion.DatosBancariosEx
import com.curso.modelo.negocio.excepcion.ExistenciasException
import com.curso.modelo.persistencia.PedidoDao

import spock.lang.Specification

class GestorPedidos_Spec extends Specification {

	Pedido pedidoTCMal
	Pedido pedidoOk
	Pedido pedidoExistenciasMal
	
	//Este es el objeto real que vamos a probar
	GestorPedidos gestorPedidos
	
	//Estos son los test doubles
	PedidoDao pedidoDao 
	GestorBancos gestorBancos 
	GestorAlmacen gestorAlmacen 
	GestorTransportes gestorTransportes 
	GestorOfertas gestorOfertas 
	
	private void setup() {
		
		//Todos los productos tienen 1.000 unidades en existencias
		def p1 = new Producto(null, "P1", "F1", 25d, 1000)
		def p2 = new Producto(null, "P2", "F2", 50d, 1000)
		def p3 = new Producto(null, "P3", "F3", 75d, 1000)
		def p4 = new Producto(null, "P4", "F4", 100d, 1000)
		def p5 = new Producto(null, "P5", "F5", 125d, 1000)
		
		//Este cliente tiene la tarjeta de crédito falsa
		def c1 = new Cliente(1, "Harpo", "Su casa", "123", 1)
		//Este cliente tiene la tarjeta de crédito bien
		def c2 = new Cliente(2, "Mongomery Burns", "Su mansión", "123", 9999)
		
		//Pedido correcto
		pedidoOk = new Pedido(1, "PED-0", LocalDate.now(), "PENDIENTE", c2, null)
		def detalles1 = new ArrayList<DetallePedido>()
		detalles1 = [ new DetallePedido(3, pedidoOk, p1, 25d, 25),
					  new DetallePedido(4, pedidoOk, p3, 75d, 25),
		              new DetallePedido(5, pedidoOk, p5, 125d, 25) ]
		pedidoOk.detalles = detalles1	

		pedidoTCMal = new Pedido(2, "PED-1", LocalDate.now(), "PENDIENTE", c1, null)
		def detalles2 = new ArrayList<DetallePedido>()
		detalles2 = [ new DetallePedido(1, pedidoTCMal, p2, 25d, 25),
		              new DetallePedido(2, pedidoTCMal, p4, 25d, 25) ]
		pedidoTCMal.detalles = detalles1

		pedidoExistenciasMal = new Pedido(3, "PED-2", LocalDate.now(), "PENDIENTE", c2, null)
		def detalles3 = new ArrayList<DetallePedido>()
		detalles3 = [ new DetallePedido(3, pedidoExistenciasMal, p1, 25d, 25),
		              new DetallePedido(4, pedidoExistenciasMal, p3, 75d, 25),
		              new DetallePedido(5, pedidoExistenciasMal, p5, 125d, 1001) ]
		pedidoExistenciasMal.detalles = detalles3
		
		//MOCKS
		//pedidoDao = Mock(PedidoDao)
		//Por defecto los inicializamos como dummies
		pedidoDao = Mock()
		gestorBancos = Mock()
		gestorAlmacen = Mock()
		gestorTransportes = Mock()
		gestorOfertas = Mock()		

		//GestorPedidos
		//Aqui inicializamos el objeto real que vamos a probar
		gestorPedidos = new GestorPedidos()
		gestorPedidos.pedidoDao = pedidoDao
		gestorPedidos.gestorBancos = gestorBancos
		gestorPedidos.gestorAlmacen = gestorAlmacen
		gestorPedidos.gestorTransportes = gestorTransportes
		gestorPedidos.gestorOfertas = gestorOfertas
		
	}

	def "test un pedido con datos correctos se inserta correctamente"() {
		
		given:		
			pedidoDao.buscar(1) >> pedidoOk
			//GestorBancos: basta el dummy
			//GestorAlmacen: basta el dummy
			gestorTransportes.obtenerCamion(true) >> "CAMIÓN!!"
			gestorOfertas.obtenerPerritoPiloto(true) >> "Perrito piloto"
					
		when:
			gestorPedidos.aceptar(pedidoOk)
		
		then:
			noExceptionThrown()
			pedidoOk.estado == "ACEPTADO"
			pedidoOk.camion == "CAMIÓN!!"
			pedidoOk.camion != null
	}

	def "un pedido con un cliente de datos bancarios incorrectos lanza DatosBancariosEx"() {

		given:	
			pedidoDao.buscar(2) >> pedidoTCMal
			gestorBancos.comprobarTC(pedidoTCMal.cliente.numeroTC) >> 
				{ throw new DatosBancariosEx("Datos bancarios incorrectos") }
			//GestorAlmacen: basta el dummy
			//GestorTransportes: basta el dummy
			//GestorOfertas: basta el dummy
		
		when:			
			gestorPedidos.aceptar(pedidoTCMal)
						
		then:
			thrown(DatosBancariosEx)
			pedidoTCMal.estado == "PENDIENTE"
			pedidoTCMal.camion == null
	}

	def "un pedido con falla si no hay existencias lanzando ExistenciasException"() {
		
		given:		
			pedidoDao.buscar(3) >> pedidoExistenciasMal
			//gestorBancos: basta el dummy
			
			/*
			gestorAlmacen.comprobarExistencias(_, _) >> {
					Producto producto, Integer cantidad -> 
						if(cantidad>1000) {
							throw new ExistenciasException(producto, cantidad, 1001)
						}
				}
			*/	
			gestorAlmacen.comprobarExistencias(_, 1001) >> 
				{ producto, cantidad -> throw new ExistenciasException(producto, cantidad, 1000) }
			
			//GestorTransportes: basta el dummy
			//GestorOfertas: basta el dummy
			
			when:			
				gestorPedidos.aceptar(pedidoExistenciasMal)
				
			then:
				thrown(ExistenciasException)
				pedidoExistenciasMal.estado == "PENDIENTE"
				pedidoExistenciasMal.camion == null
	}
	
}

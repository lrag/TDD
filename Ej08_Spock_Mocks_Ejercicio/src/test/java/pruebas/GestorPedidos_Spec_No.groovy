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

//
//En este ejemplo definimos los mocks en métodos independientes
//Cada mock tiene definido su comportamiento completo
//Esto implica que no se usan todos los metodos de los mocks 
//en cada prueba individual
//Tambien implica que no podemos hacer el verify de los métodos
//invocados, ni el número de veces ni el orden
//
class GestorPedidos_Spec_No extends Specification {

	Pedido pedido1
	Pedido pedido2
	
	GestorPedidos gestorPedidos
	
	PedidoDao pedidoDao
	GestorBancos gestorBancos
	GestorAlmacen gestorAlmacen
	GestorTransportes gestorTransportes
	GestorOfertas gestorOfertas
	
	private void crearPedidos() {
		def p1 = new Producto(null, "P1", "F1", 25d, 1000)
		def p2 = new Producto(null, "P2", "F2", 50d, 1000)
		def p3 = new Producto(null, "P3", "F3", 75d, 1000)
		def p4 = new Producto(null, "P4", "F4", 100d, 1000)
		def p5 = new Producto(null, "P5", "F5", 125d, 1000)
		
		def c1 = new Cliente(1, "Harpo", "Su casa", "123", 1)
		def c2 = new Cliente(2, "Mongomery Burns", "Su mansión", "123", 9999)
		
		pedido1 = new Pedido(1, "PED-0", LocalDate.now(), "PENDIENTE", c1, null)
		def detalles1 = new ArrayList<DetallePedido>()
		detalles1 << new DetallePedido(1, pedido1, p2, 25d, 25)
		detalles1 << new DetallePedido(2, pedido1, p4, 25d, 25)
		pedido1.detalles = detalles1

		pedido2 = new Pedido(2, "PED-1", LocalDate.now(), "PENDIENTE", c2, null)
		def detalles2 = new ArrayList<DetallePedido>()
		detalles2 << new DetallePedido(3, pedido2, p1, 25d, 25)
		detalles2 << new DetallePedido(4, pedido2, p3, 75d, 25)
		detalles2 << new DetallePedido(5, pedido2, p5, 125d, 25)
		pedido2.detalles = detalles2		
	}
	
	def PedidoDao getPedidoDaoMock() {
		pedidoDao = Mock(PedidoDao.class)
		pedidoDao.buscar(1) >> pedido1
		pedidoDao.buscar(2) >> pedido2
		return pedidoDao
	}
	
	def GestorBancos getGestorBancosMock() {
		gestorBancos = Mock(GestorBancos)
		//gestorBancos.comprobarTC(pedido1.cliente.numeroTC) >> {}
		gestorBancos.comprobarTC(pedido2.cliente.numeroTC) >> { throw new DatosBancariosEx("Datos bancarios incorrectos") }
		return gestorBancos
	}

	def GestorAlmacen getGestorAlmacenMock() {
		/*
		gestorAlmacen = Mock(GestorAlmacen)
		gestorAlmacen.comprobarExistencias(_, _) >> {
				Producto producto, Integer cantidad -> 
					if(cantidad>1000) {
						throw new ExistenciasException(producto, cantidad, 1000)
					}
			}
		*/

		gestorAlmacen = Mock(GestorAlmacen)
		gestorAlmacen.comprobarExistencias(_, 1000) >> 
			{ throw new ExistenciasException(producto, cantidad, 1000) }
		return gestorAlmacen
	}
	
	//GESTOR TRANSPORTES
	def getGestorTransportesMock() {
		gestorTransportes = Mock(GestorTransportes)
		gestorTransportes.obtenerCamion(true) >> "CAMIÓN!!"
		gestorTransportes.obtenerCamion(false) >> { throw new Exception("No hay camión :(") }
		return gestorTransportes
	}

	//GESTOR OFERTAS
	def getGestorOfertasMock() {
		gestorOfertas = Mock(GestorOfertas)
		gestorOfertas.obtenerPerritoPiloto(true) >> "Perrito piloto"
		gestorOfertas.obtenerPerritoPiloto(false) >> { throw new Exception("No hay perrito piloto :(") }
		return gestorOfertas
	}
	
	def setup() {
		
		crearPedidos()
		gestorPedidos = new GestorPedidos()
		
		gestorPedidos.pedidoDao         = getPedidoDaoMock();
		gestorPedidos.gestorBancos      = getGestorBancosMock();
		gestorPedidos.gestorAlmacen     = getGestorAlmacenMock();
		gestorPedidos.gestorTransportes = getGestorTransportesMock();
		gestorPedidos.gestorOfertas     = getGestorOfertasMock();
	}
	
	def "un pedido con datos correctos se inserta correctamente"() {
		
		when:			
			gestorPedidos.aceptar(pedido1)
			
			//Si: quiseramos definir aqui los test doubles:
			//pedidoDao.buscar(2) >> pedido2
			//gestorTransportes.obtenerCamion(true) >> "CAMIÓN!!"
			//gestorOfertas.obtenerPerritoPiloto(true) >> "Perrito piloto"
		
		then:
			noExceptionThrown()
			pedido1.estado == "ACEPTADO"
			pedido1.camion == "CAMIÓN!!"
			pedido1.camion != null
	}

	def "un pedido con un cliente de datos bancarios incorrectos lanza DatosBancariosEx"() {
		
		when:			
			gestorPedidos.aceptar(pedido2)
			
			//Si: quiseramos definir aqui los test doubles:
			//pedidoDao.buscar(1) >> pedido1
			//gestorBancos.comprobarTC(pedido2.cliente.numeroTC) >> { throw new DatosBancariosEx("Datos bancarios incorrectos") }
						
		then:
			thrown(DatosBancariosEx)
			pedido2.estado == "PENDIENTE"
			pedido2.camion == null
	}
	
}

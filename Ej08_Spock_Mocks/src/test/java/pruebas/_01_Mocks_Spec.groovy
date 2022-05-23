package pruebas

import com.curso.modelo.negocio.excepcion.SucursalException
import com.curso.modelo.entidad.Cliente
import com.curso.modelo.entidad.Comercial
import com.curso.modelo.entidad.Sucursal
import com.curso.modelo.negocio.GestorClientes
import com.curso.modelo.negocio.GestorComerciales
import com.curso.modelo.negocio.GestorDirecciones
import com.curso.modelo.negocio.GestorSucursales
import com.curso.modelo.negocio.excepcion.DireccionException
import com.curso.modelo.persistencia.ClienteDao

import spock.lang.Specification

//
//En este ejemplo definimos los mocks en m�todos independientes
//Cada mock tiene definido su comportamiento completo
//Esto implica que no se usan todos los metodos de los mocks 
//en cada prueba individual
//Tambien implica que no podemos hacer el verify de los m�todos
//invocados, ni el n�mero de veces ni el orden
//
class _01_Mocks_Spec extends Specification {

	//Test doubles:
	//
	//dummies: un objeto que para los metodos void no hace nada
	//         y para los que devuelven algo devuelve:
	//      	  -cero si es n�mero
	//        	  -false si es boolean
	//        	  -null si es referencia
	//stubs: un objeto que cuenta con una serie de respuestas enlatadas para determinados m�todos
	//fakes: un objeto programado por nosotros y que reproduce el comportamiento del objeto real
	//       un fake se programa de verdad!
	//mocks: un objeto que recuerda las llamadas que ha recibido, el orden de las mismas y el n�mero de veces

	//Esta es la clase que vamos a probar:
	GestorClientes gestorClientes
	
	//Estos son los test doubles que necesitaremos:
	//Observar que no hay que hacer un test double de 'GestorDirecciones' puesto que no lo utliza
	//GestorClientes directamente
	GestorSucursales gestorSucursales
	GestorComerciales gestorComerciales
	ClienteDao clienteDao
	
	def setup() {		
		//Inicialmente los test doubles son dummies
		//gestorComerciales = Mock(GestorComerciales.class)
		//gestorSucursales = Mock(GestorSucursales) //En groovy te puedes ahorrar el .class
		//clienteDao = Mock(ClienteDao)
		//Inicialmente los test doubles son dummies
		gestorComerciales = Mock()
		gestorSucursales = Mock()
		clienteDao = Mock()
		
		//Este es el de verdad 
		gestorClientes = new GestorClientes()
		//Proporcionamos los test doubles
		gestorClientes.gestorComerciales = gestorComerciales
		gestorClientes.gestorSucursales  = gestorSucursales
		gestorClientes.clienteDao = clienteDao
	}
	
	def "test un cliente con datos correctos se inserta correctamente"() {
		
		given:
			def cliente = new Cliente(null,"Nombre","Direccion","Telefono");
			
		and:
					
			//Stub
			gestorComerciales
				.encontrarComerciales() >> [ new Comercial(1,"EMP-1","Comercial1"),
											 new Comercial(2,"EMP-2","Comercial2") ]			
			//Stub
			//El '_' representa cualquier valor recibido 
			gestorSucursales
				.encontrarSucursalCercana(_) >> new Sucursal(1,"Sucursal1","C/Tal,123")
				
			//Stub
			//Pasando al mock un valor variable del tipo adecuado
			//A�adiendo un closure como cuerpo del m�todo para a�adir el comportamiento 'devuelve el
			//cliente recibido pero con un valor en el id'
			clienteDao.insertar(_) >> {
						Cliente cli ->
							cli.id = 1
							cli //return impl�cito, para disfrutar
					}

		when:			
			def clienteInsertado = gestorClientes.altaCliente(cliente)	
		
		then:
			clienteInsertado.sucursal.nombre == "Sucursal1"
			clienteInsertado.id == 1
			clienteInsertado.comerciales.size() == 2
	}
	
	def "test un cliente con direccion nula no se inserta"() {

		given:		
			def cliente = new Cliente(null,"Nombre",null,"Telefono")
			
		and :
			//Stub
			gestorSucursales
				.encontrarSucursalCercana(null) >>
					{ throw new DireccionException("Direcci�n nula") }
					
			//Para gestorComerciales nos basta el dummie porque no esperamos que llegue a utilizarse
			//al estar la direcci�n a null

			//Para clienteDao nos basta el dummie
		
		when:		
			def clienteInsertado = gestorClientes.altaCliente(cliente)
		
		then:
			Exception e = thrown(Exception) 
			e.message == "Direcci�n nula"
			clienteInsertado == null		
		
	}
	
	def "un cliente con direccion inexistente no se inserta"() {
		
		given:		
			def cliente = new Cliente(null,"Nombre","C/Falsa, 123","Telefono");
			
		and:		
			//Para gestorComerciales nos basta el dummie porque no esperamos que llegue a utilizarse
			//al estar la direcci�n a null		
			gestorSucursales
				.encontrarSucursalCercana(_) >>
					//Si en el cuerpo del closure no se usan los par�metros no hace falta ponerlos:
					//{ direccion -> throw new DireccionException("Esta direccion es falsa") }
					{ throw new DireccionException("Esta direccion es falsa") }
		
			//Para clienteDao nos basta el dummie
		
		when:
		
			def clienteInsertado = gestorClientes.altaCliente(cliente)
			
		then:
			Exception e = thrown(Exception)
			e.message == "Esta direccion es falsa"
			clienteInsertado == null
			
	}

}

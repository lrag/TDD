package pruebas

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
//En este ejemplo definimos los mocks y verificamos que 
//el numero de llamadas que reciben es el correcto.
//
//Por la naturaleza de los mocks de spock no podemos separar
//la definición del comportamiento del mock de la verificación
//de las llamadas
//
//Esto implica que no podemos definir el mock con todo su comportamiento
//y reutilizarlo en diferentes tests y hay que definirlos una y otra
//vez, cada vez con las características adecuadas
//
//Ademas hay que tener en cuenta que al hacer la verificación el test deja de ser 
//unitario 'caja negra' para convertirse casi en uno de integración
//
//Conclusión: mucho cuidado con esto
//
class _02_Mocks_Interacciones_Spec extends Specification {

	GestorClientes gestorClientes
	GestorSucursales gestorSucursales = Mock(GestorSucursales.class)
	GestorComerciales gestorComerciales = Mock(GestorComerciales.class)
	ClienteDao clienteDao = Mock(ClienteDao.class)
	
	def setup() {
		//Este es el de verdad
		gestorClientes = new GestorClientes()
		//Le inyectamos los test doubles que de momento son dummies
		gestorClientes.gestorComerciales = gestorComerciales
		gestorClientes.gestorSucursales  = gestorSucursales
		gestorClientes.clienteDao = clienteDao
	}
	
	def "un cliente con datos correctos se inserta correctamente"() {
		
		given:
			def cliente = new Cliente(null,"Nombre","Direccion","Telefono")
			
			1 * gestorComerciales.encontrarComerciales() >>
				[ new Comercial(1,"EMP-1","Comercial1"),
				  new Comercial(2,"EMP-2","Comercial2") ]
								
			1 * gestorSucursales.encontrarSucursalCercana(_) >>
				new Sucursal(1,"Sucursal1","C/Falsa,123")
				
			1 *	clienteDao
				.insertar(_) >> {
						Cliente cli ->
							cli.id = 1
							return cli
					}

		when:			
			def clienteInsertado = gestorClientes.altaCliente(cliente)	
		
		then:
			clienteInsertado.sucursal.nombre == "Sucursal1"
			clienteInsertado.id == 1
			clienteInsertado.comerciales.size() == 2
	}

	//Otra manera de escribirlo, con el comportamiento de los mocks y las interacciones definidas
	//en el THEN		
	def "un cliente con datos correctos se inserta correctamente BIS"() {
		
		given:
			def cliente = new Cliente(null,"Nombre","Direccion","Telefono")

		when:
			def clienteInsertado = gestorClientes.altaCliente(cliente)
		
		then:
	
			1 * gestorComerciales.encontrarComerciales() >>
					[ new Comercial(1,"EMP-1","Comercial1"),
					  new Comercial(2,"EMP-2","Comercial2") ]
								
			1 * gestorSucursales.encontrarSucursalCercana(_) >>
				new Sucursal(1,"Sucursal1","C/Falsa,123")
				
			1 *	clienteDao
				.insertar(_) >> {
						Cliente cli ->
							cli.id = 1
							return cli
					}
	
			clienteInsertado.sucursal.nombre == "Sucursal1"
			clienteInsertado.id == 1
			clienteInsertado.comerciales.size() == 2
	}

	//Para comprobar el orden
	def "un cliente con datos correctos se inserta correctamente y los mocks se invocan en el orden correcto"() {
		
		given:
			def cliente = new Cliente(null,"Nombre","Direccion","Telefono")

		when:
			def clienteInsertado = gestorClientes.altaCliente(cliente)

		then:
			1 * gestorSucursales.encontrarSucursalCercana(_) >>
				new Sucursal(1,"Sucursal1","C/Falsa,123")

		then:
			1 * gestorComerciales.encontrarComerciales() >>
					[ new Comercial(1,"EMP-1","Comercial1"),
					  new Comercial(2,"EMP-2","Comercial2") ]					

		then:				
			1 *	clienteDao
				.insertar(_) >> {
						Cliente cli ->
							cli.id = 1
							return cli
					}
	
		then: "quizas esto no quisieramos comprobarlo, puesto que este test es para comprobar el orden"
			clienteInsertado.sucursal.nombre == "Sucursal1"
			clienteInsertado.id == 1
			clienteInsertado.comerciales.size() == 2
	}

	
	def "un cliente con datos incorrectos lanza la excepcion adecuada"() {		

		when:
			def cliente = new Cliente(null,"Nombre",null,"Telefono")

			1 * gestorSucursales.encontrarSucursalCercana(null) >>
				{ throw new DireccionException("Dirección nula") }
			
			def clienteInsertado = gestorClientes.altaCliente(cliente)			
		
		then:
			Exception e = thrown(Exception) 
			e.message == "Dirección nula"
			clienteInsertado == null	
		
		when:
			cliente = new Cliente(null,"Nombre","C/Falsa, 123","Telefono")

			1 * gestorSucursales.encontrarSucursalCercana(cliente.direccion) >>
				{ throw new DireccionException("La dirección es falsa") }
			
			clienteInsertado = gestorClientes.altaCliente(cliente)			
					
		then:
			e = thrown(Exception) 
			e.message == "La dirección es falsa"
			clienteInsertado == null			
			
	}

}

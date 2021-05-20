package pruebas

import com.curso.modelo.entidad.Persona
import com.curso.modelo.negocio.Calculadora
import com.curso.modelo.negocio.GestorPersonas
import spock.lang.Specification
import spock.lang.Unroll


class _06_PruebaParametrizada_Spec extends Specification {
	
	//Si necesitamos repetir un test con distintos datos podemos hacer esta aproximación:
	def "calculadora.sumar debe sumar correctamente, pero esto es un poco trapero"(){
		
		given:
			def calculadora = new Calculadora()
			
		expect:
			//Con varios asertos seguidos funciona, pero en el informe no los vemos individualmente
			//
			//Es más dificil extraer los datos para las pruebas de otros orígenes. Por ejemplo: un fichero
			//
			//Estamos infringiendo la norma 'crear un objeto a probar nuevo para cada test'
			calculadora.sumar(1,2) == 3 
			calculadora.sumar(3,4) == 7 
			calculadora.sumar(5,6) == 11 
		
	}
	
	
	//Es posible añadir los datos con los que realizar un test en  
	//el bloque 'where'
	//
	//El test se ejecutará una vez por cada juego de datos
	//
	//Unroll indica a spock que necesitamos que en el informe cada ejecución del 
	//test aparezca por separado:	
	@Unroll
	//Incluso podemos utilizar los parámetros en el nombre del test
	//
	//Disponemos de dos formatos para aportar la informacion:
	// -Data tables
	// -Data pipes
	def "calculadora.sumar debe sumar correctamente  #s1 + #s2 = #suma "() {
		
		given:
			def calculadora = new Calculadora()
	 
		when:
			println (s1+","+s2+","+suma)
			def rs = calculadora.sumar(s1, s2)
	 
		then:
			rs == suma
			
		where:
			//A este formato se le llama 'data tables' en Spock
			s1   | s2   | suma
			1    | 2    | 3
			10   | 20   | 30
			100  | 200  | 300
			
			/*Las tablas han de tener mínimo dos columnas. 
			//Si solo necesitamos una debemos usar esta sintaxis:
			dato | _
			a    | _
			b    | _
			c    | _
			*/			
			
	}
	
	@Unroll
	def "test parametrizado con colecciones"() {
		
		when:
			def tam = lista.size()
			
		then:
			tam == tamEsperado
			
		where:
		
			lista                         | tamEsperado
			["uno","dos","tres","cuatro"] | 4
			["aaa","bbb","ccc"]           | 3
	}
	
	@Unroll
	def "test parametrizado con data pipes #suma"(){
		given:
			def calculadora = new Calculadora()
	 
		when:
			println (s1+","+s2+","+suma)
			def rs = calculadora.sumar(s1, s2)
	 
		then:
			rs == suma
			
		where:
			s1   << [1,2,3]
			s2   << [3,2,1]
			suma << [4,4,4]
		
	}
	
	@Unroll
	def "test parametrizado con objetos. #persona.nombre"(){
		given:
			def gestorPersonas = new GestorPersonas()
	 
		when:
			gestorPersonas.insertar(persona)
	 
		then:
			noExceptionThrown()
			
		where:
			persona   << [ new Persona("Philip Marlowe","C/Tal","123"),
				           new Persona("Lew Archer","C/Pascual","456"),
				           new Persona("Columbo","C/Pascual","456"),
						   new Persona("Jules Maigret","C/Pascualè","789") ]		
	}
	
	@Unroll
	def "test parametrizado que obtiene los datos de un método. #persona.nombre"(){
		given:
			def gestorPersonas = new GestorPersonas()
	 
		when:
			gestorPersonas.insertar(persona)
	 
		then:
			noExceptionThrown()
			
		where:
			persona   << getPersonas()
	}
	
	public getPersonas() {
		//El return va implícito
		[ new Persona("Sam Spade","C/Tal","123"),
		  new Persona("David Addison","C/Pascual","456"),
		  new Persona("Mike Hammer","C/Pascual","456"),
		  new Persona("Pepe Carvalho","C/Pascualè","789") ]
	}

}

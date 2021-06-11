package pruebas

import com.curso.modelo.entidad.Persona
import com.curso.modelo.negocio.Calculadora
import com.curso.modelo.negocio.GestorPersonas

import spock.lang.Specification


class _04_Excepciones_Spec extends Specification {
	
	def "haciendo algo malo esperamos una excepción"() {
		
		given:
			def datos = [ 1, 2, 3, 4 ]
	 
		when:
			datos.remove(20)
	 
		then:
			thrown(IndexOutOfBoundsException)
			//La ejecución del test continúa pese a la excepción
			datos.size() == 4		
	}

	//Accediendo a la excepción que se ha lanzado 
	def "dividiendo por cero calculadora lanzará excepción"(){
		
		given:
			def calculadora = new Calculadora()
		
		when:
			calculadora.dividir(100, 0)
			
		then:
			//thrown devuelve la excepción
			Exception e = thrown(Exception)
			e.message == "División por cero"		
	}
	
	//Comprobando que no se lanza una excepcion
	def "gestorPersonas.insertar ha de funcionar con datos correctos"(){
		
		given:
			def gestorPersonas = new GestorPersonas()
			
		and:
		
			def persona = new Persona("Harry Callahan", "SF", "555")
		
		when:
			//Observar que aqui no nos obligan a capturar excepciones:
			gestorPersonas.insertar(persona)
			
		then:
			noExceptionThrown()
			//otras cosas que sin duda habría que comprobar...
			
	}	
	
}



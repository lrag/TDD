package pruebas

import com.curso.modelo.entidad.Persona
import com.curso.modelo.negocio.Calculadora
import com.curso.modelo.negocio.GestorPersonas

import spock.lang.Specification


class _04_Excepciones_Spec extends Specification {
	
	def "haciendo algo malo esperamos una excepci�n"() {
		
		given:
			def datos = [ 1, 2, 3, 4 ] //Parece un array pero es un List<Integer>
	 
		when:
			datos.remove(20)
	 
		then:
			thrown(IndexOutOfBoundsException)
			//La ejecuci�n del test contin�a pese a la excepci�n
			datos.size() == 4		
	}

	//Accediendo a la excepci�n que se ha lanzado 
	def "dividiendo por cero calculadora lanzar� excepci�n"(){
		
		given:
			def calculadora = new Calculadora()
		
		when:
			calculadora.dividir(100, 0)
			
		then:
			//thrown devuelve la excepci�n
			Exception e = thrown(Exception)
			e.message == "Divisi�n por cero"		
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
			//otras cosas que sin duda habr�a que comprobar...
			
	}	
	
}



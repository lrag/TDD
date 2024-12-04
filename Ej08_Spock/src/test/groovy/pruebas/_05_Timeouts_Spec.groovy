package pruebas

import com.curso.modelo.entidad.Persona
import com.curso.modelo.negocio.Calculadora
import com.curso.modelo.negocio.GestorPersonas

import spock.lang.Specification
import spock.lang.Timeout
import java.util.concurrent.TimeUnit;

//@Timeout(value = 20000, unit = TimeUnit.MILLISECONDS)
class _05_Timeouts_Spec extends Specification {
	
	
	@Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
	def "Esta prueba debe ejecutarse en el tiempo estipulado"() {

		given:
			def calculadora = new Calculadora()
		
		when:
			calculadora.calculoExtremadamenteComplejo()
			
		then:
			noExceptionThrown()	
				
	}

}
package pruebas

import spock.lang.Specification

//Siempre heredamos de Specification
class _01_Ejemplo_Spec extends Specification {

	//Feature = Test
	def "dos y dos son cuatro"() {
		
		//Bloque expect
		expect:
			//El aserto es implicito
			2 + 2 == 4
			
	}

}





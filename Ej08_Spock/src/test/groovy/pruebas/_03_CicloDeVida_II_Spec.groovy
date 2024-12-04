package pruebas

import com.curso.modelo.negocio.Calculadora

import spock.lang.Specification

class _03_CicloDeVida_II_Spec extends Specification {

	def calculadora
	
	//Si necesitamos hacer las mismas tareas como preparación o limpieza de cualquier test
	//podemos usar callbacks, aqui llamados 'fixtures'
	def setupSpec() {
		//System.out.println("Antes de los test")
		println "Antes de los test"
	}

	def cleanupSpec() {
		println "Despues de los test"
	}

	def setup() {
		println "Antes de cada test"
		calculadora = new Calculadora()
	}

	def cleanup() {
		println "Despues de cada test" 
	}
		
	def "calculadora.sumar suma correctamente"(){
		
		when:
			def rs = calculadora.sumar(10, 20)
		
		then:
			rs == 30			
	}	
	
	def "calculadora.cuadrado eleva al cuadrado correctamente BIS"(){
		
		expect:
			625 == calculadora.cuadrado(25)
	} 
		
}


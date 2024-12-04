package pruebas

import com.curso.modelo.negocio.Calculadora

import spock.lang.Specification

//Siempre heredamos de Specification
class _02_Bloques_Spec extends Specification {
	
	/*
	Setup/Given – Se ejecuta cualquier inicialización previa al test. 
	              Es un bloque implícito, cualquier código no incluido en un bloque se supondrá en él.
	When        – Bloque en el que invocamos el código a probar
	Then        – Aserto. Colocamos en el una expresión que se evaluará como un boolean
	Expect      – Suma de when y Then, su uso es opcional
	Cleanup     – Código que elimina cualquier resto de la ejecución del test
	*/
	
	//given - when - then 
	def "cuatro y dos son seis"() {
		
		given:
			int s1 = 4
			int s2 = 2
	 
		when:
			int rs = s1 + s2
	 
		then:
			rs == 6
		
	}

	//given - expect
	//expect es la suma de when y then
	def "seis y dos son ocho"() {
		
		given:
			int s1 = 6
			int s2 = 2
			
		expect:
			s1 + s2 == 8					
	}
	
	//Cada bloque puede expresarse en varias partes utilizando 'and'
	def "y ocho dieciseis"() {
		
		given:
			int s1 = 8
			
		and:
			int s2 = 8
			
		expect:
			s1 + s2 == 16
		and:
			(s1 + s2) % 2 == 0
	}
	
	//Salvo given/setup y clean up el resto de bloques puede repetirse
	//Esto en realidad es juntar dos test en uno, utilizar con precaución
	def "probando varias cosas que realmente esten relacionadas"() {
		
		given: "dada esta calculadora"
			def calculadora = new Calculadora()
			
		when: "division correcta"
			def res = calculadora.dividir(10, 2)
			
		then:
			res == 5
			
		when: "division por cero"
			res = calculadora.dividir(10, 0)
			
		then:
			thrown(Exception)		
		
	}
	
}

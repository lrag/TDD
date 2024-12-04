package pruebas

import com.curso.modelo.negocio.Calculadora

import spock.lang.Specification

class _03_CicloDeVida_I_Spec extends Specification {

	//Se crear� un objeto _03_CicloDeVida para cada test, por lo que
	//tambien se instancia una nueva Calculadora
	def calculadora = new Calculadora()
	
	//Si declaramos la calculadora como un atributo est�tico se reutilizar�
	//en todos los test de esta clase
	//def static calculadora = new Calculadora()
	
	def "calculadora.sumar suma correctamente"(){
		
		/*
		when:
			def rs = calculadora.sumar(10, 20)
		
		then:
			rs == 30
		*/
		
		expect: 
			calculadora.sumar(10, 20) == 30
			
	}	
	
	def "calculadora.cuadrado eleva al cuadrado correctamente "(){
		
		when:
			def rs = calculadora.cuadrado(25)
		
		then:
			rs == 625
	} 

	//El test anterior podr�a haberse escrito as�:
	def "calculadora.cuadrado eleva al cuadrado correctamente BIS"(){
		
		expect:
			625 == calculadora.cuadrado(25)

	} 
		
}


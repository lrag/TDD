package com.curso.pruebas;

import com.curso.modelo.negocio.CalculadoraPrueba;

public class PruebaMain2 {

	public static void main(String[] args) {
		
		CalculadoraPrueba c = new CalculadoraPrueba();
		
		double resultado = c.cuadrado(5d);
		
		if(resultado == 25d) {
			System.out.println("calculadora.cuadrado OK");
		} else {
			System.out.println("calculadora.cuadrado ZASCA!");
		}
		
		
	}
	
	
}

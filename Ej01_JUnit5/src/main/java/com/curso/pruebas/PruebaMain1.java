package com.curso.pruebas;

import com.curso.modelo.negocio.CalculadoraPrueba;

public class PruebaMain1 {

	public static void main(String[] args) {
		
		CalculadoraPrueba c = new CalculadoraPrueba();
		
		double resultado = c.sumar(10d,20d);
		
		if(resultado == 30d) {
			System.out.println("calculadora.sumar OK");
		} else {
			System.out.println("calculadora.sumar ZASCA!");
		}
		
		
	}
	
	
}

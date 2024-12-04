package com.curso.modelo.negocio;

public class Calculadora {
	
	public Calculadora() {
		super();
		System.out.println("Creando Calculadora");
	}

	public Double sumar(Double...sumandos) {
		Double suma = 0d;
		for(Double sumando:sumandos) {
			suma = suma + sumando;
		}
		return suma;
	}

	public Double dividir(Double dividendo, Double divisor) throws Exception {
		if( divisor == 0) {
			throw new Exception("División por cero");
		}
		return dividendo / divisor;
	}
		
	public Integer calculoExtremadamenteComplejo() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 1;
	}	
	
	public Double cuadrado(Double base) {
		return base*base;
	}
	
}

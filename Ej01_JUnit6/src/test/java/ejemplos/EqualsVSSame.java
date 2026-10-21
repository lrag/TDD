package ejemplos;

import com.curso.modelo.entidad.Cliente;

public class EqualsVSSame {

	public static void main(String[] args) {
		
		int n1 = 20;
		int n2 = 20;
		
		System.out.println("===============================================");
		System.out.println(n1);
		System.out.println(n2);
		
		if(n1 == n2) {
			System.out.println("Iguales");
		} else {
			System.out.println("Direferentes");
		}
		
		System.out.println("===============================================");
		Cliente c1 = new Cliente(1L,"Ringo", "C/Su calle", "555123456");
		Cliente c2 = new Cliente(1L,"Ringo", "C/Su calle", "555123456");
		Cliente c3 = new Cliente(1L,"Fistro", "C/Tal", "555654321");
		Cliente c4 = c1;
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		
		if( c1 == c2 ) {
			System.out.println("Iguales");
		} else {
			System.out.println("Diferentes");
		}
		
		if( c1 == c4 ) {
			System.out.println("Iguales");
		} else {
			System.out.println("Diferentes");
		}
		
		Cliente c5 = new Cliente(1L,"Chiquito", "Tokyo", "555123456");
		Cliente c6 = new Cliente(1L,"Chiquito", "Malaga", "555123456");		
		
	}
	
}

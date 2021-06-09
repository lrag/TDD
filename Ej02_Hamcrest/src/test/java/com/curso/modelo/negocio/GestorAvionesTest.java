package com.curso.modelo.negocio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.curso.modelo.entidad.Avion;


/*
- assertThat  
 
- is 
- not 
- nullValue 
- empty 
- endsWith 
- startsWith 
- hasItem 
- hasItems 
- hasProperty
*/

public class GestorAvionesTest {

	private GestorAviones gestorAviones;

	@BeforeEach
	void setUp() throws Exception {
		gestorAviones = new GestorAviones();
	}

	@Test
	void despuesDeInsertarElAvionTendraId() {
		
		
		Avion a = new Avion(null, "Messerschmitt", "BF 109", 1936);
		try {
			gestorAviones.insertar(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//JUnit                 
		//assertNotNull(a.getId());		
		
		assertThat(a.getId(), is(not(nullValue())));		                        
	}

	@Test
	void insertarTest() {
		
		Integer antes = gestorAviones.listar().size();
		Avion a = new Avion(null, "Supermarine", "Spitfire", 1938);
		try {
			gestorAviones.insertar(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer despues = gestorAviones.listar().size();
						
		//JUnit
		//assertEquals(despues, new Integer(antes+1));
		
		assertThat(despues, is(new Integer(antes+1)));		
	}

	@Test
	void buscarTest() {
		
		final String FABRICANTE = "fabricante";
		final String MODELO = "modelo";
		final Integer YEAR = 2020;
		
		Avion a1 = new Avion(null, FABRICANTE, MODELO, YEAR);
		try {
			gestorAviones.insertar(a1); //Este no es el método que estamos probando
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Avion a2 = gestorAviones.buscar(a1.getId()); //Este si es el método que estamos probando
		
		//JUnit 5
		assertAll( () -> assertEquals(a2.getId(), a1.getId()),
				   () -> assertEquals(a2.getFabricante(), FABRICANTE),
				   () -> assertEquals(a2.getModelo(), MODELO),
				   () -> assertEquals(a2.getYear(), YEAR) );
		
		//Hamcrest
		assertThat(a2, allOf( hasProperty("id", is(a1.getId())),
							  hasProperty("fabricante", is(FABRICANTE)),
							  hasProperty("modelo", is(MODELO)),
							  hasProperty("year", is(YEAR))
							)
				  );
	
		//Con un matcher personalizado
		assertThat(a2, new esIgual(a1));
		
		//Hamcrest no tiene soft assertions :(
		
	}

	@Test
	void testListar() {

		List<Avion> aviones = gestorAviones.listar();
		
		//assertThat(aviones, hasSize(3));
		//assertThat(aviones, contains(objetos));
		assertThat(aviones,  is(not(empty())));
	
		//Con junit
		//Assertions.assertTrue(aviones.size()>0);
		
	}

	@Test
	public void listarPorFabricanteTest() {		
		final String FABRICANTE = "McDonell Douglas";
		List<Avion> aviones = gestorAviones.listarPorFabricante(FABRICANTE);
		assertThat(aviones, everyItem( hasProperty("fabricante", is(FABRICANTE) )));
	}

}

class esIgual extends BaseMatcher<Avion>{

	private Avion avion;
	
	public esIgual(Avion avion) {
		super();
		this.avion = avion;
	}

	@Override
	public boolean matches(Object obj) {
		Avion aAux = (Avion) obj;
		return aAux.getId().equals(avion.getId()) &&
			   aAux.getFabricante().equals(avion.getFabricante()) &&	
			   aAux.getModelo().equals(avion.getModelo()) &&	
			   aAux.getYear().equals(avion.getYear());	
	}

	@Override
	public void describeTo(Description descripcion) {
		descripcion.appendText("el avion debe ser igual ").appendValue(avion);
	}
	
}



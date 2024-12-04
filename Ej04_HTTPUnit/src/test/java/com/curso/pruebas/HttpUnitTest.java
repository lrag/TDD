package com.curso.pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebResponse;

public class HttpUnitTest {

	@Test
	public void test() throws Exception {
		WebConversation webconversation = new WebConversation();
		WebResponse response = null;
		try {
			response = webconversation.getResponse("http://www.google.com");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		/** Comprobamos que el Http Response Code es 200 */
		assertEquals(200, response.getResponseCode());
		WebForm[] formularios = response.getForms();
		/** Comprobamos que tenemos únicamente un formulario en la página */
		assertTrue(formularios.length == 1);
		/*
		 * Verificamos si en la página de google, existe un botón de búsqueda con el
		 * texto en cuestión "Buscar con Google"
		 */
		assertEquals("Buscar con Google", response.getForms()[0].getParameter("q").getControls()[0].getTitle());
	}

}

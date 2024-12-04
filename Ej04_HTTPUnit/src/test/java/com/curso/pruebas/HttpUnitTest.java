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
		/** Comprobamos que tenemos �nicamente un formulario en la p�gina */
		assertTrue(formularios.length == 1);
		/*
		 * Verificamos si en la p�gina de google, existe un bot�n de b�squeda con el
		 * texto en cuesti�n "Buscar con Google"
		 */
		assertEquals("Buscar con Google", response.getForms()[0].getParameter("q").getControls()[0].getTitle());
	}

}

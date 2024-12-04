package com.curso.pruebas;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlDivision;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class _02_FormularioTest {

		
	@Test
	public void testSimpleForm() throws Exception {
        
		try (final WebClient webClient = new WebClient()) {
	        final HtmlPage page = webClient.getPage("http://localhost:8080/Ej00_WEB/index.html");

	        HtmlForm form = page.getFormByName("formulario");
	        HtmlTextInput txtNombre = form.getInputByName("nombre");	        
	        HtmlTextInput txtMail   = form.getInputByName("mail");		            
	
			String nombre = "Ringo Starr";
			String mail   = "ringo@starr.com";
			
			txtNombre.setValueAttribute(nombre);
			txtMail.setValueAttribute(mail);
			
			HtmlSubmitInput btnSubmit = (HtmlSubmitInput) page.getElementById("btnEnviar");
			HtmlPage paginaDestino = btnSubmit.click();
		
	        HtmlDivision divNombre = (HtmlDivision) paginaDestino.getElementById("nombre");	        
	        HtmlDivision divMail   = (HtmlDivision) paginaDestino.getElementById("mail");
	        
	        //Los asertos ya son asertos normales de JUnit (o lo que hayamos puesto)
	        Assertions.assertEquals(nombre, divNombre.getTextContent().trim());
	        Assertions.assertEquals(mail  , divMail.getTextContent().trim());

		}
	
    } 
	
}



package com.curso.pruebas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

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
	        Assertions.assertEquals(nombre, divNombre.getTextContent());
	        Assertions.assertEquals(mail  , divMail.getTextContent());

		}
	
    } 
	
}



package com.curso.pruebas;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class _01_HtmlUnitTest {

	@Test
	public void test() throws Exception {
		
		try (final WebClient webClient = new WebClient()) {
	        final HtmlPage page = webClient.getPage("http://www.wikipedia.es");
	        
	        Assertions.assertEquals(200, page.getWebResponse().getStatusCode());
	        Assertions.assertEquals("Wikipedia, la enciclopedia libre", page.getTitleText());
	        
	        /*
	        <input type="search" name="search" placeholder="Buscar en Wikipedia" autocapitalize="sentences" title="Buscar en este wiki [f]" accesskey="f" id="searchInput"/>
			<input type="hidden" name="title" value="Especial:Buscar"/>
			<input type="submit" name="fulltext" value="Buscar" title="Busca páginas con este texto." id="mw-searchButton" class="searchButton mw-fallbackSearchButton"/>
			<input type="submit" name="go" value="Ir" title="Ir a la página con este nombre exacto si existe" id="searchButton" class="searchButton"/> 
	        */
	        
	        HtmlTextInput txtBusqueda = (HtmlTextInput) page.getElementByName("search");	
	        System.out.println(txtBusqueda);
	        txtBusqueda.setValueAttribute("TDD");
	        
	        HtmlSubmitInput btnBuscar = (HtmlSubmitInput) page.getElementByName("go");
	        HtmlPage resultadoBusqueda = btnBuscar.click();	 
	        
	        //System.out.println(resultadoBusqueda.asXml());
	        	        
	        List<DomNode> nodos = resultadoBusqueda.getByXPath("//*[contains(text(),'Desarrollo guiado por pruebas')]");
	        System.out.println(nodos);
	        Assertions.assertTrue(nodos.size()>0);
	        
		}   	

	}

}

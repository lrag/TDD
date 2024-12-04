package com.curso.pruebas;

import java.util.List;

import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class _01_HtmlUnitTest {
	
	@Test
	public void test() throws Exception {
		
		//El objeto webClient simula ser un navegador
		try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {

			//HtmlPAge representa a la página recibida
	        final HtmlPage page = webClient.getPage("https://www.baeldung.com/");
			
			
			
			/*
			System.out.println("FU");
			//HtmlPAge representa a la página recibida
	        final HtmlPage page = webClient.getPage("http://www.wikipedia.es");
	        System.out.println("FU");
	        
	        Assertions.assertEquals(200, page.getWebResponse().getStatusCode());
	        Assertions.assertEquals("Wikipedia, la enciclopedia libre", page.getTitleText());
	        
	        /*
			<form action="/w/index.php" id="searchform">
				<div id="simpleSearch" data-search-loc="header-navigation">
					<input type="search" name="search" placeholder="Buscar en Wikipedia" autocapitalize="sentences" title="Buscar en este wiki [f]" accesskey="f" id="searchInput"/>
					<input type="hidden" name="title" value="Especial:Buscar"/>
					<input type="submit" name="fulltext" value="Buscar" title="Busca páginas con este texto." id="mw-searchButton" class="searchButton mw-fallbackSearchButton"/>
					<input type="submit" name="go" value="Ir" title="Ir a la página con este nombre exacto si existe" id="searchButton" class="searchButton"/>
				</div>
			</form>
	        *
	        
	        //Cualquiera de los dos nos sirve:
	        //HtmlTextInput txtBusqueda = (HtmlTextInput) page.getElementByName("search");	
	        HtmlTextInput txtBusqueda = (HtmlTextInput) page.getElementById("searchInput");	
	        System.out.println(txtBusqueda);
	        txtBusqueda.setValueAttribute("TDD");
	        
	        HtmlSubmitInput btnBuscar = (HtmlSubmitInput) page.getElementByName("go");
	        //HtmlUnit espera pacientemente a que se cargue la respuesta 
	        HtmlPage resultadoBusqueda = btnBuscar.click();	 
	        
	        //El contenido de la página:
	        //System.out.println(resultadoBusqueda.asXml());
	        	  
	        //<h1 id="firstHeading" class="firstHeading" >Desarrollo guiado por pruebas</h1>
	        List<DomNode> nodos = resultadoBusqueda.getByXPath("//*[contains(text(),'Desarrollo guiado por pruebas')]");
	        System.out.println(nodos);
	        Assertions.assertTrue(nodos.size()>0);
	        */
	        
		}   	

	}

}

package com.curso.pruebas;

import java.util.List;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTable;
import org.htmlunit.html.HtmlTableRow;
import org.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class _03_CestaTest {

	@Test
	public void test() throws Exception {
		
		try (final WebClient webClient = new WebClient()) {
	        final HtmlPage page = webClient.getPage("http://localhost:8080/Ej00_WEB/SVCesta");
	        
	        Assertions.assertEquals(200, page.getWebResponse().getStatusCode());
	        Assertions.assertEquals("Cesta", page.getTitleText());
	        
	        final HtmlForm form = page.getFormByName("formulario");
	        final HtmlTextInput txtProducto = form.getInputByName("producto");	        
	        final HtmlTextInput txtCantidad = form.getInputByName("cantidad");	        
	        System.out.println(txtProducto);
	        System.out.println(txtCantidad);
	        //Estos asertos no son necesarios: si no se encuentra algo al buscarlo en la p�gina HtmlUnit lanza excepci�n
	        //Assertions.assertNotNull(txtProducto,"El formulario debe tener un campo 'producto'");
	        //Assertions.assertNotNull(txtCantidad,"El formulario debe tener un campo 'cantidad'");
	    }		
		
	}

	@Test
	public void addDetalleTest() throws Exception {
		
		try (final WebClient webClient = new WebClient()) {
	        final HtmlPage page = webClient.getPage("http://localhost:8080/Ej00_WEB/SVCesta");

	        HtmlForm form = page.getFormByName("formulario");
	        HtmlTextInput txtProducto = form.getInputByName("producto");	        
	        HtmlTextInput txtCantidad = form.getInputByName("cantidad");		            
	
			String producto = "producto1";
			String cantidad = "10.0";
			
			txtProducto.setValueAttribute(producto);
			txtCantidad.setValueAttribute(cantidad);
			
			HtmlSubmitInput btnSubmit = (HtmlSubmitInput) page.getElementById("btnEnviar");
			HtmlPage nuevaPagina = btnSubmit.click();
		
			HtmlTable table = nuevaPagina.getHtmlElementById("tablaDetalles");
			List<HtmlTableRow> filas = table.getRows();
			HtmlTableRow row = filas.get(filas.size()-1);
			Assertions.assertEquals(producto, row.getCell(0).getTextContent());
			Assertions.assertEquals(cantidad, row.getCell(1).getTextContent());

		}
		
	}
	
	
}

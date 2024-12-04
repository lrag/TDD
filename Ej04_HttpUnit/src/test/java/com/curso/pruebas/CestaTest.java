package com.curso.pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
//import org.xml.sax.SAXException;

import com.meterware.httpunit.TableRow;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

public class CestaTest {

	@Test
	public void test() throws Exception {
		
		WebConversation webconversation = new WebConversation();
		WebResponse response = webconversation.getResponse("http://localhost:8080/Ej00_WEB/SVCesta");
		assertEquals(200, response.getResponseCode());
		WebForm[] formularios = response.getForms();
		assertTrue(formularios.length == 1);

		assertNotNull(response.getForms()[0].getParameter("producto"), "El formulario debe tener un campo 'producto'");
		assertNotNull(response.getForms()[0].getParameter("cantidad"), "El formulario debe tener un campo 'cantidad'");
	}

	@Test
	public void addDetalleTest() throws Exception {

		WebConversation webConversation = new WebConversation();
		WebResponse formResponse = webConversation.getResponse("http://localhost:8080/Ej00_WEB/SVCesta");
		WebForm[] forms = formResponse.getForms();

		assertEquals(200, formResponse.getResponseCode(), "El ResponseCode debe ser 200");
		assertNotNull(forms, "La pagina devuelta por la URL debe contener algun formulario");
		assertTrue(forms.length == 1, "La pagina devuelta por la URL solo debe contener un formulario");

		String producto = "producto1";
		Double cantidad = 10d;

		formResponse.getForms()[0].setParameter("producto", producto);
		formResponse.getForms()[0].setParameter("cantidad", cantidad.toString());

		WebResponse destino = formResponse.getForms()[0].submit();
		
		WebTable[] tables = destino.getTables();
		assertNotNull(tables, "La pagina que nos devuelve el submit debe contener alguna tabla");

		WebTable table = tables[1];

		System.out.println(table);

		assertEquals(producto, table.getCellAsText(1, 0),
				"El campo producto debe ser el enviado, en este caso: " + producto);
		assertEquals(cantidad.toString(), table.getCellAsText(1, 1),
				"El campo cantidad debe ser el enviado, en este caso: " + cantidad);

		//A�adimos un segundo producto
		producto = "producto2";
		cantidad = 20d;

		formResponse.getForms()[0].setParameter("producto", producto);
		formResponse.getForms()[0].setParameter("cantidad", cantidad.toString());

		destino = formResponse.getForms()[0].submit();

		tables = destino.getTables();
		assertNotNull(tables, "La pagina que nos devuelve el submit debe contener alguna tabla");

		table = tables[1];
		TableRow[] filas = table.getRows();
		System.out.println("==========");
		TableRow fila = filas[filas.length-1];

		System.out.println(table);
		
		
		
	}
	
}

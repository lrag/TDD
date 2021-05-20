package com.curso.pruebas;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.AuthorizationRequiredException;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

public class FormularioTest {

	@Test
	public void test() throws IOException, SAXException {
		
		WebConversation webconversation = new WebConversation();
		WebResponse response = webconversation.getResponse("http://localhost:8080/Ej00_WEB/index.html");
		
		System.out.println(response);
		System.out.println(response.getText());
		
		assertEquals(200, response.getResponseCode());
		WebForm[] formularios = response.getForms();
		assertTrue(formularios.length == 1);
				
		//JUnit 4
		//assertNotNull("El formulario debe tener un campo 'nombre'",response.getForms()[0].getParameter("nombre"));
		//assertNotNull("El formulario debe tener un campo 'mail'",response.getForms()[0].getParameter("mail"));
		
		//assertNotNull(response.getForms()[0].getParameter("nombre"),"El formulario debe tener un campo 'nombre'");
		//assertNotNull(response.getForms()[0].getParameter("mail"),"El formulario debe tener un campo 'mail'");
	
	}
	
	@Test
	public void testSimpleForm() throws AuthorizationRequiredException, IOException, SAXException {
        
		//
		//Accedemos a index.html
		//
		WebConversation webConversation = new WebConversation();
		WebResponse formResponse = webConversation.getResponse("http://localhost:8080/Ej00_WEB/index.html");	
		
		assertEquals(200, formResponse.getResponseCode(), "El ResponseCode debe ser 200");

		//
		//Enviamos el formulario de la pagina de inicio
		//
		WebForm[] forms = formResponse.getForms();
		assertNotNull(forms, "La pagina devuelta por la URL debe contener algun formulario");
		assertTrue(forms.length == 1, "La pagina devuelta por el servidor solo debe contener un formulario");

		String nombre =  "nombre";
		String mail =  "correo@electronico.es";
		
		formResponse.getForms()[0].setParameter("nombre",nombre);
		formResponse.getForms()[0].setParameter("mail", mail);

		WebResponse destino = formResponse.getForms()[0].submit();

		//
		//Comprobamos que la respuesta al submit es correcta
		//
		assertEquals("Datos enviados", destino.getTitle(), "El titulo de la pagina que nos devuelve el submit del formulario debe ser 'Datos enviados'");
		
		HTMLElement[] list =  destino.getElementsByTagName("div");
		assertNotNull(list, "La pagina devuelta por la URL debe contener algun tag div");
		
		for (HTMLElement htmlElement : list) {
			if (htmlElement.getID().equals("nombre")){
				assertEquals(nombre,htmlElement.getText(), "El campo nombre debe ser el enviado, en este caso: " + nombre);
			} else if (htmlElement.getID().equals("mail")){
				assertEquals(mail,htmlElement.getText(), "El campo mail debe ser el enviado, en este caso: " + mail);
			} else {
				fail("Existe algun div que no es el de 'nombre' o el 'mail'");
			}
		}

		//
		//Buscamos los datos nombre y mail, esta vez en la tabla
		//
		WebTable[] tables = destino.getTables();
		assertNotNull(tables, "La pagina que nos devuelve el submit debe contener alguna tabla");
		
		WebTable table = tables[0];
		assertEquals(nombre,table.getCellAsText(0, 1), "El campo nombre debe ser el enviado, en este caso: " + nombre);
		assertEquals(mail,table.getCellAsText(1, 1), "El campo mail debe ser el enviado, en este caso: " + mail);

		//
		//Buscamos el enlace de retorno a index.html
		//
		WebLink[] links = destino.getLinks();
		assertNotNull(links, "La pagina que nos devuelve el submit debe contener algun link");
		
		WebLink link = destino.getLinkWith("inicio");
		assertNotNull(links, "La pagina que nos devuelve el submit debe contener algun link con texto 'inicio'");
				
		//
		//Seguimos el enlace que dirige a index.jsp
		//
		WebResponse index = link.click();
		
		//
		//Buscamos en index.html los campos del formulario
		//
		WebForm[] forms2 = index.getForms();
		
		assertNotNull(forms2, "La pagina devuelta por el link debe contener algun formulario");
		assertTrue(forms2.length == 1, "La pagina devuelta por el link solo debe contener un formulario");
		
		WebForm formulario = index.getForms()[0];
		
		System.out.println("=========================================");
		
		assertNotNull(formulario.getParameter("nombre"), "El formulario debe tener un campo 'nombre'");
		assertNotNull(formulario.getParameter("mail"), "El formulario debe tener un campo 'mail'");
	
    } 
	
}



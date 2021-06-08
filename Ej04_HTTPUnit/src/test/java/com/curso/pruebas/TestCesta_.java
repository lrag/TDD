package com.curso.pruebas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.meterware.httpunit.WebClient;

public class TestCesta_ {

	
	@Test
	public void homePage() throws Exception {
	    try (final WebClient webClient = new WebClient()) {
	        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	        Assertions.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

	        final String pageAsXml = page.asXml();
	        Assertions.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

	        final String pageAsText = page.asText();
	        Assertions.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
	    }
	}
	
}

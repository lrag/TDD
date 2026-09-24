package com.curso;

import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

// @ServletComponentScan registra los @WebServlet/@WebFilter de com.curso.controlador
// tal cual estaban en Ej06_Selenium_4, sin tocar una línea de esas clases.
//
// No extiende SpringBootServletInitializer: eso solo hace falta para desplegar el
// .war en un Tomcat externo. Aquí la app se arranca directamente desde este main().
@SpringBootApplication
@ServletComponentScan
public class Ej06SeleniumWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ej06SeleniumWebApplication.class, args);
	}

	// Al no usar spring-boot-starter-web (sin Spring MVC) Spring Boot no registra
	// ningún servlet para servir los estáticos de src/main/webapp (index.html, css, js...).
	// Damos de alta el DefaultServlet de Tomcat en "/", igual que hace cualquier Tomcat
	// standalone por defecto. Los @WebServlet ("/SVLogin", "/seguro/SVClientes") y el
	// JspServlet (*.jsp) siguen ganando porque sus mapeos son más específicos.
	@Bean
	public ServletRegistrationBean<DefaultServlet> defaultServletRegistration() {
		ServletRegistrationBean<DefaultServlet> registration = new ServletRegistrationBean<>(new DefaultServlet(), "/");
		registration.setLoadOnStartup(1);
		return registration;
	}

}

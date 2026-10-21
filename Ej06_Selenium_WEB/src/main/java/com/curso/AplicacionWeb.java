package com.curso;

import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

//
//http://localhost:8080/Ej06_Selenium_4/index.html
//


@SpringBootApplication
@ServletComponentScan
public class AplicacionWeb {

	public static void main(String[] args) {
		SpringApplication.run(AplicacionWeb.class, args);
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

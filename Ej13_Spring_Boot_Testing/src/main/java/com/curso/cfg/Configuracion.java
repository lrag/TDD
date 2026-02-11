package com.curso.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configuracion {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}

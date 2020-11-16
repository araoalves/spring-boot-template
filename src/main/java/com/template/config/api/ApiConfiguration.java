package com.template.config.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

	@Bean(name="teste_bean")
	public String testeBean(){
		return "teste_bean";
	}
	
}

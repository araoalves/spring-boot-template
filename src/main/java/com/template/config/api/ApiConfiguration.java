package com.template.config.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.template.model.ERole;
import com.template.model.Role;
import com.template.model.payload.request.SignupRequest;
import com.template.repository.RoleRepository;
import com.template.service.JwtUserDetailsService;

@Configuration
public class ApiConfiguration {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Bean(name="teste_bean")
	public String testeBean(){
		return "teste_bean";
	}
	
	@Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
	
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	roleRepository.save(new Role(1,ERole.ROLE_ADMIN));
	    	roleRepository.save(new Role(2,ERole.ROLE_MODERATOR));
	    	roleRepository.save(new Role(3,ERole.ROLE_USER));
	    	userDetailsService.loadUser(signUpRequest());
	      };
	 }
	
	private @Valid SignupRequest signUpRequest() {		
		Set<String> strings = new LinkedHashSet<>();
		strings.add("ROLE_ADMIN");
		return new SignupRequest("afarias","afarias@gmail.com", strings,"123456");
	}

	
}

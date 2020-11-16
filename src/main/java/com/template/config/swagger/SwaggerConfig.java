package com.template.config.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	@Bean
	public Docket productApi() {
	return new Docket(DocumentationType.SWAGGER_2)

	.select()
    .apis(RequestHandlerSelectors.any())
    .paths(Predicates.not(PathSelectors.regex("/error")))
    .paths(PathSelectors.any())
    .build()
    .securitySchemes(Lists.newArrayList(apiKey()))
    .apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
		.title("Spring Boot REST API")
		.description("\"Spring Boot REST API\"")
		.version("1.0.0")
		.license("Apache License Version 2.0")
		.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
		.contact(new Contact("John Thompson", "https://springframework.guru/about/", "john@springfrmework.guru"))
		.build();
	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	private ApiKey apiKey() {
	    return new ApiKey("apiKey", "Authorization", "header");
	    }

	    private SecurityContext securityContext() {
	    return SecurityContext.builder().securityReferences(defaultAuth())
	        .forPaths(PathSelectors.any()).build();
	    }

	    private List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope = new AuthorizationScope(
	        "global", "accessEverything");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    return Arrays.asList(new SecurityReference("apiKey",
	        authorizationScopes));
	    }
	
}

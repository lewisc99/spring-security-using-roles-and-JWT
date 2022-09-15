package com.springsecurityroles.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	
	
	@Bean
	 public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.springsecurityroles.controller")) 
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
               
    }



	private ApiInfo apiDetails()
	{
		return new ApiInfo(
				"Spring Security roles JWT",
				"Simple Project using spring security and "
				+ "authentication and authoritization using JWT",
				"1.0",
				"Free to Use",
				new springfox.documentation.service.Contact("Lewis Carlos", "https://www.linkedin.com/in/luiz-carlos-b50693173/", "lewiscontato99@gmail.com"),
				"Api License","",
			 Collections.emptyList()
				);
	}
	
	
}

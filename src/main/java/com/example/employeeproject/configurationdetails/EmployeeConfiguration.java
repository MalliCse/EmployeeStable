package com.example.employeeproject.configurationdetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
 @EnableSwagger2
//@Profile("dev")
public class EmployeeConfiguration {
	
	// Package Level configuration
	/*
	 * @Bean public Docket productApi() { return new
	 * Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.basePackage(
	 * "com.example.employeeproject.controller"))
	 * .paths(PathSelectors.ant("/api/v1/saveAllEmployeeDetails"))
	 * .build().globalResponseMessage(RequestMethod.GET,
	 * getCustomizedResponseMessages() ); }
	 */
	
	// Class Level Configuration
	
	  @Bean public Docket productApi() { return new
	  Docket(DocumentationType.SWAGGER_2).select()
	  .apis(RequestHandlerSelectors.withClassAnnotation(ShowAPi.class))
	  .paths(PathSelectors.any()) .build().globalResponseMessage(RequestMethod.GET,
	  getCustomizedResponseMessages() ); }
	 
	// Method Level Configuration
	
	/*
	 * @Bean public Docket productApi() { return new
	 * Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.withMethodAnnotation(ShowAPi.class))
	 * .paths(PathSelectors.any()) .build().globalResponseMessage(RequestMethod.GET,
	 * getCustomizedResponseMessages() ).apiInfo(apiInfo()); }
	 */
	 private List<ResponseMessage> getCustomizedResponseMessages(){
	        List<ResponseMessage> responseMessages = new ArrayList<>();
	        responseMessages.add(new ResponseMessageBuilder().code(500).message("Internal Server Error").build());
	        responseMessages.add(new ResponseMessageBuilder().code(403).message("ForBidden").build());
	        return responseMessages;
	    }
	 
	 private ApiInfo apiInfo() {
	        return new ApiInfoBuilder().title("Employee API")
	                .description("Employee API")
	                .termsOfServiceUrl("http://localhost:9098")
	                .version("1.0").build();
	    }
}

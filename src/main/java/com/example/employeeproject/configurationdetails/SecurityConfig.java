package com.example.employeeproject.configurationdetails;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	        .withUser("Malli").password("{noop}Malli@12345").roles("USER");
	  }
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.httpBasic().and()
	        .authorizeRequests().antMatchers("/api/v1/**")
	        .hasRole("USER")
	        .and()
	        .csrf()
	        .disable()
	        .formLogin()
	        .disable();
	  }
	}
	



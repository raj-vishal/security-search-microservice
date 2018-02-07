package com.optus.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// Authentication
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("optus").password("candidates")
		.roles("USER", "ADMIN");
	}

	// Authorization
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
		.antMatchers("/counter-api/").hasRole("USER")
		.antMatchers("/**").hasRole("ADMIN").and()
		.csrf().disable();
	}

}
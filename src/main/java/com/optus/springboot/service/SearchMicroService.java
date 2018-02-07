package com.optus.springboot.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.optus.springboot.search"}) // redundant if the controller is in the same package. already covered by above @
public class SearchMicroService {

	public static void main(String[] args) {
		SpringApplication.run(SearchMicroService.class, args);
	}
}

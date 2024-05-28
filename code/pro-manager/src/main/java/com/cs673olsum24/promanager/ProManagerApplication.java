package com.cs673olsum24.promanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class ProManagerApplication {	
	private static final Logger LOGGER = LogManager.getLogger(ProManagerApplication.class);		
	public static void main(String[] args) {
		SpringApplication.run(ProManagerApplication.class, args);
		LOGGER.info("Application Started");	
	}		 
}
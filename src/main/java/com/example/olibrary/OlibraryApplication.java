package com.example.olibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OlibraryApplication {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(OlibraryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OlibraryApplication.class, args);
	}

}

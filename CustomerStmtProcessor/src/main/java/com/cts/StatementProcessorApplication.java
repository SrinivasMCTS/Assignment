package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author 437977 Spring boot main app
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.cts")
public class StatementProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatementProcessorApplication.class, args);
	}
}

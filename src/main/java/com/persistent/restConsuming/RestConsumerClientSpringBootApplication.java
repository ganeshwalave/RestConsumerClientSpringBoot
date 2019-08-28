package com.persistent.restConsuming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.persistent.*"})
public class RestConsumerClientSpringBootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RestConsumerClientSpringBootApplication.class, args);
	}

}

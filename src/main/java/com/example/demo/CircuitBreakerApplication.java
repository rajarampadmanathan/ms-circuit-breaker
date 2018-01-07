package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@EnableCircuitBreaker
@RestController
@EnableEurekaClient
public class CircuitBreakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerApplication.class, args);
	}
	
	@HystrixCommand(fallbackMethod="sayHelloAlt")
	@GetMapping(path="/user/{userId}")
	public String sayHello( @PathVariable("userId") int userId){
		if(userId==10) {
			throw new RuntimeException();
		}
		return "Hello from instance CB "+userId;
	}
	
	public String sayHelloAlt(int name) {
		return "CB instnace temporarily unavailable. Sorry for the inconvienience "+name;
	}
}

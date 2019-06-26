package com.unicom.test.microservice.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableEurekaClient
@EnableScheduling
@SpringBootApplication
public class ProducerApp {
	public static void main(String[] args) {
		SpringApplication.run(ProducerApp.class, args);
	}
}

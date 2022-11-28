package com.training.microservices.app.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.training.microservices.commons.tests.models.entity"})
public class MicroservicesTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesTestsApplication.class, args);
	}

}

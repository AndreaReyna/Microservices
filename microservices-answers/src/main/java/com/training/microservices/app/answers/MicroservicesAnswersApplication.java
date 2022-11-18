package com.training.microservices.app.answers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.training.microservices.app.answers.models.entity", 
	"com.training.microservices.commons.tests.models.entity"})
public class MicroservicesAnswersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesAnswersApplication.class, args);
	}

}

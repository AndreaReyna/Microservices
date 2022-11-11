package com.training.microservices.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.training.microservices.commons.students.models.entity"})
public class MicroservicesUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesUsersApplication.class, args);
	}

}

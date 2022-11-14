package com.training.microservices.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.training.microservices.commons.students.models.entity",
"com.training.microservices.courses.models.entity",
"com.training.microservices.commons.tests.models.entity"})
public class MicroservicesCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesCoursesApplication.class, args);
	}

}

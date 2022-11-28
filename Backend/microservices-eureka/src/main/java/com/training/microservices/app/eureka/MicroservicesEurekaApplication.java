package com.training.microservices.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroservicesEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesEurekaApplication.class, args);
	}

}

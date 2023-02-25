package com.snort.inteli.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaAppServer {

	public static void main(String[] args) {
		SpringApplication.run(EurekaAppServer.class, args);
		System.out.println("EurekaAppServer is running...");
	}

}

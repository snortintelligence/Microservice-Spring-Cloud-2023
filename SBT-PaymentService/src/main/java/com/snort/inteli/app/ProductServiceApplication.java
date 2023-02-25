package com.snort.inteli.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
		System.out.println("ProductServiceApplication runing..");
	}

//	@LoadBalanced
//	@Bean
//	public WebClient init(){
//		return WebClient.builder().baseUrl("https://dummyjson.com")
//				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.build();
//	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}

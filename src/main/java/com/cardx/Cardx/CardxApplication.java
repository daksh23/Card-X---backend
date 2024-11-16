package com.cardx.Cardx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@PropertySource("classpath:cardx.properties")
public class CardxApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardxApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}

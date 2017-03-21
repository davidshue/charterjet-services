package com.charter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CharterjetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharterjetsApplication.class, args);
	}
}

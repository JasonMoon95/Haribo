package com.MyFirstApp.Haribo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class HariboApplication {

	public static void main(String[] args) {
		SpringApplication.run(HariboApplication.class, args);
	}
}

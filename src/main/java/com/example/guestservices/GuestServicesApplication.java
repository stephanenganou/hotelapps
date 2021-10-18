package com.example.guestservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer // it's all it takes to make this resource server: OAuth enabled.
public class GuestServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestServicesApplication.class, args);
	}

}

package com.fitproject.cravefit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CravefitdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CravefitdashboardApplication.class, args);
	}

}

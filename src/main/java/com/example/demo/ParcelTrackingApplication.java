package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@SpringBootApplication
public class ParcelTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcelTrackingApplication.class, args);
	}
}


            

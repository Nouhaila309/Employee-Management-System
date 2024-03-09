package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EntityScan(basePackages = "com.example.demo.entity")
public class MySecondTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySecondTaskApplication.class, args);
	}
}

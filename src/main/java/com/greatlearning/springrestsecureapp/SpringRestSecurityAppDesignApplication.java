package com.greatlearning.springrestsecureapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringRestSecurityAppDesignApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestSecurityAppDesignApplication.class, args);
		log.info("Application Main is called and executed");

	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Application is Up and Running");

	}
}

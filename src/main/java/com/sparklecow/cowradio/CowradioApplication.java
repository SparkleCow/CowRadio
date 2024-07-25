package com.sparklecow.cowradio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CowradioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CowradioApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(){
		return args -> System.out.println("Hello world!");
	}
}

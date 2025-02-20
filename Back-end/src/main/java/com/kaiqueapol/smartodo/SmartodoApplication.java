package com.kaiqueapol.smartodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="TaskManagerAPI", version = "1", description = "Documentation of Task Manager API"))
public class SmartodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartodoApplication.class, args);
	}

}

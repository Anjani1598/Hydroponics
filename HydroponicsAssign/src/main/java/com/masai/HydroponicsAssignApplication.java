package com.masai;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@CrossOrigin(origins = "*")
public class HydroponicsAssignApplication {

	public static void main(String[] args) {
		SpringApplication.run(HydroponicsAssignApplication.class, args);
	}

}

package com.thinkxfactor.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

public class SpringdemoApplication {

	public static void main(String[] args) {
		System.out.println(args);
		SpringApplication.run(SpringdemoApplication.class, args);
	}

}

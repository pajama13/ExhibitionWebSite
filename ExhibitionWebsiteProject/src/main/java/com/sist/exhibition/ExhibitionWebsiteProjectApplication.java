package com.sist.exhibition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages={"com.sist.exhibition.controller","com.sist.exhibition.dao","com.sist.exhibition.entity","com.sist.exhibition.rest"})
@SpringBootApplication
public class ExhibitionWebsiteProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExhibitionWebsiteProjectApplication.class, args);
	}

}

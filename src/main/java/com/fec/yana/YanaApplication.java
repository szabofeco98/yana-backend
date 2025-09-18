package com.fec.yana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class YanaApplication {

	public static void main(String[] args) {
		SpringApplication.run(YanaApplication.class, args);
	}

}

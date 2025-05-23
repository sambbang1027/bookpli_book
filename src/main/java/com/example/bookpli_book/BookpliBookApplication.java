package com.example.bookpli_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.example.bookpli_book")
public class BookpliBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookpliBookApplication.class, args);
	}

}

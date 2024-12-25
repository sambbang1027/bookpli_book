package com.example.bookpli_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.example.bookpli_book")
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class BookpliBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookpliBookApplication.class, args);
	}

}

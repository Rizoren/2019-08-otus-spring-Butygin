package ru.otus.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
public class LibraryApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext app = SpringApplication.run(LibraryApplication.class, args);
	}

}

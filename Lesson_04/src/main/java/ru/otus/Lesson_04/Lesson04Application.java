package ru.otus.Lesson_04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson04Application {

	private static Lesson04Properties lesson04Properties;

	public static void main(String[] args) {

		lesson04Properties = SpringApplication.run(Lesson04Application.class, args).getBean(Lesson04Properties.class);
		System.out.println(lesson04Properties.version);
	}

}

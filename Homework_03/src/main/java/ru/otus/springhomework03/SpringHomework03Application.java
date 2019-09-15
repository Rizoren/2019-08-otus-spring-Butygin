package ru.otus.springhomework03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.springhomework03.services.ConsolePollingImpl;

@SpringBootApplication
public class SpringHomework03Application {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(SpringHomework03Application.class, args);
		ConsolePollingImpl exam = context.getBean("exam", ConsolePollingImpl.class);
		exam.run();
	}

}

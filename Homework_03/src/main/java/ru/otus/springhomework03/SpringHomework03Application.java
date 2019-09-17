package ru.otus.springhomework03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.springhomework03.services.ConsolePolling;
import ru.otus.springhomework03.services.ConsolePollingImpl;

@SpringBootApplication
public class SpringHomework03Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringHomework03Application.class, args).getBean("exam",ConsolePolling.class).run();
	}

}

package ru.otus.springhomework04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.springhomework04.services.ConsolePolling;

@SpringBootApplication
public class SpringHomework04Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringHomework04Application.class, args).getBean("exam",ConsolePolling.class).run();
	}

}

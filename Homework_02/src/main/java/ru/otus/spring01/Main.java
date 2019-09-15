package ru.otus.spring01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring01.config.AppConfig;
import ru.otus.spring01.services.ConsolePolling;

public class Main {

    public static void main(String[] args) throws Exception  {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ConsolePolling exam = context.getBean("exam", ConsolePolling.class);
        exam.run();
    }
}

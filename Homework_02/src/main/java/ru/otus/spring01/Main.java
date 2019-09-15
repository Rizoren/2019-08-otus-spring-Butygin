package ru.otus.spring01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring01.config.AppConfig;
import ru.otus.spring01.services.ConsolePollingImpl;

public class Main {

    public static void main(String[] args) throws Exception  {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ConsolePollingImpl exam = context.getBean("exam", ConsolePollingImpl.class);
        exam.run();
    }
}

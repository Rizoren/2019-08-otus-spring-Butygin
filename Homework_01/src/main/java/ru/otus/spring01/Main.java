package ru.otus.spring01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring01.dao.IPollingAnswer;
import ru.otus.spring01.dao.IPollingQuestion;
import ru.otus.spring01.services.ConsolePolling;
import ru.otus.spring01.services.IFileReader;

import java.util.function.Supplier;

public class Main {

    public static void main(String[] args)  {

        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        Supplier<IPollingAnswer> getAnswerBean = () -> context.getBean("answer", IPollingAnswer.class);
        Supplier<IPollingQuestion> getQuestionBean = () -> context.getBean("question", IPollingQuestion.class);
        IFileReader reader = context.getBean("reader", IFileReader.class);

        try {
            reader.read(getQuestionBean, getAnswerBean);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ConsolePolling exam = context.getBean("exam", ConsolePolling.class);
        exam.run();
    }
}

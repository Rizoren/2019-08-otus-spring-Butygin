package ru.otus.spring01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring01.dao.PollingAnswer;
import ru.otus.spring01.dao.PollingQuestion;
import ru.otus.spring01.services.ConsolePolling;
import ru.otus.spring01.services.QuestionnaireReader;

import java.util.function.Supplier;

public class Main {

    public static void main(String[] args)  {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Supplier<PollingAnswer> getAnswerBean = () -> context.getBean("answer", PollingAnswer.class);
        Supplier<PollingQuestion> getQuestionBean = () -> context.getBean("question", PollingQuestion.class);
        QuestionnaireReader reader = context.getBean("reader", QuestionnaireReader.class);

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

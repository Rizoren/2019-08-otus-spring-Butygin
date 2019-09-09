package ru.otus.spring01.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring01.dao.*;

import java.util.*;

public class ConsolePolling
{
    private final static String NL = System.lineSeparator();
    @Autowired
    private IOService ioService;
    @Autowired
    private PollingPerson person;

    void showQuestion(String question) {
        ioService.printMSln("pqa.question", new String[] {question} );
    }

    void showAnswers(List<PollingAnswer> answers) throws Exception {
        int i = 1;
        if (answers.size() > 0 ) {
            for (PollingAnswer answer : answers) {
                ioService.println(i + ": " + answer.getAnswer());
                i++;
            }
        }
        else {
            throw new Exception(ioService.getMS("pqa.errInvalidAnswer"));
        }
    }

    int readAnswer(List<PollingAnswer> answers) {
        //out.print("Вариант ответа: ");
        ioService.printMS("pqa.answer");
        try {
            String r = ioService.getScanner().nextLine();
            int result = (r.replaceAll("\\D","").length() > 0 ? Integer.parseInt(r.replaceAll("\\D","")) : -1);
            if (result > answers.size() || result < 1) {
                return -1;
            }
            else

            return result;
        } catch (Exception e) {
            return -1;
        }
    }

    private String getErrorMessage(int answerIndex)
    {
        switch (answerIndex) {
            case -1:
                return "pqa.errAnswer";
            default:
                return "pqa.errAnswerUnk";
        }
    }

    private void showErrorMessage(int answerIndex)
    {
        ioService.printMSln("pqa.err", new String[] {ioService.getMS(getErrorMessage(answerIndex))});
    }

    private void readPersonalInfo()
    {
        ioService.print(NL);
        ioService.printMS("pqa.name");
        person.setFirstName( ioService.getScanner().nextLine() );
        ioService.printMS("pqa.family");
        person.setSurName( ioService.getScanner().nextLine() );
    }

    public void run() throws Exception
    {
        readPersonalInfo();

        for (PollingQuestion question : person.getPollingResultImpl()) {

            ioService.print(NL);
            int answerIndex = 0;

            do {
                showQuestion(question.getQuestion());
                showAnswers(question.getAnswers());
                answerIndex = readAnswer(question.getAnswers());

                if (answerIndex < 0) {
                    showErrorMessage(answerIndex);
                }
            } while (answerIndex < 0);

            person.getPollingResultImpl().addScore(question.getAnswers().get(answerIndex - 1).getScore());
        }
        ioService.print(NL);
        ioService.printMSln("pqa.end", new String[] {person.getFirstName() + " " + person.getSurName()} );
        ioService.printMSln("pqa.score", new String[] {String.valueOf(person.getPollingResultImpl().getScore())} );
    }

    public int getScore()
    {
        return person.getPollingResultImpl().getScore();
    }
}

package ru.otus.springhomework04.services;

import org.springframework.stereotype.Service;
import ru.otus.springhomework04.dao.QuestionnaireReader;
import ru.otus.springhomework04.model.PollingAnswer;
import ru.otus.springhomework04.model.PollingPerson;
import ru.otus.springhomework04.model.PollingQuestion;

import java.util.*;

@Service
public class ConsolePollingImpl implements ConsolePolling
{
    private final static String NL = System.lineSeparator();
    private IOService ioService;
    private PollingPerson person;
    private QuestionnaireReader reader;

    public ConsolePollingImpl() {}
    public ConsolePollingImpl(PollingPerson person, QuestionnaireReader reader, IOService ioService) {
        this.person = person;
        this.reader = reader;
        this.ioService = ioService;
    }

    void read() {
        try {
            reader.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        ioService.printMS("pqa.answer");
        try {
            String r = ioService.readString();
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
        person.setFirstName( ioService.readString() );
        ioService.printMS("pqa.family");
        person.setSurName( ioService.readString() );
    }

    @Override
    public void run() throws Exception
    {
        read();
        readPersonalInfo();

        for (PollingQuestion question : person.getPollingResult()) {

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

            person.getPollingResult().addScore(question.getAnswers().get(answerIndex - 1).getScore());
        }
        ioService.print(NL);
        ioService.printMSln("pqa.end", new String[] {person.getFirstName() + " " + person.getSurName()} );
        ioService.printMSln("pqa.score", new String[] {String.valueOf(this.getScore())} );
    }

    @Override
    public int getScore()
    {
        return person.getPollingResult().getScore();
    }

    @Override
    public void PrintSummaryInfo() {
        ioService.print(NL);
        ioService.printMSln("info.person", new String[] { person.getFirstName(),  person.getSurName() } );
        ioService.printMSln("pqa.score", new String[] { String.valueOf(this.getScore()) } );
        ioService.print(NL);
    }
}

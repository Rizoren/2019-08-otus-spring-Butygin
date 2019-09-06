package ru.otus.spring01.services;

import ru.otus.spring01.dao.*;

import java.io.PrintStream;
import java.util.*;

public class ConsolePolling
{
    private final static String NL = System.lineSeparator();
    private Scanner scanner;
    private PrintStream out;
    private String firstName;
    private String surName;
    private PollingResultImpl pollingResultImpl;

    public ConsolePolling(PollingResultImpl pollingResultImpl) {
        this.out = new PrintStream(System.out);
        this.scanner = new Scanner(System.in);
        this.pollingResultImpl = pollingResultImpl;
    }

    void showQuestion(String question) {
        out.println("Вопрос: " + question);
    }

    void showAnswers(List<PollingAnswer> answers) {
        int i = 1;
        for (PollingAnswer answer : answers) {
            out.println(i + ": " + answer.getAnswer());
            i++;
        }
    }

    int readAnswer(List<PollingAnswer> answers) {
        out.print("Вариант ответа: ");

        try {
            String r = scanner.nextLine();
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
                return "Данного варианта ответа не существует";
            default:
                return "Неизвестная ошибка";
        }
    }

    private void showErrorMessage(int answerIndex)
    {
        out.println("Ошибка: " + getErrorMessage(answerIndex));
    }

    private void readPersonalInfo()
    {
        out.print(NL + "Введите ваше имя: ");
        firstName = scanner.nextLine();
        out.print("Введите вашу фамилию: ");
        surName = scanner.nextLine();
    }

    public void run()
    {
        readPersonalInfo();

        for (PollingQuestion question : pollingResultImpl) {

            out.println();
            int answerIndex = 0;

            do {
                showQuestion(question.getQuestion());
                showAnswers(question.getAnswers());
                answerIndex = readAnswer(question.getAnswers());

                if (answerIndex < 0) {
                    showErrorMessage(answerIndex);
                }
            } while (answerIndex < 0);

            pollingResultImpl.addScore(question.getAnswers().get(answerIndex - 1).getScore());
        }

        out.println(NL + "Поздравляю, " + firstName + " " + surName);
        out.println("Ваша отценка знаний: " + pollingResultImpl.getScore());
    }

    public int getScore()
    {
        return pollingResultImpl.getScore();
    }
}

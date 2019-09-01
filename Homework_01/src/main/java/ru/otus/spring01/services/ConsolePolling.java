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
    private IPollingResult pollingResult;

    public ConsolePolling(IPollingResult pollingResult) {
        this.out = new PrintStream(System.out);
        this.scanner = new Scanner(System.in);
        this.pollingResult = pollingResult;
    }

    void showQuestion(String question) {
        out.println("Вопрос: " + question);
    }

    void showAnswers(List<IPollingAnswer> answers) {
        int i = 1;
        for (IPollingAnswer answer : answers) {
            out.println(i + ": " + answer.getAnswer());
            i++;
        }
    }

    int readAnswer(List<IPollingAnswer> answers) {
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

        for (IPollingQuestion question : pollingResult) {

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

            pollingResult.addScore(question.getAnswers().get(answerIndex - 1).getScore());
        }

        out.println(NL + "Поздравляю, " + firstName + " " + surName);
        out.println("Ваша отценка знаний: " + pollingResult.getScore());
    }

    public int getScore()
    {
        return pollingResult.getScore();
    }
}

package ru.otus.spring01.dao;

import java.util.List;

public interface IPollingQuestion
{
    String getQuestion();

    void setQuestion(String question);

    List<IPollingAnswer> getAnswers();

    void addAnswer(IPollingAnswer answer);
}

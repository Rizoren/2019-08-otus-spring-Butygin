package ru.otus.spring01.dao;

import ru.otus.spring01.model.PollingQuestion;

import java.util.Iterator;

public interface PollingResult extends Iterable<PollingQuestion>
{
    void addQuestion(PollingQuestion question);

    int getScore();

    void addScore(int score);

    int size();

    Iterator<PollingQuestion> iterator();
}

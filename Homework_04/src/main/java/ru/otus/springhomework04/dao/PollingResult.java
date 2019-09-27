package ru.otus.springhomework04.dao;

import ru.otus.springhomework04.model.PollingQuestion;

import java.util.Iterator;

public interface PollingResult extends Iterable<PollingQuestion>
{
    void addQuestion(PollingQuestion question);

    int getScore();

    void addScore(int score);

    int size();

    void clear();

    Iterator<PollingQuestion> iterator();
}

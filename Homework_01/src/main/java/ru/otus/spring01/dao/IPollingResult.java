package ru.otus.spring01.dao;

import java.util.Iterator;

public interface IPollingResult extends Iterable<IPollingQuestion>
{
    void addQuestion(IPollingQuestion question);

    int getScore();

    void addScore(int score);

    int size();

    Iterator<IPollingQuestion> iterator();
}

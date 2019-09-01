package ru.otus.spring01.services;

import ru.otus.spring01.dao.IPollingAnswer;
import ru.otus.spring01.dao.IPollingQuestion;

import java.io.IOException;
import java.util.function.Supplier;

public interface IFileReader
{
    void read(Supplier<IPollingQuestion> getQuestionBean, Supplier<IPollingAnswer> getAnswerBean) throws IOException;
}
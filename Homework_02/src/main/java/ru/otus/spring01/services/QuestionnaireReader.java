package ru.otus.spring01.services;

import ru.otus.spring01.dao.PollingAnswer;
import ru.otus.spring01.dao.PollingQuestion;

import java.io.IOException;
import java.util.function.Supplier;

public interface QuestionnaireReader
{
    void read(Supplier<PollingQuestion> getQuestionBean, Supplier<PollingAnswer> getAnswerBean);
}
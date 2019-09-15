package ru.otus.spring01.dao;

import ru.otus.spring01.model.PollingAnswer;
import ru.otus.spring01.model.PollingQuestion;

import java.util.function.Supplier;

public interface QuestionnaireReader
{
    void read();
}
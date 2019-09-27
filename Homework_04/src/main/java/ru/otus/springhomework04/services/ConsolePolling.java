package ru.otus.springhomework04.services;

public interface ConsolePolling {

    void run() throws Exception;

    int getScore();

    void PrintSummaryInfo();

    void reloadReaderSrc();
}

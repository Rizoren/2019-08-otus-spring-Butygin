package ru.otus.spring01.services;

public interface IOService {

    void print (String str);
    void println (String str);

    void printMSln (String str);
    void printMSln (String str, String[] strArr);

    void printMS (String str);
    void printMS (String str, String[] strArr);

    String getMS (String str);
}

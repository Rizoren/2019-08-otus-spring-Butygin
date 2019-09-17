package ru.otus.springhomework03.services;

public interface IOService {

    String readString();

    void print (String str);
    void println (String str);

    void printMSln (String str);
    void printMSln (String str, String[] strArr);

    void printMS (String str);
    void printMS (String str, String[] strArr);

    String getMS (String str);

    String getLang();
}

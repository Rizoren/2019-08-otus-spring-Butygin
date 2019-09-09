package ru.otus.spring01.services;

import lombok.Getter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

@Getter
public class IOService {
    private ReloadableResourceBundleMessageSource messageSource;
    private Locale lc;
    private PrintStream printStream;
    private Scanner scanner;

    public IOService(String lang, String bundle) {
        this.messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(bundle);
        messageSource.setDefaultEncoding("cp1251");
        this.lc = new Locale(lang);
        this.printStream = System.out;
        this.scanner = new Scanner(System.in);
    }

    public void print (String s) { printStream.print(s); }
    public void println (String s) { printStream.println(s); }

    public void printMSln (String s) {
        printStream.println(
                messageSource.getMessage(s,null,lc)
        );
    }
    public void printMSln (String s, String[] sm) {
        printStream.println(
                messageSource.getMessage(s,sm,lc)
        );
    }

    public void printMS (String s) {
        printStream.print(
                messageSource.getMessage(s,null,lc)
        );
    }
    public void printMS (String s, String[] sm) {
        printStream.println(
                messageSource.getMessage(s,sm,lc)
        );
    }

    public String getMS (String s) {
        return messageSource.getMessage(s,null,lc);
    }
}

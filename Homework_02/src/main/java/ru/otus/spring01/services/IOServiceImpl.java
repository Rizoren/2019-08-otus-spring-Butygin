package ru.otus.spring01.services;

import lombok.Getter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

@Getter
public class IOServiceImpl implements IOService {
    private ReloadableResourceBundleMessageSource messageSource;
    private Locale locale;
    private PrintStream printStream;
    private Scanner scanner;

    public IOServiceImpl(String lang, String bundle) {
        this.messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(bundle);
        messageSource.setDefaultEncoding("cp1251");
        this.locale = new Locale(lang);
        this.printStream = System.out;
        this.scanner = new Scanner(System.in);
    }

    public void print (String str) { printStream.print(str); }
    public void println (String str) { printStream.println(str); }

    public void printMSln (String str) {
        printStream.println(
                messageSource.getMessage(str,null, locale)
        );
    }
    public void printMSln (String str, String[] strArr) {
        printStream.println(
                messageSource.getMessage(str,strArr, locale)
        );
    }

    public void printMS (String str) {
        printStream.print(
                messageSource.getMessage(str,null, locale)
        );
    }
    public void printMS (String str, String[] strArr) {
        printStream.println(
                messageSource.getMessage(str,strArr, locale)
        );
    }

    public String getMS (String str) {
        return messageSource.getMessage(str,null, locale);
    }
}

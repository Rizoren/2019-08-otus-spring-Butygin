package ru.otus.springhomework04.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.otus.springhomework04.AppProperties;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

@Getter
@Service("ioService")
public class IOServiceImpl implements IOService {
    private MessageSource messageSource;
    private Locale locale;
    private PrintStream printStream;
    private Scanner scanner;

    @Autowired
    public IOServiceImpl(AppProperties appProperties, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = new Locale(appProperties.getDefLang());
        this.printStream = System.out;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readString() {
        return this.scanner.nextLine();
    }
    @Override
    public void setLang(String lang) {
        this.locale = new Locale(lang);
    }
    @Override
    public String getLang() {
        return locale.getLanguage();
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

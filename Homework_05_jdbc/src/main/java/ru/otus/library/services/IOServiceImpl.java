package ru.otus.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service("ioService")
public class IOServiceImpl implements IOService {

    private PrintStream printStream;
    private Scanner scanner;

    @Autowired
    public IOServiceImpl() {
        this.printStream = new PrintStream(System.out);
        this.scanner = new Scanner(System.in);
    }
    @Override
    public String readString() { return this.scanner.nextLine(); }
    @Override
    public void print (String str) { printStream.print(str); }
    @Override
    public void println (String str) { printStream.println(str); }

}

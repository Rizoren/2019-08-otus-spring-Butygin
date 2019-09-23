package ru.otus.springhomework04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.springhomework04.services.ConsolePolling;
import ru.otus.springhomework04.services.IOService;

import java.util.Arrays;
import java.util.Collection;

@ShellComponent
public class ShellConsole {
    private ConsolePolling exam;
    private IOService ioService;
    private AppProperties appProperties;
    private boolean examDone = false;

    @Autowired
    public ShellConsole(IOService ioService, ConsolePolling exam, AppProperties appProperties) {
        this.exam = exam;
        this.ioService = ioService;
        this.appProperties = appProperties;
    }

    @ShellMethod(value = "Run console polling", key = {"run","start"})
    public void run() throws Exception {
        exam.run();
        examDone = true;
    }

    @ShellMethod(value = "Show summary info", key = {"ssi","show-info","info"})
    @ShellMethodAvailability(value = "ifExamDone")
    public void PrintSummaryInfo() {
        exam.PrintSummaryInfo();
    }

    @ShellMethod(value = "Set lang [ en | ru ]", key = {"set-lang", "sl"})
    public void setLang(String lang) {

        if(Arrays.stream(new String[] {"ru","en"}).anyMatch(s -> s.equals(lang))) {
            ioService.setLang(lang);
            appProperties.setDefLang(lang);
            exam.reloadReaderSrc();
        }
        else ioService.printMSln("pqa.langNotFount", new String[] {lang} );

    }

    private Availability ifExamDone() {
        return examDone == false ? Availability.unavailable("Please done the polling") : Availability.available();
    }


}

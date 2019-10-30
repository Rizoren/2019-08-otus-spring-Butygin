package ru.otus.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.todolist.service.TestSrv;

@ShellComponent
public class ShellConsole {

    private TestSrv testSrv;

    @Autowired
    public ShellConsole(TestSrv testSrv) {
        this.testSrv = testSrv;
    }

    @ShellMethod(value = "test", key = {"t","test"})
    public void showInfo() {
        testSrv.testRep();
    }

}

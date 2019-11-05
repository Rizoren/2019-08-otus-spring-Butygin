package ru.otus.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.todolist.model.Users;
import ru.otus.todolist.repository.UsersRepository;
import ru.otus.todolist.service.TestSrv;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@ShellComponent
public class ShellConsole {

    private TestSrv testSrv;
    private UsersRepository usersRepository;

    @Autowired
    public ShellConsole(TestSrv testSrv, UsersRepository usersRepository) {
        this.testSrv = testSrv;
        this.usersRepository = usersRepository;
    }

    @ShellMethod(value = "test", key = {"t","test"})
    public void showInfo() {
        testSrv.testRep();
    }

    @ShellMethod(value = "test", key = {"t2","test2"})
    public void test() {
        List<Users> users = usersRepository.findAll();
        users.forEach(u -> System.out.println(u.toString()));
    }
}

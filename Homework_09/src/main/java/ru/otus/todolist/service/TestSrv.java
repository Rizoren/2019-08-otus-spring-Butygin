package ru.otus.todolist.service;

import org.springframework.stereotype.Service;
import ru.otus.todolist.model.Tasks;
import ru.otus.todolist.model.Users;
import ru.otus.todolist.repository.TasksRepository;
import ru.otus.todolist.repository.UsersRepository;

import java.util.List;

@Service
public class TestSrv {

    UsersRepository usersRepository;
    TasksRepository tasksRepository;

    public TestSrv (UsersRepository usersRepository, TasksRepository tasksRepository) {
        this.usersRepository = usersRepository;
        this.tasksRepository = tasksRepository;
    }

    public void testRep() {
        List<Users> users = usersRepository.findAll();
        System.out.println(users.size());
    }
}

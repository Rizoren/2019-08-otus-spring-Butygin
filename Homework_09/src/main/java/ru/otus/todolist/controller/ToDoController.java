package ru.otus.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.todolist.repository.TasksRepository;
import ru.otus.todolist.repository.UsersRepository;

@Controller
public class ToDoController {
    private final TasksRepository tasksRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public ToDoController(TasksRepository tasksRepository, UsersRepository usersRepository) {
        this.tasksRepository = tasksRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/list")
    public String listPage(Model model) {
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(Model model) {
        return "edit";
    }
}

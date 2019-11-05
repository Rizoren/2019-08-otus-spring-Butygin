package ru.otus.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.todolist.model.Tasks;
import ru.otus.todolist.model.Users;
import ru.otus.todolist.repository.TasksRepository;
import ru.otus.todolist.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

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
    public String listPage(@RequestParam("userName") String userName, Model model) {
        Users user = usersRepository.findByUserName(userName);
        List<Tasks> tasks = tasksRepository.findAllByUsers(user);
        model.addAttribute("tasks", tasks);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") String id,Model model) {
        Tasks task = tasksRepository.findById(Long.valueOf(id)).orElse(new Tasks());
        model.addAttribute("task", task);
        return "edit";
    }
}

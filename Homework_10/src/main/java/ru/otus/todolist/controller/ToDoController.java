package ru.otus.todolist.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.todolist.model.Tasks;
import ru.otus.todolist.model.Users;
import ru.otus.todolist.repository.TasksRepository;
import ru.otus.todolist.repository.UsersRepository;

import javax.validation.Valid;
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

    @GetMapping("{id}/list")
    public String getListPage(@PathVariable Long id, Model model) {
        Users user = usersRepository.findById(id).get();
        List<Tasks> tasks = user.getTasks();
        model.addAttribute("userId", id);
        model.addAttribute("tasks", tasks);
        return "list";
    }

    @PostMapping("/list")
    public RedirectView postListPage(@RequestParam("userName") String userName) {
        Users user = usersRepository.findByUserName(userName).orElse(new Users(userName));
        if (user.getUserId() == 0) {
            user = usersRepository.saveAndFlush(user);
        }
        return new RedirectView(user.getUserId()+"/list");
    }

    @PostMapping("{id}/droplist")
    public RedirectView dropListPage(@PathVariable Long id) {
        Users user = usersRepository.findById(id).get();
        usersRepository.delete(user);
        return new RedirectView("/");
    }

    @GetMapping("{id}/newtask")
    public String createPage(@PathVariable Long id, Model model) {
        Tasks task = new Tasks();
        model.addAttribute("userId", id);
        model.addAttribute("task", task);
        return "edit";
    }

    @GetMapping("{id}/edit")
    public String editPage(@PathVariable Long id, @RequestParam("task_id") String task_id, Model model) {
        Tasks task = tasksRepository.findById(Long.valueOf(task_id)).orElse(new Tasks());
        model.addAttribute("userId", id);
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("{id}/edit")
    @Transactional
    public RedirectView editPageSave(@PathVariable Long id, @Valid @ModelAttribute(name = "task") Tasks task, Model model) {
        task.setUsers(usersRepository.findById(id).get());
        task = tasksRepository.saveAndFlush(task);
        model.addAttribute("task", task);
        return new RedirectView("/{id}/list");
    }

    @PostMapping("{id}/droptask")
    public RedirectView deletePageSave(@RequestParam("task_id") Long task_id) {
        Tasks task = tasksRepository.findById(task_id).get();
        tasksRepository.delete(task);
        return new RedirectView("/{id}/list");
    }

}

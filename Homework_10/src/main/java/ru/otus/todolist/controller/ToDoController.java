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

    @GetMapping("/list")
    public String getListPage(@RequestParam("id") Long id) {
        return "list";
    }

    @PostMapping("/list")
    public RedirectView postListPage(@RequestParam("userName") String userName) {
        Users user = usersRepository.findByUserName(userName).orElse(new Users(userName));
        if (user.getUserId() == 0) {
            user = usersRepository.saveAndFlush(user);
        }
        return new RedirectView("/list?id="+user.getUserId());
    }

    @PostMapping("/droplist")
    public RedirectView dropListPage(@RequestParam("id") Long id) {
        Users user = usersRepository.findById(id).get();
        usersRepository.delete(user);
        return new RedirectView("/");
    }

    @GetMapping("/newtask")
    public String createPage(@RequestParam("id") Long id, Model model) {
//        Tasks task = new Tasks();
//        model.addAttribute("userId", id);
//        model.addAttribute("task", task);
        return "edit";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") Long id, @RequestParam("task_id") String task_id, Model model) {
        Tasks task = tasksRepository.findById(Long.valueOf(task_id)).orElse(new Tasks());
        model.addAttribute("userId", id);
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("/edit")
    @Transactional
    public RedirectView editPageSave(@RequestParam("id") Long id, @Valid @ModelAttribute(name = "task") Tasks task, Model model) {
        task.setUsers(usersRepository.findById(id).get());
        task = tasksRepository.saveAndFlush(task);
        model.addAttribute("task", task);
        return new RedirectView("/list?id="+id);
    }

    @PostMapping("/droptask")
    public RedirectView deletePageSave(@RequestParam("id") Long id, @RequestParam("task_id") Long task_id) {
        Tasks task = tasksRepository.findById(task_id).get();
        tasksRepository.delete(task);
        return new RedirectView("/list?id="+id);
    }

}

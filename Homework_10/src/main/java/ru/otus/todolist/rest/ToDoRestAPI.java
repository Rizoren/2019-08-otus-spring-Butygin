package ru.otus.todolist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.otus.todolist.model.Tasks;
import ru.otus.todolist.model.Users;
import ru.otus.todolist.repository.TasksRepository;
import ru.otus.todolist.repository.UsersRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ToDoRestAPI {
    private final TasksRepository tasksRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public ToDoRestAPI(TasksRepository tasksRepository, UsersRepository usersRepository) {
        this.tasksRepository = tasksRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/api/tasklist")
    public List<Tasks> getTaskList(@RequestParam("id") Long id) {
        return tasksRepository.findAllByUsers_UserId(id);
    }

    @PostMapping("/api/droptasklist")
    @Transactional
    public void dropTaskList(@RequestParam("id") Long id) {
        tasksRepository.deleteAllByUsers_UserId(id);
    }

    @GetMapping("/api/task")
    public Tasks getNewTask(@RequestParam("task_id") Long id) {
        return tasksRepository.findById(id).orElse(new Tasks());
    }

    @PostMapping("/api/savetask")
    @Transactional
    public Tasks saveTask(@RequestParam("id") Long id, @Valid @ModelAttribute(name = "task") Tasks task) {
        task.setUsers(usersRepository.findById(id).get());
        task = tasksRepository.saveAndFlush(task);
        return task;
    }

    @PostMapping("/api/droptask")
    public void dropTask(@RequestParam("task_id") Long id) {
        tasksRepository.deleteById(id);
    }
}


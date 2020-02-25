package todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todolist.dto.TaskDto;
import todolist.exception.NotFoundException;
import todolist.exception.ServerException;
import todolist.service.ToDoTaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ToDoRestAPI {

    private final ToDoTaskService taskService;

    @GetMapping("/api/tasklist")
    public List<TaskDto> getTaskList(@RequestParam("id") String id) throws NotFoundException {
        return taskService.findAllByUserId(id);
    }

    @PostMapping("/api/droptasklist")
    public void dropTaskList(@RequestParam("id") String id) throws ServerException {
        taskService.deleteByUserId(id);
    }

    @GetMapping("/api/task")
    public TaskDto getTask(@RequestParam("task_id") String id) {
        return Long.valueOf(id) == 0L ? taskService.getNewTask() : taskService.findById(id);
    }

    @PostMapping("/api/savetask")
    public TaskDto saveTask(@RequestParam("user_id") String userId, @RequestParam("task_id") String taskId,
                            @Valid @ModelAttribute(name = "task") TaskDto task) throws ServerException  {
        return taskService.saveById(task, userId);
    }

    @PostMapping("/api/droptask")
    public void dropTask(@RequestParam("task_id") String id) throws ServerException {
        taskService.deleteById(id);
    }
}


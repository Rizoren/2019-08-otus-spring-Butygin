package todolist.service;

import todolist.dto.TaskDto;
import todolist.exception.NotFoundException;
import todolist.exception.ServerException;

import java.util.List;

public interface ToDoTaskService {
    List<TaskDto> findAllByUserId(String id) throws NotFoundException;
    TaskDto findById(String id);
    TaskDto saveById(TaskDto task, String userId) throws ServerException;
    TaskDto getNewTask();
    void deleteById(String id) throws ServerException;
    void deleteByUserId(String id) throws ServerException;

}

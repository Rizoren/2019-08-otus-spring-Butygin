package todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.dto.TaskDto;
import todolist.dto.UserDto;
import todolist.dto.mapper.TaskMapper;
import todolist.dto.mapper.UserMapper;
import todolist.exception.NotFoundException;
import todolist.exception.ServerException;
import todolist.model.Tasks;
import todolist.repository.TasksRepository;
import todolist.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToDoTaskServiceImpl implements ToDoTaskService {

    private final TasksRepository tasksRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;

    @Override
    public TaskDto findById(String id) {
        return taskMapper.toDto(tasksRepository.findById(Long.valueOf(id)).orElse(new Tasks()));
    }

    @Override
    public List<TaskDto> findAllByUserId(String id) throws NotFoundException {
        return taskMapper.toListDto(tasksRepository.findAllByUser_Id(Long.valueOf(id)).orElseThrow(()-> new NotFoundException("User not found!")));
    }

    @Override
    @Transactional
    public TaskDto saveById(TaskDto task, String userId) throws ServerException {
        Tasks taskEntity;
        if (Long.valueOf(task.getId()) != 0) {
            taskEntity = tasksRepository.findById(Long.valueOf(task.getId())).get();
        } else {
            taskEntity = new Tasks();
            taskEntity.setUser(userRepository.findFirstById(Long.valueOf(userId)).get());
        }
        taskEntity.setId(Long.valueOf(task.getId()));
        taskEntity.setName(task.getName());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setStatus(Short.valueOf(task.getStatus()));
        taskEntity = tasksRepository.saveAndFlush(taskEntity);

        return taskMapper.toDto(taskEntity);
    }

    @Override
    public TaskDto getNewTask() {
        return taskMapper.toDto(new Tasks());
    }

    @Override
    @Transactional
    public void deleteById(String id) throws ServerException {
        tasksRepository.deleteById(Long.valueOf(id));
    }

    @Override
    @Transactional
    public void deleteByUserId(String id) throws ServerException {
        tasksRepository.deleteAllByUser_Id(Long.valueOf(id));
    }
}

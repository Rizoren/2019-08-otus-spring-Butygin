package todolist.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import todolist.dto.UserFullDto;
import todolist.model.Tasks;
import todolist.dto.TaskDto;
import todolist.model.User;

import java.sql.Timestamp;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TaskMapper extends ObjectMapper<Tasks, TaskDto> {

    @Mappings( {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "enddate", dateFormat = "dd-MM-yyyy HH:mm", target = "enddate")
    })
    TaskDto toDto(Tasks task);

    default Tasks toEntity(TaskDto taskDto) {
        Tasks task = new Tasks();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setStatus(Short.valueOf(taskDto.getStatus()));
        //task.setEnddate(new Timestamp(taskDto.getEnddate().getTime()));
        return task;
    }
}

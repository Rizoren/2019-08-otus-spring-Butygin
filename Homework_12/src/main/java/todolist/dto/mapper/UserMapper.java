package todolist.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import todolist.model.User;
import todolist.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends ObjectMapper<User, UserDto> {

    @Mappings( {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "username")
    })
    UserDto toDto(User user);
}

package todolist.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import todolist.dto.UserDto;
import todolist.dto.UserFullDto;
import todolist.model.User;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserFullMapper extends ObjectMapper<User, UserFullDto> {

    @Mappings( {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "username"),
            @Mapping(source = "password", target = "password")
    })
    UserFullDto toDto(User user);

    default User toEntity(UserFullDto newuser) {
        User user = new User();
        user.setName(newuser.getUsername());
        user.setPassword(newuser.getPassword());
        return user;
    }
}

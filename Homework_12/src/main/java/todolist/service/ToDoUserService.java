package todolist.service;

import todolist.dto.UserDto;
import todolist.dto.UserFullDto;

public interface ToDoUserService {
    UserDto findUserByName(String username);
    UserDto findUserById(String id);
    UserDto createNewUser(UserFullDto user);
    String getCountUsers();
}
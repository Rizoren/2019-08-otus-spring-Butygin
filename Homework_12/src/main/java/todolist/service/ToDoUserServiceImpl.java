package todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.dto.UserDto;
import todolist.dto.UserFullDto;
import todolist.dto.mapper.UserMapper;
import todolist.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToDoUserServiceImpl implements ToDoUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto findUserByName(String username) {
        return userMapper.toDto(userRepository.findFirstByName(username).get());
    }

    @Override
    public UserDto findUserById(String id) {
        return userMapper.toDto(userRepository.findFirstById(Long.valueOf(id)).get());
    }

    @Override
    public UserDto createNewUser(UserFullDto user) {
        return null;
    }

    @Override
    public String getCountUsers() {
        return String.valueOf(userRepository.findAll().size());
    }
}

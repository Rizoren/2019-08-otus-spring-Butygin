package todolist.controller;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import todolist.model.Authority;
import todolist.model.Tasks;
import todolist.model.User;
import todolist.repository.UserRepository;

import java.util.Collections;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Контроллер MVC")
@SpringBootTest
@AutoConfigureMockMvc
public class ToDoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @DisplayName(" должен возвращать welcome-page")
    @Test
    void shouldReturnListPage() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(content().string(containsString("Welcome in Your ToDo List")))
                .andReturn();
    }

    @DisplayName(" должен направлять на страницу errors/err404 (User Not Found)")
    @Test
    @WithMockUser
    void shouldReturnUserNotFound() throws Exception {

        mockMvc.perform(get("/list").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("errors/err404"))
                .andExpect(content().string(containsString("User not found!")))
                .andReturn();
    }
}

package ru.otus.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class ToDolistApplicationTests {

    public static final String YOUR_TO_DO_LIST = "Your ToDo List";
    public static final long USER_1 = 1L;
    public static final long TASK_1 = 1L;
    public static final String TASK_STR_1 = "Task1";
    public static final String TASK_STR_2 = "Task2";
    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnLoginPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(content().string(containsString(YOUR_TO_DO_LIST)))
                .andReturn();
    }

    @Test
    void shouldReturnListPage() throws Exception {
        mockMvc.perform(get("/"+USER_1+"/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(content().string(containsString(TASK_STR_1)))
                .andExpect(content().string(containsString(TASK_STR_2)))
                .andReturn();
    }

/*    @Test
    void shouldReturnEditPage() throws Exception {
        mockMvc.perform(get("/{id}/edit", USER_1).param("task_id", String.valueOf(TASK_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(content().string(containsString(TASK_STR_1)))
                .andReturn();
    }*/
}

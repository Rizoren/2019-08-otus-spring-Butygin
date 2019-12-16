package ru.otus.todolist;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.todolist.model.Tasks;
import ru.otus.todolist.model.Users;
import ru.otus.todolist.repository.TasksRepository;
import ru.otus.todolist.repository.UsersRepository;

import java.sql.Timestamp;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryTests {

    public static final long USER_1 = 1L;
    public static final long USER_2 = 2L;
    public static final String USER_NAME_1 = "User1";
    public static final String USER_NAME_2 = "User2";
    public static final long TASK_1 = 1L;
    public static final long TASK_2 = 2L;
    public static final long TASK_3 = 3L;
    public static final long TASK_4 = 4L;
    public static final String TASK_STR_1 = "Task1";
    public static final String TASK_STR_2 = "Task2";
    public static final String TASK_STR_3 = "Task3";
    public static final String TASK_STR_4 = "Task4";
    public static final short TASK_STATUS_1 = (short) 1;
    public static final short TASK_STATUS_0 = (short) 1;
    public static final Timestamp TASK_DATE = Timestamp.valueOf("2000-01-01 00:00:00.000000");
    @Autowired
    private TestEntityManager em;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TasksRepository tasksRepository;

    @Test
    public void getUsers () {
        val find = usersRepository.findAll();
        val exp = Arrays.asList(
                new Users(USER_1, USER_NAME_1, USER_NAME_1),
                new Users(USER_2, USER_NAME_2, USER_NAME_2)
        );
        assertThat(find).hasSize(exp.size());
    }

    @Test
    public void getTasks () {
        val find = tasksRepository.findAll();
        val exp = Arrays.asList(
                new Tasks(TASK_1, TASK_STR_1,TASK_STR_1, TASK_STATUS_1, TASK_DATE, null),
                new Tasks(TASK_2, TASK_STR_2,TASK_STR_2, TASK_STATUS_0, TASK_DATE, null),
                new Tasks(TASK_3, TASK_STR_3,TASK_STR_3, TASK_STATUS_1, TASK_DATE, null),
                new Tasks(TASK_4, TASK_STR_4,TASK_STR_4, TASK_STATUS_0, TASK_DATE, null)
        );
        assertThat(find).hasSize(exp.size());
    }

    @Test
    public void getUserByName () {
        val find = usersRepository.findByUserName(USER_NAME_1).get();
        val exp = em.find(Users.class, USER_1);

        assertThat(find).isNotNull().isEqualTo(exp);
    }
}

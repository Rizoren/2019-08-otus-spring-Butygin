package todolist.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import todolist.model.Authority;
import todolist.model.Tasks;
import todolist.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий задач")
@DataJpaTest
@EnableConfigurationProperties
@ComponentScan({"todolist.repository","todolist.model.Tasks"})
public class TasksRepositoryTest {

    @Autowired
    TestEntityManager em;
    @Autowired
    TasksRepository tasksRepository;

    List<Tasks> prepareList = new ArrayList<>();

    @Order(1)
    @DisplayName("должен получать все задачи")
    @Test
    void shouldGetTaskList() {
        Set<Authority> ath = Collections.emptySet();

        val user1 = em.merge(new User(1L, "USER_1", "USER_1", ath));
        val user2 = em.merge(new User(2L, "USER_2", "USER_2", ath));

        prepareList.add(em.merge(new Tasks(1L, "NAME_1", "DESC_1", (short) 0, null, user1)));
        prepareList.add(em.merge(new Tasks(2L, "NAME_2", "DESC_2", (short) 0, null, user1)));
        prepareList.add(em.merge(new Tasks(3L, "NAME_3", "DESC_3", (short) 0, null, user2)));

        List<Tasks> tasks = tasksRepository.findAll();
        assertThat(tasks).hasSize(3).containsOnlyElementsOf(prepareList);
    }

    @Order(2)
    @DisplayName("должен получать все задачи по пользователю")
    @Test
    void shouldGetTaskListByUserId() {
        Set<Authority> ath = Collections.emptySet();

        val user1 = em.merge(new User(1L, "USER_1", "USER_1", ath));
        val user2 = em.merge(new User(2L, "USER_2", "USER_2", ath));

        prepareList.add(em.merge(new Tasks(1L, "NAME_1", "DESC_1", (short) 0, null, user1)));
        prepareList.add(em.merge(new Tasks(2L, "NAME_2", "DESC_2", (short) 0, null, user1)));
        prepareList.add(em.merge(new Tasks(3L, "NAME_3", "DESC_3", (short) 0, null, user2)));

        List<Tasks> tasks = tasksRepository.findAllByUser_Id(1L).orElse(null);
        assertThat(tasks).hasSize(2).containsOnlyElementsOf(prepareList.subList(0,2));
    }
}

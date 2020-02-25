package todolist.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import todolist.model.Authority;
import todolist.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий пользователей")
@DataJpaTest
@EnableConfigurationProperties
@ComponentScan({"todolist.repository","todolist.model.User"})
public class UserRepositoryTest {

    @Autowired
    TestEntityManager em;
    @Autowired
    UserRepository userRepository;

    @DisplayName("должен получать всех пользователей")
    @Test
    void shouldGetUserList() {
        Set<Authority> ath = Collections.emptySet();

        val user1 = em.merge(new User(1L, "USER_1", "USER_1", ath));
        val user2 = em.merge(new User(2L, "USER_2", "USER_2", ath));
        val user3 = em.merge(new User(3L, "USER_3", "USER_3", ath));

        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(3).containsOnly(user1,user2,user3);
    }
}

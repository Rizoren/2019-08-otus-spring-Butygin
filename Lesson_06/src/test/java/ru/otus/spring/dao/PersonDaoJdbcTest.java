package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PersonDAO")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(PersonDaoJdbc.class)
public class PersonDaoJdbcTest {

    public static final int EXPECTED_PERSON_COUNT = 1;
    public static final int DEFAULT_PERSON_ID = 1;
    @Autowired
    private PersonDaoJdbc personDaoJdbc;

    @DisplayName("1")
    @Test
    void shouldReturnPersonCount() {
        assertThat(personDaoJdbc.count()).isEqualTo(EXPECTED_PERSON_COUNT);
    }

    @DisplayName("2")
    @Test
    void shouldReturnPersonById() {
        assertThat(personDaoJdbc.getById(DEFAULT_PERSON_ID)).hasFieldOrPropertyWithValue("name","masha");
    }
}

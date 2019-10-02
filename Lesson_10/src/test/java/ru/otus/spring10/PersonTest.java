package ru.otus.spring10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring10.domain.Person;
import ru.otus.spring10.repostory.PersonRepository;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test_1() {
        personRepository.save(new Person("LUPA"));
        assertThat(personRepository.findByName("LUPA"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "LUPA");
    }
}

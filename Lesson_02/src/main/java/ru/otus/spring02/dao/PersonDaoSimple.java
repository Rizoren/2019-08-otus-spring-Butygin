package ru.otus.spring02.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring02.domain.Person;

@Component
public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}

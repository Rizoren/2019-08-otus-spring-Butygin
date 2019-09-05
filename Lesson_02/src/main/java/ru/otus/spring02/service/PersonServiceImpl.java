package ru.otus.spring02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring02.dao.PersonDao;
import ru.otus.spring02.domain.Person;

@Component
public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;
    @Autowired
    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}

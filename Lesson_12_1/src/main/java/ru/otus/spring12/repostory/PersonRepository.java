package ru.otus.spring12.repostory;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring12.domain.Person;

import java.util.List;

@Repository
public interface PersonRepository extends KeyValueRepository<Person, Integer> {

    List<Person> findAll();
}

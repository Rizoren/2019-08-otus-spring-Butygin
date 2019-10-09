package ru.otus.spring12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import ru.otus.spring12.domain.Email;
import ru.otus.spring12.domain.Person;
import ru.otus.spring12.repostory.EmailRepository;
import ru.otus.spring12.repostory.EmailRepositoryImpl;
import ru.otus.spring12.repostory.PersonRepository;

import javax.annotation.PostConstruct;
import java.util.WeakHashMap;

@SpringBootApplication
@EnableMapRepositories//(mapType = WeakHashMap.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersonRepository repository;
    @Autowired
    private EmailRepository emailRepository;

    @PostConstruct
    public void init() {
        repository.save(new Person("Pushkin"));

        System.out.println(repository.findAll());

        emailRepository.save(new Email("a@a.ru"));
        //emailRepository.save(new Email(1,"b@b.ru"));

        System.out.println(emailRepository.findAll());
    }
}

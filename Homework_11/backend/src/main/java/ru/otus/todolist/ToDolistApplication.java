package ru.otus.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.todolist.repository.UsersRepository;

@SpringBootApplication
public class ToDolistApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ToDolistApplication.class, args);
    }

}

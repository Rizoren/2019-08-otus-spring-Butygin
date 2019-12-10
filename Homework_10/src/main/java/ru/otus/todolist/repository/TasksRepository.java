package ru.otus.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.todolist.model.Tasks;
import ru.otus.todolist.model.Users;

import java.util.List;

@Repository
@Transactional
public interface TasksRepository extends JpaRepository<Tasks, Long> {
}

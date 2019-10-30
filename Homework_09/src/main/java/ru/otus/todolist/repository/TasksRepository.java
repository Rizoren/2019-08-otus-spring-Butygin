package ru.otus.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.todolist.model.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
}

package ru.otus.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.todolist.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}

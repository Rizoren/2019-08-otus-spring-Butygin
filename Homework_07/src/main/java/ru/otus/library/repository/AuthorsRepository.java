package ru.otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Long> {
}

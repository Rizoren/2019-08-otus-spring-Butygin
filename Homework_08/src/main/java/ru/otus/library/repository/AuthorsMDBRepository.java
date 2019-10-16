package ru.otus.library.repository;

import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;
import ru.otus.library.model.AuthorsMDB;

@Repository
public interface AuthorsMDBRepository extends MongoRepository<AuthorsMDB, ObjectId> {
}

package ru.otus.library.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.GenresMDB;

@Repository
public interface GenresMDBRepository extends MongoRepository<GenresMDB, ObjectId> {
}

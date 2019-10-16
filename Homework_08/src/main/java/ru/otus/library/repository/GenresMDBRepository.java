package ru.otus.library.repository;

import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Genres;

@Repository
public interface GenresMDBRepository extends MongoRepository<Genres, ObjectId> {
}

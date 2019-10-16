package ru.otus.library.repository;

import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.BooksMDB;
import ru.otus.library.model.Genres;

import java.util.List;

@Repository
public interface BooksMDBRepository extends MongoRepository<BooksMDB, ObjectId> {
//    List<Books> findAllByAuthors(Authors authors);
//    List<Books> findAllByGenres(Genres genres);
}

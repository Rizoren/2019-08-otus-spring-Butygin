package ru.otus.library.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.*;

import java.util.List;

@Repository
public interface BooksMDBRepository extends MongoRepository<BooksMDB, ObjectId>, BooksMDBRepositoryCustom {
    List<BooksMDB> findAllByGenresIsContaining(GenresMDB genresMDB);
    List<BooksMDB> findAllByAuthorsIs(AuthorsMDB authorsMDB);
}

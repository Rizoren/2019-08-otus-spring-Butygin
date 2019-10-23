package ru.otus.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.BooksMDB;
import ru.otus.library.model.GenresMDB;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@RequiredArgsConstructor
public class BooksMDBRepositoryImpl implements BooksMDBRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<GenresMDB> findDistinctGenresMDB() {
        Aggregation aggregation = newAggregation(
                project().andInclude("genres")
                , unwind("genres")
                , group("genres.genre_name")
                , project().andExclude("_id").and("_id").as("genre_name")
                , sort(Sort.Direction.ASC,"genre_name")
        );

        return mongoTemplate.aggregate(aggregation, BooksMDB.class, GenresMDB.class).getMappedResults();
    }
}


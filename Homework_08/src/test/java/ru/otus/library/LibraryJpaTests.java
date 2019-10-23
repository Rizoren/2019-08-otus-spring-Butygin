package ru.otus.library;


import lombok.val;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.library.model.AuthorsMDB;
import ru.otus.library.model.BooksMDB;
import ru.otus.library.model.GenresMDB;
import ru.otus.library.repository.AuthorsMDBRepository;
import ru.otus.library.repository.BooksMDBRepository;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repositories of Library")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.library.config", "ru.otus.library.repository", "ru.otus.library.events"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibraryJpaTests {

    public static final String FIRST_AUTHOR_ID = "5dadde8600086d41e4b79eaa";
    public static final String FIRST_BOOK_ID = "5dadde8600086d41e4b79eab";
    public static final int PREPARED_AMOUNT_AUTHORS = 3;
    public static final int PREPARED_AMOUNT_GENRES = 2;
    public static final int PREPARED_AMOUNT_BOOKS = 3;
    public static final String TEST_STRING_DATA = "Test";

    @Autowired
    private MongoTemplate mt;
    @Autowired
    private AuthorsMDBRepository authorsMDBRepository;
    @Autowired
    private BooksMDBRepository booksMDBRepository;

    @DisplayName("Authors get-test")
    @Test
    @Order(1)
    public void getAuthorsRepositoryMDBTest () {
        val pre = authorsMDBRepository.findAll();
        val find = authorsMDBRepository.findById(pre.get(0).get_id());
        val expected = mt.findById(pre.get(0).get_id(), AuthorsMDB.class);
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Books get-test")
    @Test
    @Order(2)
    public void getBooksRepositoryMDBTest () {
        val pre = booksMDBRepository.findAll();
        val find = booksMDBRepository.findById(pre.get(0).get_id());
        val expected = mt.findById(pre.get(0).get_id(), BooksMDB.class);
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Library get-all-authors-test")
    @Test
    @Order(5)
    public void getAuthorsListLibraryRepositoryMDBTest () {
        val find = authorsMDBRepository.findAll();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_AUTHORS);
    }

    @DisplayName("Library get-distinct-genres-test")
    @Test
    @Order(6)
    public void getGenresListLibraryRepositoryMDBTest () {
        val find = booksMDBRepository.findDistinctGenresMDB();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_GENRES);
    }

    @DisplayName("Library get-all-books-test")
    @Test
    @Order(7)
    public void getBooksListLibraryRepositoryMDBTest () {
        val find = booksMDBRepository.findAll();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_BOOKS);
    }

    @DisplayName("Library save-all-data-test")
    @Test
    @Order(4)
    public void saveAllDataAboutBook() {
        val author = new AuthorsMDB(new ObjectId(FIRST_AUTHOR_ID), TEST_STRING_DATA, TEST_STRING_DATA, TEST_STRING_DATA);
        val genre = new GenresMDB(TEST_STRING_DATA);
        val authors = Arrays.asList(author);
        val genres = Arrays.asList(genre);

        val book = new BooksMDB(new ObjectId(FIRST_BOOK_ID), TEST_STRING_DATA, TEST_STRING_DATA, 0, TEST_STRING_DATA, authors, genres);
        val savedbook = booksMDBRepository.save(book);

        assertThat(savedbook.get_id()).isNotNull();
        assertThat(savedbook.getBook_name()).isEqualTo(book.getBook_name());
    }

    @DisplayName("Library delete-data-test")
    @Test
    @Order(3)
    public void deleteBookData() {
        val pre = booksMDBRepository.findAll();
        val find = booksMDBRepository.findById(pre.get(0).get_id());

        booksMDBRepository.delete(find.get());

        val expected = mt.findById(pre.get(0).get_id(), BooksMDB.class);
        assertThat(expected).isNull();
    }
}

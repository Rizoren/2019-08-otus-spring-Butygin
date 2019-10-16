package ru.otus.library;


import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;
import ru.otus.library.repository.*;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repositories of Library")
@DataJpaTest
@Import({BooksRepositoryImpl.class})
public class LibraryJpaTests {

    public static final long FIRST_AUTHOR_ID = 1;
    public static final long FIRST_GENRE_ID = 1;
    public static final long FIRST_BOOK_ID = 1;
    public static final int PREPARED_AMOUNT_AUTHORS = 3;
    public static final int PREPARED_AMOUNT_GENRES = 3;
    public static final int PREPARED_AMOUNT_BOOKS = 3;
    public static final String TEST_STRING_DATA = "Test";
    @Autowired
    private TestEntityManager em;
    @Autowired
    private AuthorsRepository authorsRepository;
    @Autowired
    private GenresRepository genresRepository;
    @Autowired
    private BooksRepository booksRepository;


    @DisplayName("Authors get-test")
    @Test
    public void getAuthorsRepositoryJpaTest () {
        val find = authorsRepository.findById(FIRST_AUTHOR_ID);
        val expected = em.find(Authors.class, FIRST_AUTHOR_ID);
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Genres get-test")
    @Test
    public void getGenresRepositoryJpaTest () {
        val find = genresRepository.findById(FIRST_GENRE_ID);
        val expected = em.find(Genres.class, FIRST_GENRE_ID);
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Books get-test")
    @Test
    public void getBooksRepositoryJpaTest () {
        val find = booksRepository.findById(FIRST_BOOK_ID);
        val expected = em.find(Books.class, FIRST_BOOK_ID);
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Library get-all-authors-test")
    @Test
    public void getAuthorsListLibraryRepositoryJpaTest () {
        val find = authorsRepository.findAll();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_AUTHORS);
    }

    @DisplayName("Library get-all-genres-test")
    @Test
    public void getGenresListLibraryRepositoryJpaTest () {
        val find = genresRepository.findAll();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_GENRES);
    }

    @DisplayName("Library get-all-books-test")
    @Test
    public void getBooksListLibraryRepositoryJpaTest () {
        val find = booksRepository.findAll();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_BOOKS);
    }

    @DisplayName("Library save-all-data-test")
    @Test
    public void saveAllDataAboutBook() {
        val author = new Authors(0, TEST_STRING_DATA, TEST_STRING_DATA, TEST_STRING_DATA);
        val genre = new Genres(0, TEST_STRING_DATA);
        val authors = Collections.singletonList(author);
        val genres = Collections.singletonList(genre);

        val book = new Books(0, TEST_STRING_DATA, null, null, null, authors, genres);
        booksRepository.save(book);
        assertThat(book.getBook_id()).isGreaterThan(0);

        val actualBook = em.find(Books.class, book.getBook_id());
        assertThat(actualBook).isNotNull().matches(s -> s.getBook_name().equals(TEST_STRING_DATA))
                .matches(s -> s.getAuthors() != null && s.getAuthors().size() > 0 && s.getAuthors().get(0).getAuthor_family().equals(TEST_STRING_DATA))
                .matches(s -> s.getGenres() != null && s.getGenres().size() > 0 && s.getGenres().get(0).getGenre_name().equals(TEST_STRING_DATA));
    }

    @DisplayName("Library delete-data-test")
    @Test
    public void deleteBookData() {

        val find = booksRepository.findById(FIRST_BOOK_ID);

        booksRepository.delete(find.get());

        val expected = em.find(Books.class, FIRST_BOOK_ID);
        assertThat(expected).isNull();
    }
}

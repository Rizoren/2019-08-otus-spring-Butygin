package ru.otus.library;


import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;
import ru.otus.library.repository.AuthorsRepositoryJdbcImpl;
import ru.otus.library.repository.BooksRepositoryJdbcImpl;
import ru.otus.library.repository.GenresRepositoryJdbcImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repositories of Library")
@JdbcTest
@Import({AuthorsRepositoryJdbcImpl.class, GenresRepositoryJdbcImpl.class, BooksRepositoryJdbcImpl.class})
public class LibraryJpaTests {

    public static final long FIRST_AUTHOR_ID = 1;
    public static final long FIRST_GENRE_ID = 1;
    public static final long FIRST_BOOK_ID = 1;
    public static final int PREPARED_AMOUNT_AUTHORS = 3;
    public static final int PREPARED_AMOUNT_GENRES = 3;
    public static final int PREPARED_AMOUNT_BOOKS = 3;
    public static final String TEST_STRING_DATA = "Test";

    @Autowired
    private AuthorsRepositoryJdbcImpl authorsRepositoryJdbc;
    @Autowired
    private GenresRepositoryJdbcImpl genresRepositoryJdbc;
    @Autowired
    private BooksRepositoryJdbcImpl booksRepositoryJdbc;


    @DisplayName("Authors get-test")
    @Test
    public void getAuthorsRepositoryJpaTest () {
        val find = authorsRepositoryJdbc.findById(FIRST_AUTHOR_ID);
        val expected = new Authors(1, "Пушкин","Александр","Сергеевич" );
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Genres get-test")
    @Test
    public void getGenresRepositoryJpaTest () {
        val find = genresRepositoryJdbc.findById(FIRST_GENRE_ID);
        val expected = new Genres(1,"поэма" );
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Books get-test")
    @Test
    public void getBooksRepositoryJpaTest () {
        val find = booksRepositoryJdbc.findById(FIRST_BOOK_ID);
        val expected = new Books(   1,
                                    "Руслан и Людмила",
                                    "первая законченная поэма Александра Сергеевича Пушкина; волшебная сказка, вдохновлённая древнерусскими былинами",
                                    1820,
                                    "000-0-00-000001-0", null, null );
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Library get-all-authors-test")
    @Test
    public void getAuthorsListLibraryRepositoryJpaTest () {
        val find = authorsRepositoryJdbc.findAll();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_AUTHORS);
    }

    @DisplayName("Library get-all-genres-test")
    @Test
    public void getGenresListLibraryRepositoryJpaTest () {
        val find = genresRepositoryJdbc.findAll();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_GENRES);
    }

    @DisplayName("Library get-all-books-test")
    @Test
    public void getBooksListLibraryRepositoryJpaTest () {
        val find = booksRepositoryJdbc.findAll();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_BOOKS);
    }
/* H2 NOT SUPPORT RETURNING
    @DisplayName("Library save-data-test")
    @Test
    public void saveDataAboutBook() {

        val book = new Books(0, TEST_STRING_DATA, null, null, null, null, null);
        val actualBook = booksRepositoryJdbc.save(book);
        assertThat(book.getBook_id()).isGreaterThan(0);

        assertThat(actualBook).isNotNull().matches(s -> s.getBook_name().equals(TEST_STRING_DATA));
    }
*/
    @DisplayName("Library delete-data-test")
    @Test
    public void deleteBookData() {
        val cnt = booksRepositoryJdbc.findAll().size();
        val find = booksRepositoryJdbc.findById(FIRST_BOOK_ID);

        booksRepositoryJdbc.delete(find.get());

        val expected = cnt - 1;
        assertThat(booksRepositoryJdbc.findAll().size()).isEqualTo(expected);
    }
}

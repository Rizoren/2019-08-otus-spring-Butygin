package ru.otus.library;


import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.model.*;
import ru.otus.library.repository.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repositories of Library")
@DataJpaTest
@Import({AuthorsRepositoryJpaImpl.class, GenresRepositoryJpaImpl.class, BooksRepositoryJpaImpl.class, LibraryRepositoryJpaImpl.class})
public class LibraryJpaTests {

    public static final long FIRST_AUTHOR_ID = 1;
    public static final long FIRST_GENRE_ID = 1;
    public static final long FIRST_BOOK_ID = 1;
    public static final int PREPARED_AMOUNT_AUTHORS = 3;
    public static final int PREPARED_AMOUNT_GENRES = 3;
    public static final int PREPARED_AMOUNT_BOOKS = 3;
    @Autowired
    private TestEntityManager em;
    @Autowired
    private AuthorsRepositoryJpaImpl authorsRepositoryJpa;
    @Autowired
    private GenresRepositoryJpaImpl genresRepositoryJpa;
    @Autowired
    private BooksRepositoryJpaImpl booksRepositoryJpa;
    @Autowired
    private LibraryRepositoryJpaImpl libraryRepositoryJpa;

    @DisplayName("Authors get-test")
    @Test
    public void getAuthorsRepositoryJpaTest () {
        val find = authorsRepositoryJpa.findById(FIRST_AUTHOR_ID);
        val expected = em.find(Authors.class, FIRST_AUTHOR_ID);
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Genres get-test")
    @Test
    public void getGenresRepositoryJpaTest () {
        val find = genresRepositoryJpa.findById(FIRST_GENRE_ID);
        val expected = em.find(Genres.class, FIRST_GENRE_ID);
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Books get-test")
    @Test
    public void getBooksRepositoryJpaTest () {
        val find = booksRepositoryJpa.findById(FIRST_BOOK_ID);
        val expected = em.find(Books.class, FIRST_BOOK_ID);
        assertThat(find).isPresent().get()
                .isEqualToComparingFieldByFieldRecursively(expected);
    }

    @DisplayName("Library get-all-authors-test")
    @Test
    public void getAuthorsListLibraryRepositoryJpaTest () {
        val find = libraryRepositoryJpa.findAllAuthors();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_AUTHORS);
    }

    @DisplayName("Library get-all-genres-test")
    @Test
    public void getGenresListLibraryRepositoryJpaTest () {
        val find = libraryRepositoryJpa.findAllGenres();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_GENRES);
    }

    @DisplayName("Library get-all-books-test")
    @Test
    public void getBooksListLibraryRepositoryJpaTest () {
        val find = libraryRepositoryJpa.findAllBooks();
        assertThat(find).isNotNull().hasSize(PREPARED_AMOUNT_BOOKS);
    }
}

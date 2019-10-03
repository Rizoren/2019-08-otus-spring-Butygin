package ru.otus.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;
import ru.otus.library.repository.*;
import ru.otus.library.services.IOService;
import ru.otus.library.services.LibraryServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LibraryApplicationTests {

    public static final long ID_FOR_TEST = 1;
    public static final String TEST_STRING_DATA = "Test";
    @Autowired
    private LibraryServiceImpl libraryService;
    @MockBean
    private IOService ioService;
    @MockBean
    private BooksRepository booksRepository;
    @MockBean
    private AuthorsRepository authorsRepository;
    @MockBean
    private GenresRepository genresRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    @DisplayName("LibraryServiceImpl.showInfo")
    void testShowInfo() {
        List<Authors> authors = new ArrayList<>();
        List<Genres> genres = new ArrayList<>();

        authors.add(new Authors(1,"Первый","Первый","Первый"));
        authors.add(new Authors(2,"Второй","Второй","Второй"));
        genres.add(new Genres(1,"Первый"));
        genres.add(new Genres(2,"Второй"));
        given(authorsRepository.findAll()).willReturn(authors);
        given(genresRepository.findAll()).willReturn(genres);

        libraryService.showInfo();

        verify(authorsRepository, times(1)).findAll();
        verify(genresRepository, times(1)).findAll();
        verify(ioService, times(1)).println("В наличии книги, следующих авторов:");
        verify(ioService, times(1)).println("ID: 1, Ф.И.О: Первый Первый Первый");
        verify(ioService, times(1)).println("ID: 2, Ф.И.О: Второй Второй Второй");
        verify(ioService, times(1)).println("В наличии книги, следующих жанров:");
        verify(ioService, times(1)).println("ID: 1, Наименование: Первый");
        verify(ioService, times(1)).println("ID: 2, Наименование: Второй");
    }

    @Test
    @DisplayName("LibraryServiceImpl.showAllBooksByGenreID")
    void testShowAllBooksByGenreID() {

        List<Books> books = new ArrayList<>();
        books.add(new Books(1,"Руслан и Людмила",null,null,null,null,null));
        books.add(new Books(2,"Демон",null,null,null,null,null));
        given(booksRepository.findAllByGenreID(ID_FOR_TEST)).willReturn(books);

        libraryService.showAllBooksByGenreID(ID_FOR_TEST);

        verify(booksRepository, times(1)).findAllByGenreID(ID_FOR_TEST);
        verify(ioService, times(1)).println("Книги по жанру:");
        verify(ioService, times(1)).println("1: Руслан и Людмила");
        verify(ioService, times(1)).println("2: Демон");

    }

    @Test
    @DisplayName("LibraryServiceImpl.showAllBooksByAuthorID")
    void testShowAllBooksByAuthorID() {

        List<Books> books = new ArrayList<>();
        books.add(new Books(1,"Руслан и Людмила",null,null,null,null,null));
        books.add(new Books(2,"Демон",null,null,null,null,null));
        given(booksRepository.findAllByAuthorID(ID_FOR_TEST)).willReturn(books);

        libraryService.showAllBooksByAuthorID(ID_FOR_TEST);

        verify(booksRepository, times(1)).findAllByAuthorID(ID_FOR_TEST);
        verify(ioService, times(1)).println("Книги по автору:");
        verify(ioService, times(1)).println("1: Руслан и Людмила");
        verify(ioService, times(1)).println("2: Демон");

    }

}

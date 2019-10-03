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
import ru.otus.library.repository.LibraryRepository;
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
    private LibraryRepository libraryRepository;

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
        given(libraryRepository.findAllAuthors()).willReturn(authors);
        given(libraryRepository.findAllGenres()).willReturn(genres);

        libraryService.showInfo();

        verify(libraryRepository, times(1)).findAllAuthors();
        verify(libraryRepository, times(1)).findAllGenres();
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
        given(libraryRepository.findAllByGenreID(ID_FOR_TEST)).willReturn(books);

        libraryService.showAllBooksByGenreID(ID_FOR_TEST);

        verify(libraryRepository, times(1)).findAllByGenreID(ID_FOR_TEST);
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
        given(libraryRepository.findAllByAuthorID(ID_FOR_TEST)).willReturn(books);

        libraryService.showAllBooksByAuthorID(ID_FOR_TEST);

        verify(libraryRepository, times(1)).findAllByAuthorID(ID_FOR_TEST);
        verify(ioService, times(1)).println("Книги по автору:");
        verify(ioService, times(1)).println("1: Руслан и Людмила");
        verify(ioService, times(1)).println("2: Демон");

    }

    @Test
    @DisplayName("LibraryServiceImpl.showRandomBook")
    void testShowRandomBook() {
        List<Authors> authors = new ArrayList<>();
        authors.add(new Authors(ID_FOR_TEST,TEST_STRING_DATA,TEST_STRING_DATA,TEST_STRING_DATA));
        List<Genres> genres = new ArrayList<>();
        genres.add(new Genres(ID_FOR_TEST,TEST_STRING_DATA));

        Books books = new Books(ID_FOR_TEST, TEST_STRING_DATA, null,null,null,authors,genres);

        given(libraryRepository.findRandomBook()).willReturn(books);

        libraryService.showRandomBook();

        verify(libraryRepository, times(1)).findRandomBook();
        verify(ioService, times(1)).println("Случайная книга:");
        verify(ioService, times(1)).println("ID: "+ID_FOR_TEST+", Название: \""+TEST_STRING_DATA+
                "\"; Жанр(ы): "+TEST_STRING_DATA+"; Автор(ы): "+TEST_STRING_DATA+" "+TEST_STRING_DATA.substring(0,1)+
                ". "+TEST_STRING_DATA.substring(0,1)+".");
    }
}

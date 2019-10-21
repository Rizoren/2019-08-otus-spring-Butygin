package ru.otus.library;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.library.model.AuthorsMDB;
import ru.otus.library.model.BooksMDB;
import ru.otus.library.model.GenresMDB;
import ru.otus.library.repository.AuthorsMDBRepository;
import ru.otus.library.repository.BooksMDBRepository;
import ru.otus.library.services.IOService;
import ru.otus.library.services.LibraryServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    private AuthorsMDBRepository authorsMDBRepository;
    @MockBean
    private BooksMDBRepository booksMDBRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    @DisplayName("LibraryServiceImpl.showInfo")
    void testShowInfo() {
        List<AuthorsMDB> authors = new ArrayList<>();
        List<BooksMDB> books = new ArrayList<>();
        List<GenresMDB> genres = new ArrayList<>();

        authors.add(new AuthorsMDB(new ObjectId("5dadde8600086d41e4b79eaa"),TEST_STRING_DATA,TEST_STRING_DATA,TEST_STRING_DATA));
        genres.add(new GenresMDB(TEST_STRING_DATA));
        books.add(new BooksMDB(TEST_STRING_DATA,TEST_STRING_DATA,0,TEST_STRING_DATA, authors, genres));
        given(authorsMDBRepository.findAll()).willReturn(authors);
        given(booksMDBRepository.findDistinctGenresMDB()).willReturn(genres);

        libraryService.showInfo();

        verify(authorsMDBRepository, times(1)).findAll();
        verify(booksMDBRepository, times(1)).findDistinctGenresMDB();
        verify(ioService, times(1)).println("В наличии книги, следующих авторов:");
        verify(ioService, times(1)).println("ID: 5dadde8600086d41e4b79eaa, Ф.И.О: "+TEST_STRING_DATA+" "+TEST_STRING_DATA+" "+TEST_STRING_DATA);
        verify(ioService, times(1)).println("В наличии книги, следующих жанров:");
        verify(ioService, times(1)).println(TEST_STRING_DATA);
    }

    @Test
    @DisplayName("LibraryServiceImpl.showAllBooksByGenre")
    void testShowAllBooksByGenreID() {
        List<AuthorsMDB> authors = new ArrayList<>();
        List<BooksMDB> books = new ArrayList<>();
        GenresMDB genre = new GenresMDB(TEST_STRING_DATA);

        authors.add(new AuthorsMDB(new ObjectId("5dadde8600086d41e4b79eaa"),TEST_STRING_DATA,TEST_STRING_DATA,TEST_STRING_DATA));
        books.add(new BooksMDB(new ObjectId("5dadde8600086d41e4b79eab"),TEST_STRING_DATA,TEST_STRING_DATA,0,TEST_STRING_DATA, authors, Arrays.asList(genre)));
        given(booksMDBRepository.findAllByGenresIsContaining(genre)).willReturn(books);

        libraryService.showAllBooksByGenre(TEST_STRING_DATA);

        verify(booksMDBRepository, times(1)).findAllByGenresIsContaining(genre);
        verify(ioService, times(1)).println("Книги по жанру:");
        verify(ioService, times(1)).println("ID:\t\t\t5dadde8600086d41e4b79eab" +
                "\nНазвание:\t" + TEST_STRING_DATA +
                "\nОписание:\t" + TEST_STRING_DATA +
                "\nISBN:\t\t" + TEST_STRING_DATA +
                "\nЖанр(ы):\t" + "["+TEST_STRING_DATA+"]" +
                "\nАвтор(ы):\t" + "[ID: 5dadde8600086d41e4b79eaa, Ф.И.О: "+TEST_STRING_DATA+" "+TEST_STRING_DATA+" "+TEST_STRING_DATA+"]");
        verify(ioService, times(1)).println("------------------------------------------------------------");
    }

    @Test
    @DisplayName("LibraryServiceImpl.showAllBooksByAuthorID")
    void testShowAllBooksByAuthorID() {
        List<BooksMDB> books = new ArrayList<>();
        GenresMDB genre = new GenresMDB(TEST_STRING_DATA);

        AuthorsMDB author = new AuthorsMDB(new ObjectId("5dadde8600086d41e4b79eaa"),TEST_STRING_DATA,TEST_STRING_DATA,TEST_STRING_DATA);
        Optional<AuthorsMDB> optionalAuthorsMDB = Optional.ofNullable(author);

        books.add(new BooksMDB(new ObjectId("5dadde8600086d41e4b79eab"),TEST_STRING_DATA,TEST_STRING_DATA,0,TEST_STRING_DATA, Arrays.asList(author), Arrays.asList(genre)));
        given(authorsMDBRepository.findById(new ObjectId("5dadde8600086d41e4b79eaa"))).willReturn(optionalAuthorsMDB);
        given(booksMDBRepository.findAllByAuthorsIs(author)).willReturn(books);

        libraryService.showAllBooksByAuthorID("5dadde8600086d41e4b79eaa");

        verify(booksMDBRepository, times(1)).findAllByAuthorsIs(author);
        verify(ioService, times(1)).println("Книги " + TEST_STRING_DATA + " "
                + TEST_STRING_DATA.substring(0,1) + "." + TEST_STRING_DATA.substring(0,1) + "." + ":");
        verify(ioService, times(1)).println("ID:\t\t\t5dadde8600086d41e4b79eab" +
                "\nНазвание:\t" + TEST_STRING_DATA +
                "\nОписание:\t" + TEST_STRING_DATA +
                "\nISBN:\t\t" + TEST_STRING_DATA +
                "\nЖанр(ы):\t" + "["+TEST_STRING_DATA+"]" +
                "\nАвтор(ы):\t" + "[ID: 5dadde8600086d41e4b79eaa, Ф.И.О: "+TEST_STRING_DATA+" "+TEST_STRING_DATA+" "+TEST_STRING_DATA+"]");
        verify(ioService, times(1)).println("------------------------------------------------------------");
    }
}

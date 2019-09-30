package ru.otus.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;
import ru.otus.library.repository.AuthorsRepository;
import ru.otus.library.repository.LibraryRepository;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private LibraryRepository libraryRepository;
    private AuthorsRepository authorsRepository;
    private IOService ioService;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository, IOService ioService, AuthorsRepository authorsRepository) {
        this.libraryRepository = libraryRepository;
        this.authorsRepository = authorsRepository;
        this.ioService = ioService;
    }
    @Override
    public void showInfo() {
        ioService.println("В наличии книги, следующих авторов:");
        for (Authors a : libraryRepository.findAllAuthors()) {
            ioService.println(a.toMyString());
        }
        ioService.println("В наличии книги, следующих жанров:");
        for (Genres g : libraryRepository.findAllGenres()) {
            ioService.println(g.toMyString());
        }
    }
    @Override
    public void showAllBooksByGenreID(long id) {

        List<Books> books = libraryRepository.findAllByGenreID(id);

        ioService.println("Книги по жанру:");
        for (Books bg : books) {
            ioService.println((books.indexOf(bg)+1) + ": " + bg.getBook_name());
        }
    }
    @Override
    public void showAllBooksByAuthorID(long id) {

        List<Books> books = libraryRepository.findAllByAuthorID(id);

        ioService.println("Книги по автору:");
        for (Books bg : books) {
            ioService.println((books.indexOf(bg)+1) + ": " + bg.getBook_name());
        }
    }
    @Override
    public void showRandomBook() {
        String tmp = "";
        Books books = libraryRepository.findRandomBook();

        ioService.println("Случайная книга:");

        tmp = "ID: " + books.getBook_id() + ", Название: \"" + books.getBook_name() + "\"";
        if (books.getGenres().size() > 0) {
            tmp = tmp + "; Жанр(ы): ";
            for (Genres g : books.getGenres()) {
                tmp = tmp + g.getGenre_name() + (books.getGenres().indexOf(g)+1 < books.getGenres().size() ? ", " : "");
            }
        }

        if (books.getAuthors().size() > 0) {
            tmp = tmp + "; Автор(ы): ";
            for (Authors g : books.getAuthors()) {
                tmp = tmp + g.getAuthor_family() +
                        (g.getAuthor_name() != null ? " " + g.getAuthor_name().substring(0,1) + "." : "") +
                        (g.getAuthor_patronymic() != null ? " " + g.getAuthor_patronymic().substring(0,1) + "." : "") +
                        (books.getAuthors().indexOf(g)+1 < books.getAuthors().size() ? ", " : "");
            }
        }

        ioService.println(tmp);
    }

    @Override
    public void insertAuthor() {
        Authors author = new Authors();
        ioService.println("Укажите Фамилию, Имя, Отчество автора (каждое поле в новой строке)");
        author.setAuthor_family(ioService.readString());
        author.setAuthor_name(ioService.readString());
        author.setAuthor_patronymic(ioService.readString());
        authorsRepository.save(author);
    }
}

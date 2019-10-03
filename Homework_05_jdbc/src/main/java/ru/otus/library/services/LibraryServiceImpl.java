package ru.otus.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;
import ru.otus.library.repository.AuthorsRepository;
import ru.otus.library.repository.BooksRepository;
import ru.otus.library.repository.GenresRepository;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private AuthorsRepository authorsRepository;
    private GenresRepository genresRepository;
    private BooksRepository booksRepository;
    private IOService ioService;

    @Autowired
    public LibraryServiceImpl(IOService ioService, AuthorsRepository authorsRepository, GenresRepository genresRepository, BooksRepository booksRepository) {
        this.authorsRepository = authorsRepository;
        this.genresRepository = genresRepository;
        this.booksRepository = booksRepository;
        this.ioService = ioService;
    }
    @Override
    public void showInfo() {
        ioService.println("В наличии книги, следующих авторов:");
        for (Authors a : authorsRepository.findAll()) {
            ioService.println(a.toMyString());
        }
        ioService.println("В наличии книги, следующих жанров:");
        for (Genres g : genresRepository.findAll()) {
            ioService.println(g.toMyString());
        }
    }
    @Override
    public void showAllBooksByGenreID(long id) {

        List<Books> books = booksRepository.findAllByGenreID(id);

        ioService.println("Книги по жанру:");
        for (Books bg : books) {
            ioService.println((books.indexOf(bg)+1) + ": " + bg.getBook_name());
        }
    }
    @Override
    public void showAllBooksByAuthorID(long id) {

        List<Books> books = booksRepository.findAllByAuthorID(id);

        ioService.println("Книги по автору:");
        for (Books bg : books) {
            ioService.println((books.indexOf(bg)+1) + ": " + bg.getBook_name());
        }
    }

    @Override
    public void insertAuthor() {
        Authors author = new Authors();
        ioService.println("Укажите Фамилию, Имя, Отчество автора (каждое поле в новой строке)");
        author.setAuthor_family(ioService.readString());
        author.setAuthor_name(ioService.readString());
        author.setAuthor_patronymic(ioService.readString());
        author = authorsRepository.save(author);
        ioService.println("Присвоен ID: " + author.getAuthor_id());
    }
}

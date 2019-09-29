package ru.otus.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;
import ru.otus.library.repository.LibraryRepository;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private LibraryRepository libraryRepository;
    private IOService ioService;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository, IOService ioService) {
        this.libraryRepository = libraryRepository;
        this.ioService = ioService;
    }
    @Override
    public void showInfo() {
        ioService.println("В наличии книги, следующих авторов:");
        for (Authors a : libraryRepository.findAllAuthors()) {
            ioService.println(a.toString());
        }
        ioService.println("В наличии книги, следующих жанров:");
        for (Genres g : libraryRepository.findAllGenres()) {
            ioService.println(g.toString());
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
}

package ru.otus.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;
import ru.otus.library.repository.AuthorsRepository;
import ru.otus.library.repository.BooksRepository;
import ru.otus.library.repository.GenresRepository;
import ru.otus.library.repository.LibraryRepository;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private BooksRepository booksRepository;
    private GenresRepository genresRepository;
    private AuthorsRepository authorsRepository;
    private LibraryRepository libraryRepository;

    @Autowired
    public LibraryServiceImpl(BooksRepository booksRepository, GenresRepository genresRepository, AuthorsRepository authorsRepository, LibraryRepository libraryRepository) {
        this.booksRepository = booksRepository;
        this.genresRepository = genresRepository;
        this.authorsRepository = authorsRepository;
        this.libraryRepository = libraryRepository;
    }
    @Override
    public void showInfo() {
        System.out.println("В наличии книги, следующих авторов:");
        for (Authors a : libraryRepository.findAllAuthors()) {
            System.out.println(a.toString());
        }
        System.out.println("В наличии книги, следующих жанров:");
        for (Genres g : libraryRepository.findAllGenres()) {
            System.out.println(g.toString());
        }
    }
    @Override
    public void findAllBooksByGenre(long id) {

        List<Books> books = booksRepository.findAllByGenre(genresRepository.findById(id).get());

        System.out.println("Книги по жанру:");
        for (Books bg : books) {
            System.out.println((books.indexOf(bg)+1) + ": " + bg.getBook_name());
        }
    }
    @Override
    public void findAllBooksByAuthor(long id) {

        List<Books> books = booksRepository.findAllByAuthor(authorsRepository.findById(id).get());

        System.out.println("Книги по автору:");
        for (Books bg : books) {
            System.out.println((books.indexOf(bg)+1) + ": " + bg.getBook_name());
        }
    }
}

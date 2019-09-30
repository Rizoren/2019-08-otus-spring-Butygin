package ru.otus.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.services.LibraryServiceImpl;

@ShellComponent
public class ShellConsole {

    private LibraryServiceImpl libraryService;

    @Autowired
    public ShellConsole(LibraryServiceImpl libraryService) {
        this.libraryService = libraryService;
    }

    @ShellMethod(value = "Show info about Library", key = {"info","show-info"})
    public void showInfo() {
        libraryService.showInfo();
    }

    @ShellMethod(value = "Get list of books by one genre id", key = {"bbg","books-by-genre"})
    public void showAllBooksByGenre(long genre_id) {
        libraryService.showAllBooksByGenreID(genre_id);
    }

    @ShellMethod(value = "Get list of books by one author id", key = {"bba","books-by-author"})
    public void showAllBooksByAuthor(long author_id) {
        libraryService.showAllBooksByAuthorID(author_id);
    }

    @ShellMethod(value = "Get random book", key = {"rb","random-book"})
    public void showRandomBook() {
        libraryService.showRandomBook();
    }

}

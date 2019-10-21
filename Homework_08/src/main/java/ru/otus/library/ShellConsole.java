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

    @ShellMethod(value = "Get list of books by one genre name", key = {"bbg","books-by-genre"})
    public void showAllBooksByGenre(String genre) {
        libraryService.showAllBooksByGenre(genre);
    }

    @ShellMethod(value = "Get list of books by one author id", key = {"bba","books-by-author"})
    public void showAllBooksByAuthor(String author_id) {
        libraryService.showAllBooksByAuthorID(author_id);
    }

    @ShellMethod(value = "Insert author", key = {"ia","insert-author"})
    public void insertAuthor() {
        libraryService.insertAuthor();
    }
}

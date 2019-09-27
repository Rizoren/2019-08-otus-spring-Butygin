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
    public void showInfo() throws Exception {
        libraryService.showInfo();
    }

    @ShellMethod(value = "Get list of books by one genre id", key = {"bbg","books-by-genre"})
    public void findAllBooksByGenre(long genre_id) throws Exception {
        libraryService.findAllBooksByGenre(genre_id);
    }

    @ShellMethod(value = "Get list of books by one author id", key = {"bba","books-by-author"})
    public void findAllBooksByAuthor(long author_id) throws Exception {
        libraryService.findAllBooksByAuthor(author_id);
    }
}

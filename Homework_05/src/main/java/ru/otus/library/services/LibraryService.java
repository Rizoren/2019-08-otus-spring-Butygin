package ru.otus.library.services;

public interface LibraryService {

    void showInfo();
    void findAllBooksByGenre(long id);
    void findAllBooksByAuthor(long id);

}

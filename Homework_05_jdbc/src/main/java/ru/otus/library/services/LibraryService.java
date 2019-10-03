package ru.otus.library.services;

public interface LibraryService {

    void showInfo();
    void showAllBooksByGenreID(long id);
    void showAllBooksByAuthorID(long id);
    void insertAuthor();
}

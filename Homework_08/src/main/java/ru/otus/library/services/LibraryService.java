package ru.otus.library.services;

public interface LibraryService {

    void showInfo();
    void showAllBooksByGenre(String genre);
    void showAllBooksByAuthorID(String id);
    void insertAuthor();
}

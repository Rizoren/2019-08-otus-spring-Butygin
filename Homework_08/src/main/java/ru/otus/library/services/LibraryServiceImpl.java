package ru.otus.library.services;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.library.model.*;
import ru.otus.library.repository.*;

@Service
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    final private IOService ioService;
    final private AuthorsMDBRepository authorsMDBRepository;
    final private BooksMDBRepository booksMDBRepository;

    @Override
    public void showInfo() {
        ioService.println("В наличии книги, следующих авторов:");
        authorsMDBRepository.findAll().forEach(a -> ioService.println(a.toString()));

        ioService.println("В наличии книги, следующих жанров:");
        booksMDBRepository.findDistinctGenresMDB().forEach(g -> ioService.println(g.toString()));
    }

    @Override
    public void showAllBooksByGenre(String genre) {
        ioService.println("Книги по жанру:");
        booksMDBRepository.findAllByGenresIsContaining(new GenresMDB(genre)).forEach(b -> {
            ioService.println(b.toString());
            ioService.println("------------------------------------------------------------");
        });
    }

    @Override
    public void showAllBooksByAuthorID(String id) {
        AuthorsMDB authorsMDB = authorsMDBRepository.findById(new ObjectId(id)).orElse(null);
        ioService.println("Книги " + (authorsMDB.getFamily() != null ? authorsMDB.getFamily() + " " : "")
                                   + (authorsMDB.getName() != null ? authorsMDB.getName().substring(0,1) + "." : "")
                                   + (authorsMDB.getPatronymic() != null ? authorsMDB.getPatronymic().substring(0,1) + "." : "") + ":");
        booksMDBRepository.findAllByAuthorsIs(authorsMDB).forEach(b -> {
            ioService.println(b.toString());
            ioService.println("------------------------------------------------------------");
        });
    }

    @Override
    public void insertAuthor() {
        AuthorsMDB author = new AuthorsMDB();
        ioService.println("Укажите Фамилию, Имя, Отчество автора (каждое поле в новой строке)");
        author.setFamily(ioService.readString());
        author.setName(ioService.readString());
        author.setPatronymic(ioService.readString());
        author = authorsMDBRepository.save(author);
        ioService.println("Присвоен ID: " + author.get_id().toString());
    }
}

package ru.otus.library.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.library.model.AuthorsMDB;
import ru.otus.library.model.BooksMDB;
import ru.otus.library.model.GenresMDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private List<BooksMDB> booksMDB;
    private AuthorsMDB pyshkin, lermontov, rouling;

    @ChangeSet(order = "000", id = "dropDB", author = "springmdb", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "springmdb", runAlways = true)
    public void initAuthors(MongoTemplate template){
        pyshkin = template.save(new AuthorsMDB("Пушкин","Александр","Сергеевич"), "authors");
        lermontov = template.save(new AuthorsMDB("Лермонтов","Михаил","Юрьевич"),"authors");
        rouling = template.save(new AuthorsMDB("Роулинг","Джоан",null),"authors");
    }

    @ChangeSet(order = "002", id = "initBooks", author = "springmdb", runAlways = true)
    public void initBooks(MongoTemplate template){
        booksMDB = new ArrayList<>();
        booksMDB.add(new BooksMDB("Руслан и Людмила","первая законченная поэма Александра Сергеевича Пушкина; волшебная сказка, вдохновлённая древнерусскими былинами",1820,"000-0-00-000001-0",
                Arrays.asList(pyshkin), Arrays.asList(new GenresMDB("поэма"), new GenresMDB("русская сказка"))));
        booksMDB.add(new BooksMDB("Демон","поэма Михаила Юрьевича Лермонтова, над которой поэт работал в течение десяти лет - с 1829 по 1839 год",1856,"000-0-00-000002-0",
                Arrays.asList(lermontov), Arrays.asList(new GenresMDB("поэма"))));
        booksMDB.add(new BooksMDB("Гарри Поттер и философский камень","первый роман в серии книг про юного волшебника Гарри Поттера, написанный Дж. К. Роулинг",1997,"000-0-00-000003-0",
                Arrays.asList(rouling), Arrays.asList(new GenresMDB("фэнтези"))));
        booksMDB.add(new BooksMDB("Гарри Поттер и Тайная комната","второй роман в серии книг про юного волшебника Гарри Поттера, написанный Дж. К. Роулинг",1998,"000-0-00-000004-0",
                Arrays.asList(rouling), Arrays.asList(new GenresMDB("фэнтези"))));
        template.insert(booksMDB, "books");
    }
}

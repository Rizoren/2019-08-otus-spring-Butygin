package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class BooksMDB {

    @Id
    private ObjectId _id;
    private String book_name;
    private String book_description;
    private Integer book_year;
    private String book_isbn;

    @DBRef
    private List<AuthorsMDB> authors;

    private List<GenresMDB> genres;

    public BooksMDB(String book_name, String book_description, Integer book_year, String book_isbn, List<AuthorsMDB> authors, List<GenresMDB> genres) {
        this.book_name = book_name;
        this.book_description = book_description;
        this.book_year = book_year;
        this.book_isbn = book_isbn;
        this.authors = authors;
        this.genres = genres;
    }

    public String toString() {
        return  "ID:\t\t\t" + (this._id != null ? this._id.toString() : "") +
                "\nНазвание:\t" + (this.book_name != null ? this.book_name : "") +
                "\nОписание:\t" + (this.book_description != null ? this.book_description : "") +
                "\nISBN:\t\t" + (this.book_isbn != null ? this.book_isbn : "") +
                "\nЖанр(ы):\t" + (this.genres.size() > 0 ? this.genres.toString() : "" ) +
                "\nАвтор(ы):\t" + (this.authors.size() > 0 ? this.authors.toString() : "" );
    }

}

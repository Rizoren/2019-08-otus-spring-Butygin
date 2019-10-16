package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Data
@Document("books")
public class BooksMDB {

    @Id
    private ObjectId _id;

    @Basic
    @Column(name = "book_name", nullable = true, unique = false)
    private String book_name;

    @Basic
    @Column(name = "book_description", nullable = true, unique = false)
    private String book_description;

    @Basic
    @Column(name = "book_year", nullable = true, unique = false)
    private Integer book_year;

    @Basic
    @Column(name = "book_isbn", nullable = true, unique = false)
    private String book_isbn;

//    @ManyToMany(targetEntity = Authors.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = @JoinColumn(name = "author_id"))
//    @Fetch(value = FetchMode.SUBSELECT)
    @DBRef
    private List<AuthorsMDB> authors;

//    @ManyToMany(targetEntity = Genres.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = @JoinColumn(name = "genre_id"))
//    @Fetch(value = FetchMode.SUBSELECT)
    private List<GenresMDB> genres;

    public String toString() {
        return  "ID:\t\t\t" + this._id.toString() +
                "\nНазвание:\t" + (this.book_name != null ? this.book_name : "") +
                "\nОписание:\t" + (this.book_description != null ? this.book_description : "") +
                "\nISBN:\t\t" + (this.book_isbn != null ? this.book_isbn : "") +
                "\nЖанр(ы):\t" + (this.genres.size() > 0 ? this.genres.toString() : "" ) +
                "\nАвтор(ы):\t" + (this.authors.size() > 0 ? this.authors.toString() : "" );
    }

}

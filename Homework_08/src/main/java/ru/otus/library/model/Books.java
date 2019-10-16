package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="books_book_id_seq")
    @SequenceGenerator(name = "books_book_id_seq", sequenceName = "books_book_id_seq", allocationSize = 1)
    @Column(name = "book_id", nullable = false)
    private long book_id;

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

    @ManyToMany(targetEntity = Authors.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Authors> authors;

    @ManyToMany(targetEntity = Genres.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Genres> genres;

}

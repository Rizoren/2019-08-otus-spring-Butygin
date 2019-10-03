package ru.otus.library.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    private long book_id;
    private String book_name;
    private String book_description;
    private Integer book_year;
    private String book_isbn;
    private List<Authors> authors;
    private List<Genres> genres;

}

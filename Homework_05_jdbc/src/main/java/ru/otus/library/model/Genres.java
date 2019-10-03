package ru.otus.library.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genres {

    private long genre_id;
    private String genre_name;

    public String toMyString() {
        return "ID: " + this.genre_id + ", " +
               "Наименование: " + (this.genre_name != null ? this.genre_name : "");
    }
}

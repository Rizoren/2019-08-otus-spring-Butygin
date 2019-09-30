package ru.otus.library.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genres")
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="genres_genre_id_seq")
    @SequenceGenerator(name = "genres_genre_id_seq", sequenceName = "genres_genre_id_seq", allocationSize = 1)
    @Column(name = "genre_id", nullable = false)
    private long genre_id;

    @Basic
    @Column(name = "genre_name", nullable = false, unique = true)
    private String genre_name;

    //Так не красиво делать, но нехочу пока выносить в отдельную функцию в сервис и т.д.
    public String toMyString() {
        return "ID: " + this.genre_id + ", " +
               "Наименование: " + (this.genre_name != null ? this.genre_name : "");
    }
}

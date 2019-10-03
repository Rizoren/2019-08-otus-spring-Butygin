package ru.otus.library.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="authors_author_id_seq")
    @SequenceGenerator(name = "authors_author_id_seq", sequenceName = "authors_author_id_seq", allocationSize = 1)
    @Column(name = "author_id", nullable = false)
    private long author_id;

    @Basic
    @Column(name = "author_family", nullable = true, unique = false)
    private String author_family;

    @Basic
    @Column(name = "author_name", nullable = true, unique = false)
    private String author_name;

    @Basic
    @Column(name = "author_patronymic", nullable = true, unique = false)
    private String author_patronymic;

    public String toMyString() {
        return "ID: " + this.author_id + ", " +
               "Ф.И.О: " + (this.author_family != null ? this.author_family : "") +
                (this.author_name != null ? " " + this.author_name : "") +
                (this.author_patronymic != null ? " " + this.author_patronymic : "");
    }

}

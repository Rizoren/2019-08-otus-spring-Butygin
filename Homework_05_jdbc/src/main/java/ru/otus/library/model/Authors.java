package ru.otus.library.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authors {

    private long author_id;
    private String author_family;
    private String author_name;
    private String author_patronymic;

    public String toMyString() {
        return "ID: " + this.author_id + ", " +
               "Ф.И.О: " + (this.author_family != null ? this.author_family : "") +
                (this.author_name != null ? " " + this.author_name : "") +
                (this.author_patronymic != null ? " " + this.author_patronymic : "");
    }

}

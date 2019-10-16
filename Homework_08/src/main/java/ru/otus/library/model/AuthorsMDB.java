package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Document("authors")
public class AuthorsMDB {

    @Id
    private ObjectId _id;

    @Basic
    @Column(name = "family", nullable = true, unique = false)
    private String family;

    @Basic
    @Column(name = "name", nullable = true, unique = false)
    private String name;

    @Basic
    @Column(name = "patronymic", nullable = true, unique = false)
    private String patronymic;

    public String toString() {
        return "ID: " + this._id.toString() + ", " +
               "Ф.И.О: " + (this.family != null ? this.family : "") +
                (this.name != null ? " " + this.name : "") +
                (this.patronymic != null ? " " + this.patronymic : "");
    }

}

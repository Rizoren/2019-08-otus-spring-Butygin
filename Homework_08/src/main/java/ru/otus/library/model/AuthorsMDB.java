package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class AuthorsMDB {

    @Id
    private ObjectId _id;
    private String family;
    private String name;
    private String patronymic;

    public AuthorsMDB(String family, String name, String patronymic) {
        this.family = family;
        this.name = name;
        this.patronymic = patronymic;
    }

    public String toString() {
        return "ID: " + (this._id != null ? this._id.toString() : "") + ", " +
               "Ф.И.О: " + (this.family != null ? this.family : "") +
                (this.name != null ? " " + this.name : "") +
                (this.patronymic != null ? " " + this.patronymic : "");
    }

}

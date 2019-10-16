package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
//@Document("genres")
public class GenresMDB {

    //@Id
    //private ObjectId _id;

    @Basic
    @Column(name = "genre_name", nullable = false, unique = true)
    private String genre_name;

    public String toString() {
        return (this.genre_name != null ? this.genre_name : "");
    }
}

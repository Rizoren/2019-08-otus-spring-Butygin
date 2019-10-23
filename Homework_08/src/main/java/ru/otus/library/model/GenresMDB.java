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
//@Document("genres")
public class GenresMDB {

    //@Id
    //private ObjectId _id;

    private String genre_name;

    public String toString() {
        return (this.genre_name != null ? this.genre_name : "");
    }
}

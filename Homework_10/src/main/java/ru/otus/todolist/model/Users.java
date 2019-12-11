package ru.otus.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.type.PostgresUUIDType;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_seq", sequenceName = "users_user_id_seq", allocationSize = 1)
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Basic
    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;
    @Basic
    @Column(name = "user_password", length = 50)
    private String userPassword;
/*
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    private List<Tasks> tasks = new ArrayList<>();
*/
    public Users(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}

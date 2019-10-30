package ru.otus.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.type.PostgresUUIDType;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Basic
    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;
    @Basic
    @Column(name = "user_password", nullable = true, length = 50)
    private String userPassword;
    @Basic
    @Column(name = "user_uuid", nullable = false)
    private UUID userUuid;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tasks> tasks;
}

package ru.otus.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.PostgresUUIDType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Tasks implements Serializable {
    @Id
    @Column(name = "task_id", nullable = false)
    private long taskId;
    @Basic
    @Column(name = "task_name", nullable = false, length = 50)
    private String taskName;
    @Basic
    @Column(name = "task_description", nullable = true, length = 200)
    private String taskDescription;
    @Basic
    @Column(name = "task_status", nullable = true)
    private Short taskStatus;
    @Basic
    @Column(name = "task_uuid", nullable = false)
    private UUID taskUuid;
    @Basic
    @Column(name = "task_enddate", nullable = true)
    private Timestamp taskEnddate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", referencedColumnName = "user_uuid")
    private Users users;

}

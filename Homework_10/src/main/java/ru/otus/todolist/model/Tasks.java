package ru.otus.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
@SequenceGenerator(name = "task_seq", sequenceName = "tasks_task_id_seq", allocationSize = 1)
public class Tasks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
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
    @Column(name = "task_enddate", nullable = true)
    private Timestamp taskEnddate;

    //TODO: I have problem... Recursive getting data
    //When I get Task-list, also get User and get task list in user...
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users users;

    @Override
    public String toString() {
        return "Tasks{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskEnddate=" + taskEnddate +
                '}';
    }
}

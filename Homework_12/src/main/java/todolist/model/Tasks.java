package todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
@SequenceGenerator(name = "task_seq", sequenceName = "tasks_task_id_seq", allocationSize = 1)
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @Column(name = "task_id", nullable = false)
    private long id;
    @Basic
    @Column(name = "task_name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "task_description", nullable = true, length = 200)
    private String description;
    @Basic
    @Column(name = "task_status", nullable = true)
    private Short status;
    @Basic
    @Column(name = "task_enddate", nullable = true)
    private Timestamp enddate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}

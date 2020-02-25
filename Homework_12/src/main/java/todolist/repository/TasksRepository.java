package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.model.Tasks;

import java.util.List;
import java.util.Optional;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    Optional<List<Tasks>> findAllByUser_Id(Long id);
    void deleteAllByUser_Id(Long id);
}

package br.ufla.dcc.todolistspringboot.persistence.repositories;

import br.ufla.dcc.todolistspringboot.persistence.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}

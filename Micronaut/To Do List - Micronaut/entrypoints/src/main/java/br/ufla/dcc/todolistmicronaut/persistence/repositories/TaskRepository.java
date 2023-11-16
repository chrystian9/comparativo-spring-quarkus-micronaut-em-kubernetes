package br.ufla.dcc.todolistmicronaut.persistence.repositories;

import br.ufla.dcc.todolistmicronaut.persistence.entities.TaskEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}

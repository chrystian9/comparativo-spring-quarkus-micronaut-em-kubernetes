package br.ufla.dcc.todolistmicronaut.persistence.repositories;

import br.ufla.dcc.todolistmicronaut.persistence.entities.TaskEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}

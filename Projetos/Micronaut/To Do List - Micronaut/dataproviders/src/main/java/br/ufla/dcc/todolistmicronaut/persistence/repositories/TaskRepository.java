package br.ufla.dcc.todolistmicronaut.persistence.repositories;

import br.ufla.dcc.todolistmicronaut.persistence.entities.TaskEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
}

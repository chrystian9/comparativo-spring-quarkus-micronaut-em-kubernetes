package br.ufla.dcc.todolistquarkus.persistence.mappers;

import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import br.ufla.dcc.todolistquarkus.persistence.entities.TaskEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskEntityMapper {
    private TaskFactory taskFactory;

    public Task toTask(TaskEntity taskEntity) throws DomainException {
        return taskFactory.recreateExistingTask(taskEntity.id,
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getDeadline(),
                taskEntity.getIsCompleted());
    }

    public TaskEntity toTaskEntity(Task task){
        return new TaskEntity(task.getTitle(),
                task.getDescription(),
                task.getDeadline(),
                task.isCompleted());
    }
}

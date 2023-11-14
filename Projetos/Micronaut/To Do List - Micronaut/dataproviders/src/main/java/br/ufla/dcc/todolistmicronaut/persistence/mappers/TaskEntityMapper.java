package br.ufla.dcc.todolistmicronaut.persistence.mappers;

import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import br.ufla.dcc.todolistmicronaut.persistence.entities.TaskEntity;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class TaskEntityMapper {
    private final TaskFactory taskFactory;

    @Inject
    public TaskEntityMapper(TaskFactory taskFactory){
        this.taskFactory = taskFactory;
    }

    public Task toTask(TaskEntity taskEntity) throws DomainException {
        return taskFactory.recreateExistingTask(taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getDeadline(),
                taskEntity.getIsCompleted());
    }

    public TaskEntity toTaskEntity(Task task){
        return new TaskEntity(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDeadline(),
                task.isCompleted());
    }
}

package br.ufla.dcc.todolistspringboot.persistence.mappers;

import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import br.ufla.dcc.todolistspringboot.persistence.entities.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskEntityMapper {
    private final TaskFactory taskFactory;

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

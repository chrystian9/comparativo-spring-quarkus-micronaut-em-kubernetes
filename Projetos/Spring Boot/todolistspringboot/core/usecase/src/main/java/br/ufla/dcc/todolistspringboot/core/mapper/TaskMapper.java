package br.ufla.dcc.todolistspringboot.core.mapper;

import br.ufla.dcc.todolistspringboot.core.dtos.TaskDTO;
import br.ufla.dcc.todolistspringboot.core.task.Task;

public class TaskMapper {
    public TaskDTO toDTO(Task task){
        return new TaskDTO(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDeadline(),
                task.isCompleted());
    }
}

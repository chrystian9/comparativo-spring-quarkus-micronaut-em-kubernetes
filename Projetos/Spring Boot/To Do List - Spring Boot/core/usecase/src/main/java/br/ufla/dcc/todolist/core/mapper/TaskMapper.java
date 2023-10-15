package br.ufla.dcc.todolist.core.mapper;

import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.dtos.TaskDTO;

public class TaskMapper {
    public TaskDTO toDTO(Task task){
        return new TaskDTO(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDeadline(),
                task.isCompleted());
    }
}

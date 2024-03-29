package br.ufla.dcc.todolist.core.ports.input.impl;

import br.ufla.dcc.todolist.core.ports.input.ChangeTaskDeadlineUseCase;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.DomainValidationException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
public class ChangeTaskDeadlineUseCaseImpl implements ChangeTaskDeadlineUseCase {
    private final TaskOutputPort taskOutputPort;
    private final TaskMapper taskMapper;

    @Override
    public TaskDTO changeTaskDealineById(Long id, LocalDateTime newDeadline) throws CoreException {
        Optional<Task> existingTask = taskOutputPort.getById(id);

        if(!existingTask.isPresent()) {
            throw new CoreException(String.format("Task id %s was not found", id));
        }

        Task task = existingTask.get();

        try {
            task = task.changeDeadline(newDeadline);
        } catch (DomainException exception){
            throw new DomainValidationException(exception.getMessage());
        }

        taskOutputPort.update(task);

        return taskMapper.toDTO(task);
    }
}

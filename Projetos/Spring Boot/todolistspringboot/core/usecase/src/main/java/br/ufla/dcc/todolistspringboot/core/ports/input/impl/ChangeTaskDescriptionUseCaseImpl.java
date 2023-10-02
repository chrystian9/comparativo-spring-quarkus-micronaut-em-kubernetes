package br.ufla.dcc.todolistspringboot.core.ports.input.impl;

import br.ufla.dcc.todolistspringboot.core.dtos.TaskDTO;
import br.ufla.dcc.todolistspringboot.core.mapper.TaskMapper;
import br.ufla.dcc.todolistspringboot.core.ports.input.ChangeTaskDescriptionUseCase;
import br.ufla.dcc.todolistspringboot.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.causes.DomainValidationException;
import br.ufla.dcc.todolistspringboot.core.task.Task;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ChangeTaskDescriptionUseCaseImpl implements ChangeTaskDescriptionUseCase {
    private final TaskOutputPort taskOutputPort;
    private final TaskMapper taskMapper;

    @Override
    public TaskDTO changeTaskDescriptionById(Long id, String newDescription) throws CoreException {
        Optional<Task> existingTask = taskOutputPort.getById(id);

        if(!existingTask.isPresent()) {
            throw new CoreException(String.format("Task id %s was not found", id));
        }

        Task task = existingTask.get();

        try {
            task = task.changeDescription(newDescription);
        } catch (DomainException exception){
            throw new DomainValidationException(exception.getMessage());
        }

        taskOutputPort.update(task);

        return taskMapper.toDTO(task);
    }
}

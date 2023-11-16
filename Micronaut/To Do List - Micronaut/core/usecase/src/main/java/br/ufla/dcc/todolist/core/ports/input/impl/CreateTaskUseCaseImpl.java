package br.ufla.dcc.todolist.core.ports.input.impl;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.ports.input.CreateTaskUseCase;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.DomainValidationException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {
    private final TaskOutputPort taskOutputPort;
    private final TaskMapper taskMapper;
    private final TaskFactory taskFactory;

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) throws CoreException {
        Task task;

        try {
            task = taskFactory.createTask(taskDTO.title(), taskDTO.description(), taskDTO.deadline());
        } catch (DomainException e) {
            throw new DomainValidationException(e.getMessage());
        }

        task = taskOutputPort.create(task);

        return taskMapper.toDTO(task);
    }
}

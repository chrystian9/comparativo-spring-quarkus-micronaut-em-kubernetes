package br.ufla.dcc.todolist.core.ports.input.impl;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.ports.input.GetTaskUseCase;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.task.Task;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class GetTaskUseCaseImpl implements GetTaskUseCase {
    private final TaskOutputPort taskOutputPort;
    private final TaskMapper taskMapper;

    @Override
    public TaskDTO getById(Long id) throws CoreException {
        Optional<Task> existingTask = taskOutputPort.getById(id);

        if(!existingTask.isPresent()) {
            throw new CoreException(String.format("Task id %s was not found", id));
        }

        return taskMapper.toDTO(existingTask.get());
    }
}

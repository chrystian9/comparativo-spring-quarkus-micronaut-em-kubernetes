package br.ufla.dcc.todolist.core.ports.input.impl;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.ports.input.GetAllTaskUseCase;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.OutputPortException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllTaskUseCaseImpl implements GetAllTaskUseCase {
    private final TaskOutputPort taskOutputPort;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDTO> getAll() throws CoreException {
        return taskOutputPort.getAll()
                .stream()
                .map(taskMapper::toDTO)
                .toList();
    }
}

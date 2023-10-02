package br.ufla.dcc.todolistspringboot.core.ports.input.impl;

import br.ufla.dcc.todolistspringboot.core.dtos.TaskDTO;
import br.ufla.dcc.todolistspringboot.core.mapper.TaskMapper;
import br.ufla.dcc.todolistspringboot.core.ports.input.GetAllTaskUseCase;
import br.ufla.dcc.todolistspringboot.core.ports.output.TaskOutputPort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllTaskUseCaseImpl implements GetAllTaskUseCase {
    private final TaskOutputPort taskOutputPort;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDTO> getAll() {
        return taskOutputPort.getAll()
                .stream()
                .map(taskMapper::toDTO)
                .toList();
    }
}

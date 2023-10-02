package br.ufla.dcc.todolistspringboot.core.ports.input;

import br.ufla.dcc.todolistspringboot.core.dtos.TaskDTO;

import java.util.List;

public interface GetAllTaskUseCase {
    List<TaskDTO> getAll();
}

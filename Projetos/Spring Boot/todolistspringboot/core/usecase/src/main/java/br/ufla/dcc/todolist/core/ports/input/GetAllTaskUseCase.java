package br.ufla.dcc.todolist.core.ports.input;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;

import java.util.List;

public interface GetAllTaskUseCase {
    List<TaskDTO> getAll();
}

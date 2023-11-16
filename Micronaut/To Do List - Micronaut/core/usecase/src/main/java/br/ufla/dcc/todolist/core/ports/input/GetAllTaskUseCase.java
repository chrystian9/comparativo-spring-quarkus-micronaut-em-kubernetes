package br.ufla.dcc.todolist.core.ports.input;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.OutputPortException;

import java.util.List;

public interface GetAllTaskUseCase {
    List<TaskDTO> getAll() throws CoreException;
}

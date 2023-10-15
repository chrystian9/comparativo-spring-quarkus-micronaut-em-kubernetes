package br.ufla.dcc.todolist.core.ports.input;

import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.dtos.TaskDTO;

public interface UncompletedTaskUseCase {
    TaskDTO uncompletedTaskById(Long id) throws CoreException;
}

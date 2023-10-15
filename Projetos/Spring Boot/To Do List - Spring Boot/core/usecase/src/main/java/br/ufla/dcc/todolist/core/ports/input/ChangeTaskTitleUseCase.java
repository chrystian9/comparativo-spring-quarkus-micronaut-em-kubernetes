package br.ufla.dcc.todolist.core.ports.input;

import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.dtos.TaskDTO;

public interface ChangeTaskTitleUseCase {
    TaskDTO changeTaskTitleById(Long id, String newTitle) throws CoreException;
}
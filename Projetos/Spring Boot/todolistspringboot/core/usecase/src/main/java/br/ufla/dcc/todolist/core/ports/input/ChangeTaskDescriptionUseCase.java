package br.ufla.dcc.todolist.core.ports.input;

import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.dtos.TaskDTO;

public interface ChangeTaskDescriptionUseCase {
    TaskDTO changeTaskDescriptionById(Long id, String newDescription) throws CoreException;
}
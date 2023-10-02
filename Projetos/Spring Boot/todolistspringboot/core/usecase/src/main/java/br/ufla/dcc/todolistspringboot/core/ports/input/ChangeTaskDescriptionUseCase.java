package br.ufla.dcc.todolistspringboot.core.ports.input;

import br.ufla.dcc.todolistspringboot.core.dtos.TaskDTO;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.CoreException;

public interface ChangeTaskDescriptionUseCase {
    TaskDTO changeTaskDescriptionById(Long id, String newDescription) throws CoreException;
}
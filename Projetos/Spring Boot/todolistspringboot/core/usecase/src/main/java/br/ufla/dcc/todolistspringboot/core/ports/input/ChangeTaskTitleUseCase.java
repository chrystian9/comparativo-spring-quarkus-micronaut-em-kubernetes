package br.ufla.dcc.todolistspringboot.core.ports.input;

import br.ufla.dcc.todolistspringboot.core.dtos.TaskDTO;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.CoreException;

public interface ChangeTaskTitleUseCase {
    TaskDTO changeTaskTitleById(Long id, String newTitle) throws CoreException;
}
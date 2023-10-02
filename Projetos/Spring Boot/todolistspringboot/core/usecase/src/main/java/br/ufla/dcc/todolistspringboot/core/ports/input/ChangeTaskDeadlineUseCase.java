package br.ufla.dcc.todolistspringboot.core.ports.input;

import br.ufla.dcc.todolistspringboot.core.dtos.TaskDTO;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.CoreException;

import java.time.LocalDateTime;

public interface ChangeTaskDeadlineUseCase {
    TaskDTO changeTaskDealineById(Long id, LocalDateTime newDeadline) throws CoreException;
}

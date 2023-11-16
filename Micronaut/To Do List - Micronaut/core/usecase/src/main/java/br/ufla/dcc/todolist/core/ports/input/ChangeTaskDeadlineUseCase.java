package br.ufla.dcc.todolist.core.ports.input;

import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.dtos.TaskDTO;

import java.time.LocalDateTime;

public interface ChangeTaskDeadlineUseCase {
    TaskDTO changeTaskDealineById(Long id, LocalDateTime newDeadline) throws CoreException;
}

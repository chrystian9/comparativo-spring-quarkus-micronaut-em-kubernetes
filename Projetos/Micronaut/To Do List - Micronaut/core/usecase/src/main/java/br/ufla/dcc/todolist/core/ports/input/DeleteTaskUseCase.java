package br.ufla.dcc.todolist.core.ports.input;

import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;

public interface DeleteTaskUseCase {
    void deleteTaskById(Long id) throws CoreException;
}

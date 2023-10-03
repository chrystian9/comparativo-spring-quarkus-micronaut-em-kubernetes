package br.ufla.dcc.todolist.core.ports.output;

import br.ufla.dcc.todolist.core.shared.exceptions.causes.OutputPortException;
import br.ufla.dcc.todolist.core.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskOutputPort {
    Task create(Task task) throws OutputPortException;
    Optional<Task> getById(Long id) throws OutputPortException;
    List<Task> getAll() throws OutputPortException;
    void deleteById(Long id) throws OutputPortException;
    Task update(Task task) throws OutputPortException;
}

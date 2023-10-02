package br.ufla.dcc.todolist.core.ports.output;

import br.ufla.dcc.todolist.core.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskOutputPort {
    Task create(Task task);
    Optional<Task> getById(Long id);
    List<Task> getAll();
    void deleteById(Long id);
    Task update(Task task);
}

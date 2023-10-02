package br.ufla.dcc.todolistspringboot.persistence.adapters;

import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.task.Task;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskPersistenceAdapter implements TaskOutputPort {
    @Override
    public Task create(Task task) {
        return null; // TODO
    }

    @Override
    public Optional<Task> getById(Long id) {
        return Optional.empty(); // TODO
    }

    @Override
    public List<Task> getAll() {
        return null; // TODO
    }

    @Override
    public void deleteById(Long id) {
        // TODO
    }

    @Override
    public Task update(Task task) {
        return null; // TODO
    }
}

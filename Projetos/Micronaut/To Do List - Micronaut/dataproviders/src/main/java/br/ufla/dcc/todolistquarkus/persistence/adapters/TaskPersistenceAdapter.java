package br.ufla.dcc.todolistquarkus.persistence.adapters;

import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.OutputPortException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolistquarkus.persistence.mappers.TaskEntityMapper;
import br.ufla.dcc.todolistquarkus.persistence.entities.TaskEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
@Transactional
public class TaskPersistenceAdapter implements TaskOutputPort {
    private final TaskEntityMapper taskEntityMapper;

    @Override
    public Task create(Task task) throws OutputPortException {
        TaskEntity taskEntity = taskEntityMapper.toTaskEntity(task);

        try {
            taskEntity.persist();

            return taskEntityMapper.toTask(taskEntity);
        } catch (Exception e) {
            throw new OutputPortException(e.getMessage());
        }
    }

    @Override
    public Optional<Task> getById(Long id) throws OutputPortException {
        try {
            return optionalTaskFromTaskEntity(TaskEntity.findById(id));
        } catch (Exception e) {
            throw new OutputPortException(e.getMessage());
        }
    }

    @Override
    public List<Task> getAll() throws OutputPortException {
        try {
            return TaskEntity.findAll()
                    .stream()
                    .map(taskEntity -> {
                        try {
                            return taskEntityMapper.toTask((TaskEntity) taskEntity);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new OutputPortException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) throws OutputPortException {
        try{
            TaskEntity.deleteById(id);
        } catch (Exception e) {
            throw new OutputPortException(e.getMessage());
        }
    }

    @Override
    public Task update(Task task) throws OutputPortException {
        return create(task);
    }

    private Optional<Task> optionalTaskFromTaskEntity(TaskEntity taskEntity) throws DomainException {
        Optional<Task> task = Optional.empty();
        if (taskEntity.isPersistent()) {
            task = Optional.of(taskEntityMapper.toTask(taskEntity));
        }

        return task;
    }
}
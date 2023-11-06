package br.ufla.dcc.todolistmicronaut.persistence.adapters;

import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.OutputPortException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolistmicronaut.persistence.mappers.TaskEntityMapper;
import br.ufla.dcc.todolistmicronaut.persistence.repositories.TaskRepository;
import br.ufla.dcc.todolistmicronaut.persistence.entities.TaskEntity;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
@Transactional
public class TaskPersistenceAdapter implements TaskOutputPort {
    private final TaskRepository taskRepository;
    private final TaskEntityMapper taskEntityMapper;

    @Override
    public Task create(Task task) throws OutputPortException {
        TaskEntity taskEntity = taskEntityMapper.toTaskEntity(task);

        try {
            taskEntity = taskRepository.save(taskEntity);

            return taskEntityMapper.toTask(taskEntity);
        } catch (Exception e) {
            throw new OutputPortException(e.getMessage());
        }
    }

    @Override
    public Optional<Task> getById(Long id) throws OutputPortException {
        try {
            return optionalTaskFromOptionalJpa(taskRepository.findById(id));
        } catch (Exception e) {
            throw new OutputPortException(e.getMessage());
        }
    }

    @Override
    public List<Task> getAll() throws OutputPortException {
        try {
            return taskRepository.findAll()
                    .stream()
                    .map(taskEntity -> {
                        try {
                            return taskEntityMapper.toTask(taskEntity);
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
            taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new OutputPortException(e.getMessage());
        }
    }

    @Override
    public Task update(Task task) throws OutputPortException {
        return create(task);
    }

    private Optional<Task> optionalTaskFromOptionalJpa(Optional<TaskEntity> taskEntity) throws DomainException {
        Optional<Task> task = Optional.empty();
        if (taskEntity.isPresent()) {
            task = Optional.of(taskEntityMapper.toTask(taskEntity.get()));
        }

        return task;
    }
}
package br.ufla.dcc.todolist.core.ports.input.impl;

import br.ufla.dcc.todolist.core.ports.input.DeleteTaskUseCase;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.task.Task;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {
    private final TaskOutputPort taskOutputPort;

    @Override
    public void deleteTaskById(Long id) throws CoreException {
        Optional<Task> existingTask = taskOutputPort.getById(id);

        if(!existingTask.isPresent()) {
            throw new CoreException(String.format("Task id %s was not found", id));
        }

        taskOutputPort.deleteById(id);
    }
}

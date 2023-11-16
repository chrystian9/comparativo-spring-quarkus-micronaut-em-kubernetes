package br.ufla.dcc.todolist.core.task;

import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.RequiredFieldException;
import br.ufla.dcc.todolist.core.task.impl.CommonTask;

import java.time.LocalDateTime;

public class TaskFactory {
    public Task createTask(String title, String description, LocalDateTime deadline) throws DomainException {
        return new CommonTask(title, description, deadline);
    }

    public Task recreateExistingTask(Long id, String title, String description, LocalDateTime deadline, Boolean isCompleted) throws DomainException {
        if (id == null) {
            throw new RequiredFieldException("id");
        }

        Task existingTask = new CommonTask(id, title, description, deadline);

        return keepCompletedValueForExistingTask(existingTask, isCompleted);
    }

    Task keepCompletedValueForExistingTask(Task existingTask, Boolean isCompleted) {
        if(isCompleted!=null) {
            if(Boolean.TRUE.equals(isCompleted)) return existingTask.toComplete();
            else return existingTask.uncomplete();
        }

        return existingTask;
    }
}

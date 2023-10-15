package br.ufla.dcc.todolist.core.task;

import br.ufla.dcc.todolist.core.shared.entities.BaseEntity;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.RequiredFieldException;

import java.time.LocalDateTime;

public interface Task extends BaseEntity {
    Long getId();
    String getTitle();
    String getDescription();
    LocalDateTime getDeadline();
    Boolean isCompleted();
    Task toComplete();
    Task uncomplete();
    Task changeTitle(String newTitle) throws RequiredFieldException;
    Task changeDescription(String newDescription) throws RequiredFieldException;
    Task changeDeadline(LocalDateTime newDeadline) throws RequiredFieldException;
}

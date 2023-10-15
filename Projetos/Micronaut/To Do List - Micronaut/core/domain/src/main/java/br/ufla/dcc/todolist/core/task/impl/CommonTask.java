package br.ufla.dcc.todolist.core.task.impl;

import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.RequiredFieldException;
import br.ufla.dcc.todolist.core.shared.utils.UtilsFactory;
import br.ufla.dcc.todolist.core.shared.utils.ValidationUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.With;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonTask implements Task {
    private Long id;

    @With(AccessLevel.PRIVATE)
    private String title;

    @With(AccessLevel.PRIVATE)
    private String description;

    @With(AccessLevel.PRIVATE)
    private LocalDateTime deadline;

    @With(AccessLevel.PRIVATE)
    private Boolean isCompleted = false;

    private final ValidationUtils validationUtils;

    public CommonTask(Long id, String title, String description, LocalDateTime deadline) throws DomainException {
        this.validationUtils = (new UtilsFactory()).getValidationUtils();

        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;

        validate();
    }

    public CommonTask(String title, String description, LocalDateTime deadline) throws DomainException {
        this.validationUtils = (new UtilsFactory()).getValidationUtils();

        this.title = title;
        this.description = description;
        this.deadline = deadline;

        validate();
    }

    @Override
    public void validate() throws RequiredFieldException {
        if(this.validationUtils.isNullOrEmpty(title)){ throw new RequiredFieldException("title"); }
        if(this.validationUtils.isNullOrEmpty(description)){ throw new RequiredFieldException("description"); }
        if(this.validationUtils.isNull(deadline)){ throw new RequiredFieldException("deadline"); }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public Boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public Task toComplete() {
        return this.isCompleted.equals(Boolean.TRUE) ? this : this.withIsCompleted(true);
    }

    @Override
    public Task uncomplete() {
        return this.isCompleted.equals(Boolean.FALSE) ? this : this.withIsCompleted(false);
    }

    @Override
    public Task changeTitle(String newTitle) throws RequiredFieldException {
        CommonTask task = this.title.equals(newTitle) ? this : this.withTitle(newTitle);
        task.validate();
        return task;
    }

    @Override
    public Task changeDescription(String newDescription) throws RequiredFieldException {
        CommonTask task = this.description.equals(newDescription) ? this : this.withDescription(newDescription);
        task.validate();
        return task;
    }

    @Override
    public Task changeDeadline(LocalDateTime newDeadline) throws RequiredFieldException {
        CommonTask task = this.deadline.equals(newDeadline) ? this : this.withDeadline(newDeadline);
        task.validate();
        return task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline='" + deadline.toString() + '\'' +
                ", is completed='" + (isCompleted ? "true" : "false") +
                '}';
    }
}

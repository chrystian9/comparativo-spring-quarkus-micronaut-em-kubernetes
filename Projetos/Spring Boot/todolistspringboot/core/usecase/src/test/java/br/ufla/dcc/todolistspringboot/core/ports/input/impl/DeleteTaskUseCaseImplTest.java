package br.ufla.dcc.todolistspringboot.core.ports.input.impl;

import br.ufla.dcc.todolistspringboot.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolistspringboot.core.task.Task;
import br.ufla.dcc.todolistspringboot.core.task.TaskFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class DeleteTaskUseCaseImplTest {
    private final TaskFactory taskFactory = new TaskFactory();
    private final TaskOutputPort taskOutputPortMock = mock(TaskOutputPort.class);

    private final DeleteTaskUseCaseImpl deleteTaskUseCase = new DeleteTaskUseCaseImpl(taskOutputPortMock);

    private final Long VALID_ID = 1L;
    private final String VALID_TITLE = "Task Test";
    private final String VALID_DESCRIPTION = "Task Test - Description";
    private final LocalDateTime VALID_DEADLINE = LocalDateTime.now();

    @Test
    void executeCallDeleteTaskByIdWhenTaskIsFound() throws DomainException, CoreException {
        // given
        Task task = taskFactory.createTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE);

        // and
        doReturn(Optional.of(task))
                .when(taskOutputPortMock)
                .getById(eq(VALID_ID));

        // given
        deleteTaskUseCase.deleteTaskById(VALID_ID);
    }

    @Test
    void executeCallDeleteTaskByIdMethodWhenTaskIsNotFound() {
        // given
        doReturn(Optional.empty())
                .when(taskOutputPortMock)
                .getById(eq(VALID_ID));

        // given
        assertThatThrownBy(() -> deleteTaskUseCase.deleteTaskById(VALID_ID))
                .isInstanceOf(CoreException.class)
                .hasMessage("Task id " + VALID_ID + " was not found");
    }
}
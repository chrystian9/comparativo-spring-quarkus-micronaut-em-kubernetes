package br.ufla.dcc.todolist.core.ports.input.impl;

import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class CompletedTaskUseCaseImplTest {
    private final TaskFactory taskFactory = new TaskFactory();
    private final TaskOutputPort taskOutputPortMock = mock(TaskOutputPort.class);

    private final CompletedTaskUseCaseImpl completedTaskUseCase = new CompletedTaskUseCaseImpl(taskOutputPortMock, new TaskMapper());

    private final Long VALID_ID = 1L;
    private final String VALID_TITLE = "Task Test";
    private final String VALID_DESCRIPTION = "Task Test - Description";
    private final LocalDateTime VALID_DEADLINE = LocalDateTime.now();

    @Test
    void executeCallCompleteTaskByIdWhenTaskIsFound() throws DomainException, CoreException {
        // given
        Task task = taskFactory.createTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE);
        Task expectedTask = taskFactory.createTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE);
        expectedTask = expectedTask.toComplete();
        TaskDTO expectedTaskDTO = new TaskDTO(expectedTask.getId(),
                expectedTask.getTitle(),
                expectedTask.getDescription(),
                expectedTask.getDeadline(),
                expectedTask.isCompleted());

        // and
        doReturn(Optional.of(task))
                .when(taskOutputPortMock)
                .getById(eq(task.getId()));

        // and
        doReturn(expectedTask)
                .when(taskOutputPortMock)
                .update(eq(task));

        // when
        TaskDTO taskDTO = completedTaskUseCase.completedTaskById(task.getId());

        // then
        assertThat(taskDTO).isEqualTo(expectedTaskDTO);
        assertThat(taskDTO.isCompleted()).isNotEqualTo(task.isCompleted());
    }

    @Test
    void executeCallCompletedTaskByIdMethodWhenTaskIsNotFound() {
        // given
        doReturn(Optional.empty())
                .when(taskOutputPortMock)
                .getById(eq(VALID_ID));

        // given
        assertThatThrownBy(() -> completedTaskUseCase.completedTaskById(VALID_ID))
                .isInstanceOf(CoreException.class)
                .hasMessage("Task id " + VALID_ID + " was not found");
    }
}

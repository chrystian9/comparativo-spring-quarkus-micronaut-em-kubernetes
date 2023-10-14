package br.ufla.dcc.todolist.core.ports.input.impl;

import br.ufla.dcc.todolist.core.shared.exceptions.causes.OutputPortException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ChangeTaskDeadlineUseCaseImplTest {
    private final TaskFactory taskFactory = new TaskFactory();
    private final TaskOutputPort taskOutputPortMock = mock(TaskOutputPort.class);

    private final ChangeTaskDeadlineUseCaseImpl changeTaskDeadlineUseCase = new ChangeTaskDeadlineUseCaseImpl(taskOutputPortMock, new TaskMapper());

    private final Long VALID_ID = 1L;
    private final String VALID_TITLE = "Task Test";
    private final String VALID_DESCRIPTION = "Task Test - Description";
    private final LocalDateTime VALID_DEADLINE = LocalDateTime.now();

    @Test
    void executeCallChangeTaskDeadlineByIdMethodWhenTaskIsFound() throws DomainException, CoreException {
        // given
        LocalDateTime newDeadline = VALID_DEADLINE.withHour(VALID_DEADLINE.getHour() + 2);
        Task task = taskFactory.createTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE);
        Task expectedTask = taskFactory.createTask(VALID_TITLE, VALID_DESCRIPTION, newDeadline);
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
        TaskDTO taskDTO = changeTaskDeadlineUseCase.changeTaskDealineById(task.getId(), newDeadline);

        // then
        assertThat(taskDTO).isEqualTo(expectedTaskDTO);
        assertThat(taskDTO.deadline()).isNotEqualTo(task.getDeadline());
    }

    @Test
    void executeCallChangeTaskDeadlineByIdMethodWhenTaskIsNotFound() throws OutputPortException {
        // given
        LocalDateTime newDeadline = VALID_DEADLINE.withHour(VALID_DEADLINE.getHour() + 2);

        // and
        doReturn(Optional.empty())
                .when(taskOutputPortMock)
                .getById(eq(VALID_ID));

        // given
        assertThatThrownBy(() -> changeTaskDeadlineUseCase.changeTaskDealineById(VALID_ID, newDeadline))
                .isInstanceOf(CoreException.class)
                .hasMessage("Task id " + VALID_ID + " was not found");
    }
}
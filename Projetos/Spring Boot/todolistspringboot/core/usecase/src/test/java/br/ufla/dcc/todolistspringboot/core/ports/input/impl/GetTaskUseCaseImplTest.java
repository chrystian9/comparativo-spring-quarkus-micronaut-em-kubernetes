package br.ufla.dcc.todolistspringboot.core.ports.input.impl;

import br.ufla.dcc.todolistspringboot.core.dtos.TaskDTO;
import br.ufla.dcc.todolistspringboot.core.mapper.TaskMapper;
import br.ufla.dcc.todolistspringboot.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolistspringboot.core.task.Task;
import br.ufla.dcc.todolistspringboot.core.task.TaskFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GetTaskUseCaseImplTest {
    private final TaskFactory taskFactory = new TaskFactory();
    private final TaskOutputPort taskOutputPortMock = mock(TaskOutputPort.class);

    private final GetTaskUseCaseImpl getTaskUseCase = new GetTaskUseCaseImpl(taskOutputPortMock, new TaskMapper());

    private final Long VALID_ID = 1L;
    private final String VALID_TITLE = "Task Test";
    private final String VALID_DESCRIPTION = "Task Test - Description";
    private final LocalDateTime VALID_DEADLINE = LocalDateTime.now();

    @Test
    void executeCallGetTaskByIdWhenTaskIsFound() throws DomainException, CoreException {
        // given
        Task task = taskFactory.createTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE);
        TaskDTO expectedTaskDTO = new TaskDTO(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDeadline(),
                task.isCompleted());

        // and
        doReturn(Optional.of(task))
                .when(taskOutputPortMock)
                .getById(eq(VALID_ID));

        // when
        TaskDTO taskDTO = getTaskUseCase.getById(VALID_ID);

        // then
        assertThat(taskDTO).isNotNull();
        assertThat(taskDTO).isEqualTo(expectedTaskDTO);
    }

    @Test
    void executeCallGetTaskByIdMethodWhenTaskIsNotFound() {
        // given
        doReturn(Optional.empty())
                .when(taskOutputPortMock)
                .getById(eq(VALID_ID));

        // given
        assertThatThrownBy(() -> getTaskUseCase.getById(VALID_ID))
                .isInstanceOf(CoreException.class)
                .hasMessage("Task id " + VALID_ID + " was not found");
    }
}

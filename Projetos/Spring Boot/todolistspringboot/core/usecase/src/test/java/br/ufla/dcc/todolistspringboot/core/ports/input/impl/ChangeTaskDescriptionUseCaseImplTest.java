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

public class ChangeTaskDescriptionUseCaseImplTest {
    private final TaskFactory taskFactory = new TaskFactory();
    private final TaskOutputPort taskOutputPortMock = mock(TaskOutputPort.class);

    private final ChangeTaskDescriptionUseCaseImpl changeTaskDescriptionUseCase = new ChangeTaskDescriptionUseCaseImpl(taskOutputPortMock, new TaskMapper());

    private final Long VALID_ID = 1L;
    private final String VALID_TITLE = "Task Test";
    private final String VALID_DESCRIPTION = "Task Test - Description";
    private final LocalDateTime VALID_DEADLINE = LocalDateTime.now();

    @Test
    void executeCallChangeTaskDeadlineByIdMethodWhenTaskIsFound() throws DomainException, CoreException {
        // given
        String newDescription = "Task Test - Description 2";
        Task task = taskFactory.createTask(VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE);
        Task expectedTask = taskFactory.createTask(VALID_TITLE, newDescription, VALID_DEADLINE);
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
        TaskDTO taskDTO = changeTaskDescriptionUseCase.changeTaskDescriptionById(task.getId(), newDescription);

        // then
        assertThat(taskDTO).isEqualTo(expectedTaskDTO);
        assertThat(taskDTO.description()).isNotEqualTo(task.getDescription());
    }

    @Test
    void executeCallChangeTaskDeadlineByIdMethodWhenTaskIsNotFound() {
        // given
        String newDescription = "Task Test - Description 2";

        // and
        doReturn(Optional.empty())
                .when(taskOutputPortMock)
                .getById(eq(VALID_ID));

        // given
        assertThatThrownBy(() -> changeTaskDescriptionUseCase.changeTaskDescriptionById(VALID_ID, newDescription))
                .isInstanceOf(CoreException.class)
                .hasMessage("Task id " + VALID_ID + " was not found");
    }
}

package br.ufla.dcc.todolist.core.ports.input.impl;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.DomainValidationException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.OutputPortException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CreateTaskUseCaseImplTest {
    private final TaskFactory taskFactory = mock(TaskFactory.class);
    private final TaskOutputPort taskOutputPortMock = mock(TaskOutputPort.class);
    private final TaskMapper taskMapperMock = mock(TaskMapper.class);

    private final CreateTaskUseCaseImpl createTaskUseCase = new CreateTaskUseCaseImpl(taskOutputPortMock, new TaskMapper(), taskFactory);

    private final String VALID_TITLE = "Task Test";
    private final String VALID_DESCRIPTION = "Task Test - Description";
    private final LocalDateTime VALID_DEADLINE = LocalDateTime.now();

    private final Task taskMock = mock(Task.class, Mockito.RETURNS_DEEP_STUBS);
    private TaskDTO validTaskDTO = new TaskDTO(null, VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE, true);

    void prepareStubs() throws DomainException, OutputPortException {
        doReturn(taskMock).when(taskFactory).createTask(anyString(), anyString(), any());
        doReturn(taskMock).when(taskOutputPortMock).create(taskMock);
        when(taskMapperMock.toDTO(any())).thenReturn(validTaskDTO);
    }

    @Test
    void shoudlCallFactoriesAndOutputPort() throws DomainException, CoreException {
        prepareStubs();
        createTaskUseCase.createTask(validTaskDTO);
        verify(taskFactory, times(1)).createTask(validTaskDTO.title(), validTaskDTO.description(), validTaskDTO.deadline());
        verify(taskOutputPortMock, times(1)).create(any());
    }

    @Test
    void shouldReturnCustomer() throws DomainException, CoreException {
        prepareStubs();

        TaskDTO newTaskDTO = new TaskDTO(null, VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE, null);

        newTaskDTO = createTaskUseCase.createTask(newTaskDTO);

        assertNotNull(newTaskDTO);
    }

    @Nested
    class CreatingCustomerShouldThrow {
        @Test
        void whenEntitiesThrows() throws DomainException {
            when(taskFactory.createTask(anyString(), anyString(), any())).thenThrow(new DomainException(""));

            TaskDTO taskDTO = new TaskDTO(null, VALID_TITLE, VALID_DESCRIPTION, VALID_DEADLINE, null);

            assertThrows(DomainValidationException.class, () -> createTaskUseCase.createTask(taskDTO));
        }
    }
}
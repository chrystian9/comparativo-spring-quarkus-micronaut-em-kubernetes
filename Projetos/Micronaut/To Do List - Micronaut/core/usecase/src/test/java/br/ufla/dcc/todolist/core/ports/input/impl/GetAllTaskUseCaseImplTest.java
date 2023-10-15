package br.ufla.dcc.todolist.core.ports.input.impl;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.task.Task;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GetAllTaskUseCaseImplTest {
    private final TaskFactory taskFactory = new TaskFactory();
    private final TaskOutputPort taskOutputPortMock = mock(TaskOutputPort.class);

    private final GetAllTaskUseCaseImpl getAllTaskUseCase = new GetAllTaskUseCaseImpl(taskOutputPortMock, new TaskMapper());
    private final LocalDateTime VALID_DEADLINE = LocalDateTime.now();

    @Test
    void executeCallCompleteTaskByIdWhenTaskIsFound() throws DomainException, CoreException {
        // given
        Task task1 = taskFactory.recreateExistingTask(1L, "Task Test 1", "Task Test 1 - Description", VALID_DEADLINE.withHour(12), true);
        Task task2 = taskFactory.recreateExistingTask(2L, "Task Test 2", "Task Test 2 - Description", VALID_DEADLINE.withHour(14), false);
        Task task3 = taskFactory.recreateExistingTask(3L, "Task Test 3", "Task Test 3 - Description", VALID_DEADLINE.withHour(16), true);

        List<Task> taskList = new ArrayList<>();

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // and
        TaskDTO expectedTaskDTO1 = new TaskDTO(task1.getId(),
                task1.getTitle(),
                task1.getDescription(),
                task1.getDeadline(),
                task1.isCompleted());

        TaskDTO expectedTaskDTO2 = new TaskDTO(task2.getId(),
                task2.getTitle(),
                task2.getDescription(),
                task2.getDeadline(),
                task2.isCompleted());

        TaskDTO expectedTaskDTO3 = new TaskDTO(task3.getId(),
                task3.getTitle(),
                task3.getDescription(),
                task3.getDeadline(),
                task3.isCompleted());

        List<TaskDTO> expectedTaskDTOList = new ArrayList<>();

        expectedTaskDTOList.add(expectedTaskDTO1);
        expectedTaskDTOList.add(expectedTaskDTO2);
        expectedTaskDTOList.add(expectedTaskDTO3);

        // and
        doReturn(taskList)
                .when(taskOutputPortMock)
                .getAll();

        // when
        List<TaskDTO> taskDTOList = getAllTaskUseCase.getAll();

        // then
        assertThat(taskDTOList).isNotNull();
        assertThat(taskDTOList.size()).isEqualTo(expectedTaskDTOList.size());
        assertThat(taskDTOList.get(0)).isEqualTo(expectedTaskDTO1);
        assertThat(taskDTOList.get(1)).isEqualTo(expectedTaskDTO2);
        assertThat(taskDTOList.get(2)).isEqualTo(expectedTaskDTO3);
    }
}

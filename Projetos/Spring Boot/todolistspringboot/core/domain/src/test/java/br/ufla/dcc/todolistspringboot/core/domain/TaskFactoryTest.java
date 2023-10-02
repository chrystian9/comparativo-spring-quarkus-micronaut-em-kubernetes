package br.ufla.dcc.todolistspringboot.core.domain;

import br.ufla.dcc.todolistspringboot.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolistspringboot.core.task.Task;
import br.ufla.dcc.todolistspringboot.core.task.TaskFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskFactoryTest {
    private final TaskFactory taskFactory = new TaskFactory();

    private static final Long VALID_ID = 1L;
    private static final String VALID_TITLE = "Task Test";
    private static final String VALID_DESCRIPTION = "Task test - description";
    private static final LocalDateTime DATE_TIME_VALID = LocalDateTime.now();

    @Test
    void shouldCreateTask() throws DomainException {
        Task validTask = taskFactory.createTask(VALID_TITLE, VALID_DESCRIPTION, DATE_TIME_VALID);

        assertNotNull(validTask);
        assertNotNull(validTask.getTitle());
        assertNotNull(validTask.getDescription());
        assertNotNull(validTask.getDeadline());

        assertNull(validTask.getId());

        assertEquals(VALID_TITLE, validTask.getTitle());
        assertEquals(VALID_DESCRIPTION, validTask.getDescription());
        assertEquals(DATE_TIME_VALID, validTask.getDeadline());
    }

    @Test
    void shouldReconstituteAnExistingTask() throws DomainException {
        Task validTask = taskFactory.recreateExistingTask(VALID_ID, VALID_TITLE, VALID_DESCRIPTION, DATE_TIME_VALID, true);

        assertNotNull(validTask);
        assertNotNull(validTask.getId());
        assertEquals(VALID_ID, validTask.getId());
        assertEquals(VALID_TITLE, validTask.getTitle());
        assertEquals(VALID_DESCRIPTION, validTask.getDescription());
        assertEquals(DATE_TIME_VALID, validTask.getDeadline());
        assertEquals(true, validTask.isCompleted());
    }

    @Test
    void shouldThrowWhenCreatingTaskWithInvalidProperties() {
        assertThrows(DomainException.class, () -> {
            taskFactory.createTask(null, VALID_DESCRIPTION, DATE_TIME_VALID);
        });
        assertThrows(DomainException.class, () -> {
            taskFactory.createTask(VALID_TITLE, null, DATE_TIME_VALID);
        });
        assertThrows(DomainException.class, () -> {
            taskFactory.createTask(VALID_TITLE, VALID_DESCRIPTION, null);
        });
    }
}

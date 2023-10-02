package br.ufla.dcc.todolistspringboot.core.domain;

import br.ufla.dcc.todolistspringboot.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolistspringboot.core.shared.exceptions.causes.RequiredFieldException;
import br.ufla.dcc.todolistspringboot.core.task.Task;
import br.ufla.dcc.todolistspringboot.core.task.TaskFactory;
import br.ufla.dcc.todolistspringboot.core.task.impl.CommonTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private final TaskFactory taskFactory = new TaskFactory();

    private static final Long ID = 1L;
    private static final String TITLE = "Task Test";
    private static final String DESCRIPTION = "Task test - description";
    private static final LocalDateTime DATE_TIME_VALID = LocalDateTime.now();
    private Task validTask;

    @BeforeEach
    void beforeEach() throws DomainException {
        this.validTask = taskFactory.createTask(TITLE, DESCRIPTION, DATE_TIME_VALID);
    }

    @Test
    void shouldCreateValidTask() throws DomainException {
        Task task = new CommonTask(TITLE, DESCRIPTION, DATE_TIME_VALID);

        assertNotNull(task);
        assertNotNull(task.getTitle());
        assertNotNull(task.getDescription());
        assertNotNull(task.getDeadline());

        assertNull(task.getId());

        assertEquals(TITLE, task.getTitle());
        assertEquals(DESCRIPTION, task.getDescription());
        assertEquals(DATE_TIME_VALID, task.getDeadline());
    }

    @Test
    void shouldCreateValidTaskWithId() throws DomainException {
        Task task = new CommonTask(ID, TITLE, DESCRIPTION, DATE_TIME_VALID);

        assertNotNull(task);
        assertNotNull(task.getId());
        assertNotNull(task.getTitle());
        assertNotNull(task.getDescription());
        assertNotNull(task.getDeadline());

        assertEquals(ID, task.getId());
        assertEquals(TITLE, task.getTitle());
        assertEquals(DESCRIPTION, task.getDescription());
        assertEquals(DATE_TIME_VALID, task.getDeadline());
    }

    @Nested
    class ClientValidationShouldThrow {
        @Test
        void ifTitleIsNull() {
            assertThrows(RequiredFieldException.class, () -> {
                new CommonTask(ID, null, DESCRIPTION, DATE_TIME_VALID);
            });
        }

        @Test
        void ifDescriptionIsNull() {
            assertThrows(RequiredFieldException.class, () -> {
                new CommonTask(ID, TITLE, null, DATE_TIME_VALID);
            });
        }

        @Test
        void ifLocalDateTimeIsNull() {
            assertThrows(RequiredFieldException.class, () -> {
                new CommonTask(ID, TITLE, DESCRIPTION, null);
            });
        }
    }

    @Nested
    class CustomerPropertiesShouldChange {
        @Test
        void withValidTitle() throws DomainException {
            String newTitle = "Task Test 2";
            Task task = validTask.changeTitle(newTitle);
            assertEquals(task.getTitle(), newTitle);

            Task task2 = task.changeTitle(newTitle);
            assertEquals(task, task2);
        }

        @Test
        void withValidDescription() throws DomainException {
            String newDescription = "Task test 2 - description";
            Task task = validTask.changeDescription(newDescription);
            assertEquals(task.getDescription(), newDescription);

            Task task2 = task.changeDescription(newDescription);
            assertEquals(task, task2);
        }

        @Test
        void withValidDeadline() throws DomainException {
            LocalDateTime newDeadline = LocalDateTime.now();
            Task task = validTask.changeDeadline(newDeadline);
            assertEquals(task.getDeadline(), newDeadline);

            Task task2 = task.changeDeadline(newDeadline);
            assertEquals(task, task2);
        }

        @Test
        void whenCompleted() {
            Task uncompleted = validTask.uncomplete();
            assertFalse(uncompleted.isCompleted());

            Task completed = uncompleted.toComplete();
            assertTrue(completed.isCompleted());

            Task sameCompleted = completed.toComplete();
            assertEquals(completed, sameCompleted);
        }

        @Test
        void whenUncompleted() {
            Task completed = validTask.toComplete();
            assertTrue(completed.isCompleted());

            Task uncompleted = completed.uncomplete();
            assertFalse(uncompleted.isCompleted());

            Task sameUncompleted = uncompleted.uncomplete();
            assertEquals(uncompleted, sameUncompleted);
        }
    }
}

package br.ufla.dcc.todolistquarkus.config;

import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.ports.input.*;
import br.ufla.dcc.todolist.core.ports.input.impl.*;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import io.quarkus.arc.DefaultBean;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

@Dependent
public class AppConfig {
    private final TaskMapper taskMapper = new TaskMapper();
    private final TaskFactory taskFactory = new TaskFactory();

    @Produces
    @DefaultBean
    public CreateTaskUseCase createTaskUseCase(TaskOutputPort taskOutputPort) {
        return new CreateTaskUseCaseImpl(taskOutputPort, taskMapper, taskFactory);
    }

    @Produces
    @DefaultBean
    public DeleteTaskUseCase deleteTaskUseCase(TaskOutputPort taskOutputPort) {
        return new DeleteTaskUseCaseImpl(taskOutputPort);
    }

    @Produces
    @DefaultBean
    public GetAllTaskUseCase getAllTaskUseCase(TaskOutputPort taskOutputPort) {
        return new GetAllTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Produces
    @DefaultBean
    public GetTaskUseCase getTaskUseCase(TaskOutputPort taskOutputPort) {
        return new GetTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Produces
    @DefaultBean
    public CompletedTaskUseCase completedTaskUseCase(TaskOutputPort taskOutputPort) {
        return new CompletedTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Produces
    @DefaultBean
    public UncompletedTaskUseCase uncompletedTaskUseCase(TaskOutputPort taskOutputPort) {
        return new UncompletedTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Produces
    @DefaultBean
    public ChangeTaskTitleUseCase changeTaskTitleUseCase(TaskOutputPort taskOutputPort) {
        return new ChangeTaskTitleUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Produces
    @DefaultBean
    public ChangeTaskDescriptionUseCase changeTaskDescriptionUseCase(TaskOutputPort taskOutputPort) {
        return new ChangeTaskDescriptionUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Produces
    @DefaultBean
    public ChangeTaskDeadlineUseCase changeTaskDeadlineUseCase(TaskOutputPort taskOutputPort) {
        return new ChangeTaskDeadlineUseCaseImpl(taskOutputPort, taskMapper);
    }
}
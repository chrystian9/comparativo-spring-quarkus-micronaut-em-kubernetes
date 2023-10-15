package br.ufla.dcc.todolistquarkus.config;

import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.ports.input.*;
import br.ufla.dcc.todolist.core.ports.input.impl.*;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class AppConfig {
    private final TaskMapper taskMapper = new TaskMapper();
    private final TaskFactory taskFactory = new TaskFactory();

    @Singleton
    public CreateTaskUseCase createTaskUseCase(TaskOutputPort taskOutputPort) {
        return new CreateTaskUseCaseImpl(taskOutputPort, taskMapper, taskFactory);
    }

    @Singleton
    public DeleteTaskUseCase deleteTaskUseCase(TaskOutputPort taskOutputPort) {
        return new DeleteTaskUseCaseImpl(taskOutputPort);
    }

    @Singleton
    public GetAllTaskUseCase getAllTaskUseCase(TaskOutputPort taskOutputPort) {
        return new GetAllTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Singleton
    public GetTaskUseCase getTaskUseCase(TaskOutputPort taskOutputPort) {
        return new GetTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Singleton
    public CompletedTaskUseCase completedTaskUseCase(TaskOutputPort taskOutputPort) {
        return new CompletedTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Singleton
    public UncompletedTaskUseCase uncompletedTaskUseCase(TaskOutputPort taskOutputPort) {
        return new UncompletedTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Singleton
    public ChangeTaskTitleUseCase changeTaskTitleUseCase(TaskOutputPort taskOutputPort) {
        return new ChangeTaskTitleUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Singleton
    public ChangeTaskDescriptionUseCase changeTaskDescriptionUseCase(TaskOutputPort taskOutputPort) {
        return new ChangeTaskDescriptionUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Singleton
    public ChangeTaskDeadlineUseCase changeTaskDeadlineUseCase(TaskOutputPort taskOutputPort) {
        return new ChangeTaskDeadlineUseCaseImpl(taskOutputPort, taskMapper);
    }
}
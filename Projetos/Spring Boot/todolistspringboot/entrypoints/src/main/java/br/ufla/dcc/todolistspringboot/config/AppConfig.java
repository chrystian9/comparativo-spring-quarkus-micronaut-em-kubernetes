package br.ufla.dcc.todolistspringboot.config;

import br.ufla.dcc.todolist.core.mapper.TaskMapper;
import br.ufla.dcc.todolist.core.ports.input.*;
import br.ufla.dcc.todolist.core.ports.input.impl.*;
import br.ufla.dcc.todolist.core.ports.output.TaskOutputPort;
import br.ufla.dcc.todolist.core.task.TaskFactory;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    private final TaskMapper taskMapper = new TaskMapper();
    private final TaskFactory taskFactory = new TaskFactory();

    @Bean
    public CreateTaskUseCase createTaskUseCase(TaskOutputPort taskOutputPort) {
        return new CreateTaskUseCaseImpl(taskOutputPort, taskMapper, taskFactory);
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(TaskOutputPort taskOutputPort) {
        return new DeleteTaskUseCaseImpl(taskOutputPort);
    }

    @Bean
    public GetAllTaskUseCase getAllTaskUseCase(TaskOutputPort taskOutputPort) {
        return new GetAllTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Bean
    public GetTaskUseCase getTaskUseCase(TaskOutputPort taskOutputPort) {
        return new GetTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Bean
    public CompletedTaskUseCase completedTaskUseCase(TaskOutputPort taskOutputPort) {
        return new CompletedTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Bean
    public UncompletedTaskUseCase uncompletedTaskUseCase(TaskOutputPort taskOutputPort) {
        return new UncompletedTaskUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Bean
    public ChangeTaskTitleUseCase changeTaskTitleUseCase(TaskOutputPort taskOutputPort) {
        return new ChangeTaskTitleUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Bean
    public ChangeTaskDescriptionUseCase changeTaskDescriptionUseCase(TaskOutputPort taskOutputPort) {
        return new ChangeTaskDescriptionUseCaseImpl(taskOutputPort, taskMapper);
    }

    @Bean
    public ChangeTaskDeadlineUseCase changeTaskDeadlineUseCase(TaskOutputPort taskOutputPort) {
        return new ChangeTaskDeadlineUseCaseImpl(taskOutputPort, taskMapper);
    }
}
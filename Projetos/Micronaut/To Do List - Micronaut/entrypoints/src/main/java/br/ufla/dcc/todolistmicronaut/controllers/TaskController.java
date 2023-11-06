package br.ufla.dcc.todolistmicronaut.controllers;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.ports.input.*;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolistmicronaut.data.requests.ChangeTaskDeadlineByIdRequest;
import br.ufla.dcc.todolistmicronaut.data.requests.ChangeTaskDescriptionByIdRequest;
import br.ufla.dcc.todolistmicronaut.data.requests.ChangeTaskTitleByIdRequest;
import br.ufla.dcc.todolistmicronaut.data.requests.CreateTaskRequest;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("/task")
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;
    private final GetAllTaskUseCase getAllTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final ChangeTaskTitleUseCase changeTaskTitleUseCase;
    private final ChangeTaskDescriptionUseCase changeTaskDescriptionUseCase;
    private final ChangeTaskDeadlineUseCase changeTaskDeadlineUseCase;
    private final CompletedTaskUseCase completedTaskUseCase;
    private final UncompletedTaskUseCase uncompletedTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase, GetAllTaskUseCase getAllTaskUseCase, GetTaskUseCase getTaskUseCase, DeleteTaskUseCase deleteTaskUseCase, ChangeTaskTitleUseCase changeTaskTitleUseCase, ChangeTaskDescriptionUseCase changeTaskDescriptionUseCase, ChangeTaskDeadlineUseCase changeTaskDeadlineUseCase, CompletedTaskUseCase completedTaskUseCase, UncompletedTaskUseCase uncompletedTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getAllTaskUseCase = getAllTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.changeTaskTitleUseCase = changeTaskTitleUseCase;
        this.changeTaskDescriptionUseCase = changeTaskDescriptionUseCase;
        this.changeTaskDeadlineUseCase = changeTaskDeadlineUseCase;
        this.completedTaskUseCase = completedTaskUseCase;
        this.uncompletedTaskUseCase = uncompletedTaskUseCase;
    }

    @Post
    public void createTask(CreateTaskRequest createTaskRequest){
        try {
            createTaskUseCase.createTask(new TaskDTO(createTaskRequest.getId(),
                    createTaskRequest.getTitle(),
                    createTaskRequest.getDescription(),
                    createTaskRequest.getDeadline(),
                    createTaskRequest.getIsCompleted()));
//        } catch (DomainValidationException e) {
//            throw new BadRequestException(e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @Get("/getAllTask")
    public List<TaskDTO> getAllTask(){
        try {
            return getAllTaskUseCase.getAll();
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @Get("/getTaskById/{id}")
    public TaskDTO getTaskById(Long id){
        try {
            return getTaskUseCase.getById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @Delete("/{id}")
    public void deleteTaskById(Long id){
        try {
            deleteTaskUseCase.deleteTaskById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @Put("/changeTaskTitleById")
    public TaskDTO changeTaskTitleById(ChangeTaskTitleByIdRequest changeTaskTitleByIdRequest){
        try {
            return changeTaskTitleUseCase.changeTaskTitleById(
                    changeTaskTitleByIdRequest.getId(),
                    changeTaskTitleByIdRequest.getTitle());
//        } catch (DomainValidationException e) {
//            throw new BadRequestException(e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @Put("/changeTaskDescriptionById")
    public TaskDTO changeTaskDescriptionById(ChangeTaskDescriptionByIdRequest changeTaskDescriptionByIdRequest){
        try {
            return changeTaskDescriptionUseCase.changeTaskDescriptionById(
                    changeTaskDescriptionByIdRequest.getId(),
                    changeTaskDescriptionByIdRequest.getDescription());
//        } catch (DomainValidationException e) {
//            throw new BadRequestException(e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @Put("/changeTaskDeadlineById")
    public TaskDTO changeTaskDeadlineById(ChangeTaskDeadlineByIdRequest changeTaskDeadlineByIdRequest){
        try {
            return changeTaskDeadlineUseCase.changeTaskDealineById(
                    changeTaskDeadlineByIdRequest.getId(),
                    changeTaskDeadlineByIdRequest.getDateTime());
//        } catch (DomainValidationException e) {
//            throw new BadRequestException(e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @Put("/completedTaskById/{id}")
    public TaskDTO completedTaskById(Long id){
        try {
            return completedTaskUseCase.completedTaskById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @Put("/uncompletedTaskById/{id}")
    public TaskDTO uncompletedTaskById(Long id){
        try {
            return uncompletedTaskUseCase.uncompletedTaskById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }
}

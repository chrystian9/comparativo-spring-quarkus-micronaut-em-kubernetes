package br.ufla.dcc.todolistquarkus.controllers;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.ports.input.*;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.DomainValidationException;
import br.ufla.dcc.todolistquarkus.data.requests.ChangeTaskDeadlineByIdRequest;
import br.ufla.dcc.todolistquarkus.data.requests.ChangeTaskDescriptionByIdRequest;
import br.ufla.dcc.todolistquarkus.data.requests.ChangeTaskTitleByIdRequest;
import br.ufla.dcc.todolistquarkus.data.requests.CreateTaskRequest;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/task")
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

    @POST
    public void createTask(CreateTaskRequest createTaskRequest){
        try {
            createTaskUseCase.createTask(new TaskDTO(createTaskRequest.getId(),
                    createTaskRequest.getTitle(),
                    createTaskRequest.getDescription(),
                    createTaskRequest.getDeadline(),
                    createTaskRequest.getIsCompleted()));
        } catch (DomainValidationException e) {
            throw new BadRequestException(e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("/getAllTask")
    public List<TaskDTO> getAllTask(){
        try {
            return getAllTaskUseCase.getAll();
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("/getTaskById/{id}")
    public TaskDTO getTaskById(@PathParam("id") Long id){
        try {
            return getTaskUseCase.getById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("/{id}")
    public void deleteTaskById(@PathParam("id") Long id){
        try {
            deleteTaskUseCase.deleteTaskById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("/changeTaskTitleById")
    public TaskDTO changeTaskTitleById(ChangeTaskTitleByIdRequest changeTaskTitleByIdRequest){
        try {
            return changeTaskTitleUseCase.changeTaskTitleById(
                    changeTaskTitleByIdRequest.getId(),
                    changeTaskTitleByIdRequest.getTitle());
        } catch (DomainValidationException e) {
            throw new BadRequestException(e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("/changeTaskDescriptionById")
    public TaskDTO changeTaskDescriptionById(ChangeTaskDescriptionByIdRequest changeTaskDescriptionByIdRequest){
        try {
            return changeTaskDescriptionUseCase.changeTaskDescriptionById(
                    changeTaskDescriptionByIdRequest.getId(),
                    changeTaskDescriptionByIdRequest.getDescription());
        } catch (DomainValidationException e) {
            throw new BadRequestException(e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("/changeTaskDeadlineById")
    public TaskDTO changeTaskDeadlineById(ChangeTaskDeadlineByIdRequest changeTaskDeadlineByIdRequest){
        try {
            return changeTaskDeadlineUseCase.changeTaskDealineById(
                    changeTaskDeadlineByIdRequest.getId(),
                    changeTaskDeadlineByIdRequest.getDateTime());
        } catch (DomainValidationException e) {
            throw new BadRequestException(e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("/completedTaskById/{id}")
    public TaskDTO completedTaskById(@PathParam("id") Long id){
        try {
            return completedTaskUseCase.completedTaskById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("/uncompletedTaskById/{id}")
    public TaskDTO uncompletedTaskById(@PathParam("id") Long id){
        try {
            return uncompletedTaskUseCase.uncompletedTaskById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }
}

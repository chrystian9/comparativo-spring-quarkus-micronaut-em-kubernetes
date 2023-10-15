package br.ufla.dcc.todolistspringboot.controllers;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.ports.input.*;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.DomainValidationException;
import br.ufla.dcc.todolistspringboot.data.requests.ChangeTaskDeadlineByIdRequest;
import br.ufla.dcc.todolistspringboot.data.requests.ChangeTaskDescriptionByIdRequest;
import br.ufla.dcc.todolistspringboot.data.requests.ChangeTaskTitleByIdRequest;
import br.ufla.dcc.todolistspringboot.data.requests.CreateTaskRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/task")
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

    @PostMapping("/")
    public void createTask(@RequestBody CreateTaskRequest createTaskRequest){
        try {
            createTaskUseCase.createTask(new TaskDTO(createTaskRequest.getId(),
                    createTaskRequest.getTitle(),
                    createTaskRequest.getDescription(),
                    createTaskRequest.getDeadline(),
                    createTaskRequest.getIsCompleted()));
        } catch (DomainValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/getAllTask")
    public List<TaskDTO> getAllTask(){
        try {
            return getAllTaskUseCase.getAll();
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/getTaskById")
    public TaskDTO getTaskById(Long id){
        try {
            return getTaskUseCase.getById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/")
    public void deleteTaskById(Long id){
        try {
            deleteTaskUseCase.deleteTaskById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping(path="/changeTaskTitleById")
    public TaskDTO changeTaskTitleById(@RequestBody ChangeTaskTitleByIdRequest changeTaskTitleByIdRequest){
        try {
            return changeTaskTitleUseCase.changeTaskTitleById(
                    changeTaskTitleByIdRequest.getId(),
                    changeTaskTitleByIdRequest.getTitle());
        } catch (DomainValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping(path="/changeTaskDescriptionById")
    public TaskDTO changeTaskDescriptionById(@RequestBody ChangeTaskDescriptionByIdRequest changeTaskDescriptionByIdRequest){
        try {
            return changeTaskDescriptionUseCase.changeTaskDescriptionById(
                    changeTaskDescriptionByIdRequest.getId(),
                    changeTaskDescriptionByIdRequest.getDescription());
        } catch (DomainValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping(path="/changeTaskDeadlineById")
    public TaskDTO changeTaskDeadlineById(@RequestBody ChangeTaskDeadlineByIdRequest changeTaskDeadlineByIdRequest){
        try {
            return changeTaskDeadlineUseCase.changeTaskDealineById(
                    changeTaskDeadlineByIdRequest.getId(),
                    changeTaskDeadlineByIdRequest.getDateTime());
        } catch (DomainValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping(path="/completedTaskById")
    public TaskDTO completedTaskById(Long id){
        try {
            return completedTaskUseCase.completedTaskById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping(path="/uncompletedTaskById")
    public TaskDTO uncompletedTaskById(Long id){
        try {
            return uncompletedTaskUseCase.uncompletedTaskById(id);
        } catch (CoreException e){
            throw new RuntimeException(e);
        }
    }
}

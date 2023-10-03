package br.ufla.dcc.todolistspringboot.controllers;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import br.ufla.dcc.todolist.core.ports.input.CreateTaskUseCase;
import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;
import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;
import br.ufla.dcc.todolist.core.shared.exceptions.causes.DomainValidationException;
import br.ufla.dcc.todolistspringboot.data.requests.CreateTaskRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
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
}

package br.ufla.dcc.todolistmicronaut;

import br.ufla.dcc.todolist.core.dtos.TaskDTO;
import io.micronaut.runtime.Micronaut;
import io.micronaut.serde.annotation.SerdeImport;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title="To Do List - Micronaut",
                version = "1.0.0"
//                license = @License(
//                        name = "MIT License",
//                        url = "https://www.mit.edu/~amini/LICENSE.md")
        )
)
@SerdeImport(TaskDTO.class)
public class ToDoListMicronautApplication {
    public static void main(String[] args) {
        Micronaut.run(ToDoListMicronautApplication.class, args);
    }
}

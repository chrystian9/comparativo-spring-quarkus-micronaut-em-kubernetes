package br.ufla.dcc.todolistmicronaut;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.Entity;

@OpenAPIDefinition(
        info = @Info(
                title="To Do List - Micronaut",
                version = "1.0.0"
//                license = @License(
//                        name = "MIT License",
//                        url = "https://www.mit.edu/~amini/LICENSE.md")
        )
)
@Introspected(packages="br.ufla.dcc.todolistmicronaut.persistence.entities", includedAnnotations= Entity.class)
public class ToDoListMicronautApplication {
    public static void main(String[] args) {
        Micronaut.run(ToDoListMicronautApplication.class, args);
    }
}

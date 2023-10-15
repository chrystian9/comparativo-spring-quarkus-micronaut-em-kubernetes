package br.ufla.dcc.todolistquarkus;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title="To Do List - Quarkus",
                version = "1.0.0",
                license = @License(
                        name = "MIT License",
                        url = "https://www.mit.edu/~amini/LICENSE.md"))
)
public class ToDoListMicronautApplication {
    public static void main(String[] args) {
        Micronaut.run(ToDoListMicronautApplication.class, args);
    }
}

package br.ufla.dcc.todolistquarkus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title="To Do List - Quarkus",
                version = "1.0.0",
                license = @License(
                        name = "MIT License",
                        url = "https://www.mit.edu/~amini/LICENSE.md"))
)
public class ToDoListQuarkusApplication extends Application {
}

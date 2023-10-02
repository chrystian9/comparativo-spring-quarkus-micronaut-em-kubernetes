package br.ufla.dcc.todolistspringboot;

import br.ufla.dcc.todolistspringboot.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class ToDoListSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToDoListSpringBootApplication.class, args);
    }
}
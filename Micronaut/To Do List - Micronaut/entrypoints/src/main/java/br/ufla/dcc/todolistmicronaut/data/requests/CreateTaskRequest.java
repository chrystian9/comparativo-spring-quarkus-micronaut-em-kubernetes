package br.ufla.dcc.todolistmicronaut.data.requests;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
public class CreateTaskRequest {
    Long id;
    String title;
    String description;
    LocalDateTime deadline;
    Boolean isCompleted;
}
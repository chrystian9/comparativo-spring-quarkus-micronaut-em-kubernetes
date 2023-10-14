package br.ufla.dcc.todolistquarkus.data.requests;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
    Long id;
    String title;
    String description;
    LocalDateTime deadline;
    Boolean isCompleted;
}
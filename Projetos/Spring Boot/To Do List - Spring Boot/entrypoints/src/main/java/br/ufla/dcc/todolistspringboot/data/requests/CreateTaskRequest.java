package br.ufla.dcc.todolistspringboot.data.requests;

import lombok.*;

import java.time.LocalDateTime;

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
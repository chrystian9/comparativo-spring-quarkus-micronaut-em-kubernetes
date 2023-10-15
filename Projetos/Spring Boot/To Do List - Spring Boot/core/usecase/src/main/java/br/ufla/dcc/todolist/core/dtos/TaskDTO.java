package br.ufla.dcc.todolist.core.dtos;

import java.time.LocalDateTime;

public record TaskDTO (
    Long id,
    String title,
    String description,
    LocalDateTime deadline,
    Boolean isCompleted
){}
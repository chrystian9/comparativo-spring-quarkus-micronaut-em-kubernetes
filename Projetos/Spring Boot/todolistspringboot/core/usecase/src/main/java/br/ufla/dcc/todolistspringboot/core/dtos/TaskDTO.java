package br.ufla.dcc.todolistspringboot.core.dtos;

import java.time.LocalDateTime;

public record TaskDTO (
    Long id,
    String title,
    String description,
    LocalDateTime deadline,
    Boolean isCompleted
){}
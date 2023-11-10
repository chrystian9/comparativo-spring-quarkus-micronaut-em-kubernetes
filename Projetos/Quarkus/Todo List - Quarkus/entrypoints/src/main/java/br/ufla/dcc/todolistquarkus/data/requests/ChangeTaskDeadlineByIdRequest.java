package br.ufla.dcc.todolistquarkus.data.requests;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeTaskDeadlineByIdRequest {
    public Long id;
    public LocalDateTime deadline;
}

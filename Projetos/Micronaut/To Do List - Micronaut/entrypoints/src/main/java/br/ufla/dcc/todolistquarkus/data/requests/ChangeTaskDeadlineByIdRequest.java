package br.ufla.dcc.todolistquarkus.data.requests;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeTaskDeadlineByIdRequest {
    public Long Id;
    public LocalDateTime dateTime;
}

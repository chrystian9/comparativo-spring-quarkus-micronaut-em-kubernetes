package br.ufla.dcc.todolistmicronaut.data.requests;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
public class ChangeTaskDeadlineByIdRequest {
    public Long id;
    public LocalDateTime dateTime;
}

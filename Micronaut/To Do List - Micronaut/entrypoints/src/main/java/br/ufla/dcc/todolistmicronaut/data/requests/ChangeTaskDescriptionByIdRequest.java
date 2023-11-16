package br.ufla.dcc.todolistmicronaut.data.requests;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
public class ChangeTaskDescriptionByIdRequest {
    public Long id;
    public String description;
}

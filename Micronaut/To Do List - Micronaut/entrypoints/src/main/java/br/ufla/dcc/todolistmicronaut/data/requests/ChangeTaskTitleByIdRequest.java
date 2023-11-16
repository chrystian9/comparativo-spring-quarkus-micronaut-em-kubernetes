package br.ufla.dcc.todolistmicronaut.data.requests;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
public class ChangeTaskTitleByIdRequest {
    public Long id;
    public String title;
}

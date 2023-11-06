package br.ufla.dcc.todolistmicronaut.data.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeTaskDescriptionByIdRequest {
    public Long id;
    public String description;
}

package br.ufla.dcc.todolistspringboot.data.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeTaskDescriptionByIdRequest {
    public Long id;
    public String description;
}

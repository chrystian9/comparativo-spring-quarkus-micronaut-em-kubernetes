package br.ufla.dcc.todolistspringboot.data.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeTaskTitleByIdRequest {
    public Long id;
    public String title;
}

package br.ufla.dcc.todolistspringboot.data.requests;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeTaskTitleByIdRequest {
    public Long Id;
    public String Title;
}

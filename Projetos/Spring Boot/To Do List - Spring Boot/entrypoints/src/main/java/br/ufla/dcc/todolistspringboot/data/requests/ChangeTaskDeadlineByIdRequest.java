package br.ufla.dcc.todolistspringboot.data.requests;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeTaskDeadlineByIdRequest {
    public Long id;
    public LocalDateTime dateTime;
}

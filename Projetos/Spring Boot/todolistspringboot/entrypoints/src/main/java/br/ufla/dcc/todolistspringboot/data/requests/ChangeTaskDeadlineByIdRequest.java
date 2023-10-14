package br.ufla.dcc.todolistspringboot.data.requests;

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

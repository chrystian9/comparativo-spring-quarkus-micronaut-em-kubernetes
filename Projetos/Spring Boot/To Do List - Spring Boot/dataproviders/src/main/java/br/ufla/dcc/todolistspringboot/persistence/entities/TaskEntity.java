package br.ufla.dcc.todolistspringboot.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskEntity {
    @Id
    private Long id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Boolean isCompleted = false;
}

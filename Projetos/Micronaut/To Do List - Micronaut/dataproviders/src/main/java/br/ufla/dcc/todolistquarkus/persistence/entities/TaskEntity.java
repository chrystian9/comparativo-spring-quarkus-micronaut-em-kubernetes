package br.ufla.dcc.todolistquarkus.persistence.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
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
public class TaskEntity extends PanacheEntity {
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Boolean isCompleted = false;
}

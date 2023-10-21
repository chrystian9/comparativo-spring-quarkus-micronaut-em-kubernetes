package br.ufla.dcc.todolistquarkus.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted = false;
}
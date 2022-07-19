package com.example.todolist.task;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime deadline;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskCategory taskCategory;
}

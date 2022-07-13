package com.example.todolist.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
}

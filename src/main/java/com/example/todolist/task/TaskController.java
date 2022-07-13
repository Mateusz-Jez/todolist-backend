package com.example.todolist.task;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;


}

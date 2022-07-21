package com.example.todolist.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping(path = "/{taskCategory}")
    public List<Task> getTasksByCategory(@PathVariable("taskCategory") TaskCategory taskCategory) {
        return taskService.getTasksByCategory(taskCategory);
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSelectedTasks(@RequestBody Long[] tasks) {
        for(Long taskId:tasks) {
            taskService.deleteTask(taskId);
        }
    }

    @PostMapping(path = "/complete/{taskId}")
    public void markTaskAsCompleted(@PathVariable("taskId") Long taskId) {
        taskService.markTaskAsCompleted(taskId);
    }

    @PostMapping(path = "/complete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void markSelectedTasksAsCompleted(@RequestBody Long[] tasks) {
        for(Long taskId:tasks) {
            taskService.markTaskAsCompleted(taskId);
        }
    }

    @PutMapping(path = "/{taskId}")
    public void editTask(@PathVariable("taskId") Long taskId, @RequestBody Task task) {
        taskService.editTask(task);
    }

    @PutMapping
    public void editTasks(@RequestBody List<Task> tasks) {
        for(Task task:tasks){
            taskService.editTask(task);
        }
    }

}

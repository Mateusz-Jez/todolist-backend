package com.example.todolist.task;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public List<Task> getTasksByCategory(@PathVariable("taskCategory") String taskCategory) {
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

    @DeleteMapping
    public void deleteSelectedTasks(@RequestBody List<Long> tasksId) {
        for(Long taskId:tasksId) {
            taskService.deleteTask(taskId);
        }
    }

    @PutMapping(path = "/complete/{taskId}")
    public void markTaskAsCompleted(@PathVariable("taskId") Long taskId) {
        taskService.markTaskAsCompleted(taskId);
    }

    @PutMapping(path = "/complete")
    public void markSelectedTasksAsCompleted(@RequestBody List<Long> tasksId) {
        for(Long taskId:tasksId){
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

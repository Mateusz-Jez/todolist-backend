package com.example.todolist.task;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void deleteSelectedTasks(@RequestBody String tasksId) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Integer> list = mapper.readValue(tasksId, List.class);
            for(Integer taskId:list) {
                taskService.deleteTask(Long.valueOf(taskId));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(path = "/complete/{taskId}")
    public void markTaskAsCompleted(@PathVariable("taskId") Long taskId) {
        taskService.markTaskAsCompleted(taskId);
    }

    @PostMapping(path = "/complete")
    public void markSelectedTasksAsCompleted(@RequestBody String tasksId) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Integer> list = mapper.readValue(tasksId, List.class);
            for(Integer taskId:list) {
                taskService.markTaskAsCompleted(Long.valueOf(taskId));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
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

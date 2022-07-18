package com.example.todolist.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAllByOrderByDeadlineAsc();
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }


    public void deleteTask(Long taskId) {
        if(taskRepository.existsById(taskId)) taskRepository.deleteById(taskId);
    }

    public void markTaskAsCompleted(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()) {
            if(task.get().getTaskCategory() != TaskCategory.COMPLETED) {
                task.get().setTaskCategory(TaskCategory.COMPLETED);
                taskRepository.save(task.get());
            }
        }
    }

    public List<Task> getTasksByCategory(String taskCategory) {
        return taskRepository.findAllByCategory(TaskCategory.valueOf(taskCategory));
    }

    public void editTask(Task task) {
        Optional<Task> taskOptional = taskRepository.findById(task.getId());
        if(taskOptional.isPresent()) {
            Task editedTask = taskOptional.get();
            editedTask.setTitle(task.getTitle());
            editedTask.setDescription(task.getDescription());
            editedTask.setDeadline(task.getDeadline());
            editedTask.setTaskCategory(task.getTaskCategory());
            taskRepository.save(editedTask);
        }
    }
}

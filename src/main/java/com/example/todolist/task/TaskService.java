package com.example.todolist.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getTasks() {
        return setOverdueTasks(taskRepository.findAllByOrderByDeadlineAsc());
    }

    public List<Task> setOverdueTasks(List<Task> tasks) {
        for(Task t:tasks) {
            if(t.getDeadline().isBefore(LocalDateTime.now())) {
                if(t.getTaskCategory() != TaskCategory.COMPLETED) {
                    t.setTaskCategory(TaskCategory.OVERDUE);
                    taskRepository.save(t);
                }
            }
        }
        return tasks;
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

    public List<Task> getTasksByCategory(TaskCategory taskCategory) {
        setOverdueTasks(taskRepository.findAll());
        return taskRepository.findAllByCategory(taskCategory);
    }

    public void editTask(Task task) {
        Optional<Task> taskOptional = taskRepository.findById(task.getId());
        if(taskOptional.isPresent()) {
            Task editedTask = taskOptional.get();
            editedTask.setTitle(task.getTitle());
            editedTask.setDescription(task.getDescription());
            editedTask.setDeadline(task.getDeadline());
            editedTask.setTaskCategory(task.getTaskCategory());
            if(editedTask.getTaskCategory() == TaskCategory.OVERDUE && editedTask.getDeadline().isAfter(LocalDateTime.now())){
                editedTask.setTaskCategory(TaskCategory.PENDING);
            }
            else if(editedTask.getTaskCategory() == TaskCategory.PENDING && editedTask.getDeadline().isBefore(LocalDateTime.now())) {
                editedTask.setTaskCategory(TaskCategory.OVERDUE);
            }
            taskRepository.save(editedTask);
        }
    }
}

package com.example.todolist.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT t from Task t WHERE t.taskCategory = ?1 ORDER BY t.deadline ASC")
    List<Task> findAllByCategory(TaskCategory taskCategory);

    List<Task> findAllByOrderByDeadlineAsc();
}

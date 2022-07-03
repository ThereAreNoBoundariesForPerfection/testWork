package com.example.testwork.repositories;

import com.example.testwork.models.Task;

import java.util.List;

public interface TaskRepository {

    void saveTask(Task task);

    Task getTask(Long id);

    void updateTask(Task task);

    void deleteTask(Long id);

    List<Task> getAllTasks();
}

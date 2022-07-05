package com.example.testwork.controllers;

import com.example.testwork.exeption.TaskAlreadyExistsException;
import com.example.testwork.exeption.TaskNotFoundException;
import com.example.testwork.exeption.TopicTaskNotFoundException;
import com.example.testwork.models.Task;
import com.example.testwork.repositories.TaskRepositoryImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(description = "controller witch show work swagger")
public class TasksRestController {
    private final TaskRepositoryImpl taskRepository;

    @Autowired
    public TasksRestController(TaskRepositoryImpl taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    @ApiOperation("get all tasks")
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    @ApiOperation("get one tasks by id")
    public Task getTaskById(@PathVariable Long id) {
        Task task = taskRepository.getTask(id);
        if (task != null) {
            return task;
        }
        throw new TaskNotFoundException("task with this " + id + " not found");
    }

    @PostMapping("/tasks")
    @ApiOperation("create new tasks")
    public ResponseEntity<Void> newTask(@RequestBody Task task) {
        if (taskRepository.getTaskByName(task.getName()) == null) {
            taskRepository.saveTask(task);
            return ok().build();
        }
        throw new TaskAlreadyExistsException("a task with this name already exists");
    }

    @PutMapping("/tasks/{id}")
    @ApiOperation("update task by id")
    public ResponseEntity<Void> updateTask(@RequestBody Task task, @PathVariable Long id) {

        Task anotherTask = taskRepository.getTask(id);
        if (task != null) {
            anotherTask.setTopicTaskId(task.getTopicTaskId());
            anotherTask.setName(task.getName());
            anotherTask.setDescription(task.getDescription());
            anotherTask.setDate(task.getDate());

            taskRepository.updateTask(anotherTask);
            return ok().build();
        }
        throw new TopicTaskNotFoundException("topic task with this " + id + " not found");
    }

    @DeleteMapping("/tasks/{id}")
    @ApiOperation("delete tasks by id")
    public ResponseEntity<Void> deleteTopicTaskById(@PathVariable Long id) {
        taskRepository.deleteTask(id);
        return ok().build();
    }
}

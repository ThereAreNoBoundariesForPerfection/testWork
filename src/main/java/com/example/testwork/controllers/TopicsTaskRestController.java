package com.example.testwork.controllers;

import com.example.testwork.exeption.TopicTaskAlreadyExistsException;
import com.example.testwork.exeption.TopicTaskNotFoundException;
import com.example.testwork.models.TopicTask;
import com.example.testwork.repositories.TopicTaskRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TopicsTaskRestController {
    private final TopicTaskRepositoryImpl topicTaskRepository;

    @Autowired
    public TopicsTaskRestController(TopicTaskRepositoryImpl topicTaskRepository) {
        this.topicTaskRepository = topicTaskRepository;
    }

    @GetMapping("/topics")
    public List<TopicTask> getAllTasks() {
        return topicTaskRepository.getAllTopicTask();
    }


    @GetMapping("topics/{id}")
    public TopicTask getTopicTaskById(@PathVariable Long id) {
        TopicTask topicTask = topicTaskRepository.getTopicTask(id);
        if (topicTask != null) {
            return topicTask;
        }
        throw new TopicTaskNotFoundException("topic task with this " + id + " not found");
    }

    @PostMapping("/topics")
    public ResponseEntity<Void> newTopicTask(@RequestBody TopicTask topicTask) {
        if (topicTaskRepository.getTopicTaskByName(topicTask.getName()) == null) {
            topicTaskRepository.saveTopicTask(topicTask);
            return ok().build();
        }
            throw new TopicTaskAlreadyExistsException("a task with this name already exists");
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<Void> updateTopicTask(@RequestBody TopicTask topicTask, @PathVariable Long id) {

        TopicTask anotherTopicTask = topicTaskRepository.getTopicTask(id);
        if (anotherTopicTask != null) {
            anotherTopicTask.setName(topicTask.getName());

            topicTaskRepository.updateTopicTask(anotherTopicTask);
            return ok().build();
        }
        throw new TopicTaskNotFoundException("topic task with this " + id + " not found");
    }


    @DeleteMapping("/topics/{id}")
    public ResponseEntity<Void> deleteTopicTaskById(@PathVariable Long id) {
        topicTaskRepository.deleteTopicTask(id);
        return ok().build();
    }
}

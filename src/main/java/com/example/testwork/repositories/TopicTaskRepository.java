package com.example.testwork.repositories;

import com.example.testwork.models.TopicTask;

import java.util.List;

public interface TopicTaskRepository {

    void saveTopicTask(TopicTask topicTask);

    TopicTask getTopicTask(Long id);

    void updateTopicTask(TopicTask topicTask);

    void deleteTopicTask(Long id);

    List<TopicTask> getAllTopicTask();
}

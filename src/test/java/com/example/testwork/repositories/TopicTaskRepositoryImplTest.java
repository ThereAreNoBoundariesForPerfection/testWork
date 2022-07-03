package com.example.testwork.repositories;

import com.example.testwork.models.TopicTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TopicTaskRepositoryImplTest {
    @MockBean
    private TopicTaskRepositoryImpl topicTaskRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private TopicTask topicTask;

    @BeforeEach
    void setUp() {
        topicTaskRepository = new TopicTaskRepositoryImpl(jdbcTemplate);
        topicTask = new TopicTask();
        topicTask.setName("test");
    }

    @AfterEach
    void tearDown() {
        topicTaskRepository = null;
        jdbcTemplate = null;
        topicTask = null;
    }

    @Test
    void saveTopicTask() {
        topicTaskRepository.saveTopicTask(topicTask);

        TopicTask actualTopicTask = topicTaskRepository.getTopicTaskByName(topicTask.getName());
        assertEquals("test", actualTopicTask.getName());

        topicTaskRepository.deleteTopicTask(actualTopicTask.getId());
    }

    @Test
    void getTopicTask() {
        topicTaskRepository.saveTopicTask(topicTask);
        TopicTask actualTopicTask = topicTaskRepository.getTopicTaskByName("test");

        assertEquals(actualTopicTask, topicTaskRepository.getTopicTask(actualTopicTask.getId()));
        topicTaskRepository.deleteTopicTask(actualTopicTask.getId());
    }

    @Test
    void updateTopicTask() {
        topicTaskRepository.saveTopicTask(topicTask);
        TopicTask actualTopicTask = topicTaskRepository.getTopicTaskByName("test");

        actualTopicTask.setName("test2");
        topicTaskRepository.updateTopicTask(actualTopicTask);

        assertEquals("test2", topicTaskRepository.getTopicTask(actualTopicTask.getId()).getName());
        topicTaskRepository.deleteTopicTask(actualTopicTask.getId());
    }

    @Test
    void deleteTopicTask() {
        topicTaskRepository.saveTopicTask(topicTask);
        TopicTask actualTopicTask = topicTaskRepository.getTopicTaskByName("test");

        topicTaskRepository.deleteTopicTask(actualTopicTask.getId());
        TopicTask checkTopicTask = topicTaskRepository.getTopicTask(actualTopicTask.getId());
        assertNull(checkTopicTask);

    }

    @Test
    void getAllTopicTask() {
        TopicTask topicTask1 = new TopicTask();
        topicTask1.setName("test1");
        TopicTask topicTask2 = new TopicTask();
        topicTask2.setName("test2");
        TopicTask topicTask3 = new TopicTask();
        topicTask3.setName("test3");

        topicTaskRepository.saveTopicTask(topicTask1);
        topicTaskRepository.saveTopicTask(topicTask2);
        topicTaskRepository.saveTopicTask(topicTask3);

        assertEquals(3, topicTaskRepository.getAllTopicTask().size());

        topicTask1 = topicTaskRepository.getTopicTaskByName("test1");
        topicTask2 = topicTaskRepository.getTopicTaskByName("test2");
        topicTask3 = topicTaskRepository.getTopicTaskByName("test3");

        topicTaskRepository.deleteTopicTask(topicTask1.getId());
        topicTaskRepository.deleteTopicTask(topicTask2.getId());
        topicTaskRepository.deleteTopicTask(topicTask3.getId());
    }
}
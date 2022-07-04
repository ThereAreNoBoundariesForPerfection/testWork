package com.example.testwork.repositories;

import com.example.testwork.models.Task;
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
class TaskRepositoryImplTest {
    @MockBean
    private TaskRepositoryImpl taskRepository;
    @MockBean
    private TopicTaskRepositoryImpl topicTaskRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private TopicTask topicTask;
    private Task task;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepositoryImpl(jdbcTemplate);
        topicTaskRepository = new TopicTaskRepositoryImpl(jdbcTemplate);
        topicTask = new TopicTask();
        topicTask.setName("topicTest");
        topicTaskRepository.saveTopicTask(topicTask);
        topicTask = topicTaskRepository.getTopicTaskByName("topicTest");
        task = new Task();
        task.setTopicTaskId(topicTask.getId());
        task.setName("test");
        task.setDescription("description");
        task.setDate("2022 06 16 15:45");
    }

    @AfterEach
    void tearDown() {
        if (topicTask.getId() != 0) {
            topicTaskRepository.deleteTopicTask(topicTask.getId());
        }

        taskRepository = null;
        topicTaskRepository = null;
        jdbcTemplate = null;
        task = null;
    }

    @Test
    void saveTask() {
        taskRepository.saveTask(task);
        assertEquals("test", taskRepository.getTaskByName("test").getName());

        Task actualTask = taskRepository.getTaskByName("test");
        taskRepository.deleteTask(actualTask.getId());
    }

    @Test
    void getTask() {
        taskRepository.saveTask(task);
        Task actualTask = taskRepository.getTaskByName("test");
        assertEquals(actualTask, taskRepository.getTask(actualTask.getId()));
        taskRepository.deleteTask(actualTask.getId());
    }

    @Test
    void updateTask() {
        taskRepository.saveTask(task);
        Task actualTask = taskRepository.getTaskByName("test");
        actualTask.setName("changeName");
        taskRepository.updateTask(actualTask);

        assertEquals(actualTask.getName(), taskRepository.getTask(actualTask.getId()).getName());
        taskRepository.deleteTask(actualTask.getId());


    }

    @Test
    void deleteTask() {
        taskRepository.saveTask(task);
        Task actualTask = taskRepository.getTaskByName("test");
        taskRepository.deleteTask(actualTask.getId());

        Task checkTask = taskRepository.getTask(actualTask.getId());
        assertNull(checkTask);
    }

    @Test
    void getAllTasks() {
        Task task1 = new Task();
        task1.setTopicTaskId(topicTask.getId());
        task1.setName("test1");
        task1.setDescription("description1");
        task1.setDate("2022 06 18 15:45");
        Task task2 = new Task();
        task2.setTopicTaskId(topicTask.getId());
        task2.setName("test2");
        task2.setDescription("description2");
        task2.setDate("2022 06 18 15:45");
        Task task3 = new Task();
        task3.setTopicTaskId(topicTask.getId());
        task3.setName("test3");
        task3.setDescription("description3");
        task3.setDate("2022 06 18 15:45");

        taskRepository.saveTask(task1);
        taskRepository.saveTask(task2);
        taskRepository.saveTask(task3);

        assertEquals(3, taskRepository.getAllTasks().size());

        task1 = taskRepository.getTaskByName("test1");
        task2 = taskRepository.getTaskByName("test2");
        task3 = taskRepository.getTaskByName("test3");

        taskRepository.deleteTask(task1.getId());
        taskRepository.deleteTask(task2.getId());
        taskRepository.deleteTask(task3.getId());

    }

    @Test
    void getTaskByName() {
    }

    @Test
    void getAllTasksDesk() {
    }
}
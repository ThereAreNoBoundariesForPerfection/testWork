package com.example.testwork.controllers;

import com.example.testwork.config.IntegrationTest;
import com.example.testwork.config.SpringConfig;
import com.example.testwork.models.TopicTask;
import com.example.testwork.repositories.TopicTaskRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TopicsTaskRestControllerTest extends IntegrationTest {
    @Mock
    private TopicTaskRepositoryImpl topicTaskRepository;
    private TopicTask topicTask;

    @BeforeEach
    void setUp() {
        topicTaskRepository = new TopicTaskRepositoryImpl(new SpringConfig().jdbcTemplate());
        topicTask = new TopicTask();
        topicTask.setName("test");
        topicTaskRepository.saveTopicTask(topicTask);
        topicTask = topicTaskRepository.getTopicTaskByName("test");

    }

    @AfterEach
    void tearDown() {
        topicTaskRepository.deleteTopicTask(topicTask.getId());
        topicTaskRepository = null;
        topicTask = null;
    }


    @Test
    void getAllTasks() throws Exception {
        mockMvc.perform(get("/topics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void getTopicTaskById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/topics/" + topicTask.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }


    @Test
    void newTopicTask() {
    }

    @Test
    void updateTopicTask() {
    }

    @Test
    void deleteTopicTaskById() {

    }

}
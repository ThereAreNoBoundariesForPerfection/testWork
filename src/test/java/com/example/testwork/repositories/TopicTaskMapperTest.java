package com.example.testwork.repositories;

import com.example.testwork.models.TopicTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicTaskMapperTest {
    @Mock
    private ResultSet resultSet;
    private TopicTaskMapper topicTaskMapper;

    @BeforeEach
    void setUp() {
        topicTaskMapper = new TopicTaskMapper();
    }

    @AfterEach
    void tearDown() {
        topicTaskMapper = null;
        resultSet = null;
    }

    @Test
    void mapRow() throws SQLException {
        TopicTask expectedResult = createTopicTask();

        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("test");

        TopicTask actualResult = topicTaskMapper.mapRow(resultSet, 0);
        assertEquals(expectedResult, actualResult);
    }

    private TopicTask createTopicTask() {
        TopicTask topicTask = new TopicTask();
        topicTask.setId(1L);
        topicTask.setName("test");
        return topicTask;
    }
}